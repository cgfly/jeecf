package org.jeecf.manager.cache;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.cache.CacheContext;
import org.jeecf.cache.CacheLoadStore;
import org.jeecf.cache.enums.TypeEnum;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.EncodeUtils;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.RedisCacheUtils;

import com.fasterxml.jackson.databind.JavaType;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * 查询缓存 实现类
 * 
 * @author jianyiming
 * @version 2.0
 */
public class QueryCacheLoadStore implements CacheLoadStore {

    @Override
    public String getKey(CacheContext context) {
        String className = context.getClassName();
        String methodName = context.getMethodName();
        Object[] args = context.getArgs();
        Class<? extends Object> cls = context.getClazz();
        StringBuilder key = new StringBuilder(className + SplitCharEnum.COLON.getName());
        Map<String, Object> fields = this.getFieldsName(cls, className, methodName, args);
        if (fields != null && fields.size() > 0) {
            String keyFields = EncodeUtils.encodeBase64(JsonMapper.toJson(fields));
            key.append(keyFields + SplitCharEnum.COLON.getName());
        }
        key.append(methodName);
        return key.toString();
    }

    @Override
    public Object load(CacheContext context, String key) {
        Object obj = RedisCacheUtils.getCache(key);
        if (obj != null) {
            Class<? extends Object> cls = context.getClazz();
            TypeEnum type = context.getType();
            Type superClass = cls.getGenericSuperclass();
            Type resultClass = null;
            if (superClass instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType) superClass).getActualTypeArguments();
                resultClass = p[2];
            }
            try {
                if (type == TypeEnum.GET) {
                    JavaType resultType = JsonMapper.getInstance().getTypeFactory().constructType(resultClass);
                    JavaType responseType = JsonMapper.getInstance().getTypeFactory().constructParametricType(Response.class, resultType);
                    return JsonMapper.getInstance().readValue(obj.toString(), responseType);
                } else if (type == TypeEnum.LIST) {
                    JavaType resultType = JsonMapper.getInstance().getTypeFactory().constructType(resultClass);
                    JavaType listType = JsonMapper.getInstance().getTypeFactory().constructCollectionType(List.class, resultType);
                    JavaType responseType = JsonMapper.getInstance().getTypeFactory().constructParametricType(Response.class, listType);
                    return JsonMapper.getInstance().readValue(obj.toString(), responseType);
                } else if (type == TypeEnum.RETURNCLASS) {
                    Class<? extends Object> returnClass = context.getReturnClass();
                    JavaType responseType = JsonMapper.getInstance().getTypeFactory().constructParametricType(Response.class, returnClass);
                    return JsonMapper.getInstance().readValue(obj.toString(), responseType);
                }
            } catch (Exception e) {
                throw new BusinessException(SysErrorEnum.PARSE_ERROR);
            }
        }
        return obj;
    }

    @Override
    public void store(CacheContext context, String key, Object value) {
        Integer timeout = context.getTimeout();
        RedisCacheUtils.setCache(key, JsonMapper.toJson(value), timeout);
    }

    private Map<String, Object> getFieldsName(Class<? extends Object> cls, String clazzName, String methodName, Object[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);
        CtClass cc;
        try {
            cc = pool.get(clazzName);
            CtMethod cm = null;
            while ((cm = this.getMethod(cc, methodName)) == null) {
                cc = cc.getSuperclass();
            }
            MethodInfo methodInfo = cm.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            for (int i = 0; i < cm.getParameterTypes().length; i++) {
                map.put(attr.variableName(i + pos), args[i]);// paramNames即参数名
            }
            return map;
        } catch (NotFoundException e) {
            throw new BusinessException(BusinessErrorEnum.FUNCTION_NOT_FOUND);
        }

    }

    private CtMethod getMethod(CtClass cc, String methodName) throws NotFoundException {
        try {
            return cc.getDeclaredMethod(methodName);
        } catch (Exception e) {
            return null;
        }
    }

}
