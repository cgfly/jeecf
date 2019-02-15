package org.jeecf.gen.chain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.gen.utils.TableHook;

/**
 * 责任链上下文
 * 
 * @author jianyiming
 * @since 1.0
 */
public class ChainContext {
    /**
     * 线程变量
     */
    private ThreadLocal<Map<String, Object>> local = new ThreadLocal<>();
    /**
     * 责任链集合
     */
    private List<AbstractHandler> handlers = null;
    /**
     * 表数据回调
     */
    private TableHook tableHook;
    /**
     * 责任链长度
     */
    private int size = -1;
    /**
     * 责任链指针
     */
    private static final String INDEX = "index";
    /**
     * 状态标志
     */
    private static final String FLAG = "flag";
    /**
     * 上下文参数应用参数
     */
    private static final String CONTEXT_CONFIG_PARAMS = "contextParams";
    /**
     * 存储代码生成参数
     */
    private static final String PARAMS = "params";

    /**
     * 构造器 初始化上下文
     * 
     * @param handlers
     */
    public ChainContext(List<AbstractHandler> handlers, TableHook tableHook) {
        this.handlers = handlers;
        this.tableHook = tableHook;
        this.size = handlers.size();
    }

    /**
     * 赋值存储代码参数
     * 
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        @SuppressWarnings("unchecked")
        Map<String, Object> params = (Map<String, Object>) this.getLocalMap(ChainContext.PARAMS);
        params.put(key, value);
    }

    /**
     * 获取存储代码参数
     * 
     * @param key
     * @return
     */
    public Object get(String key) {
        @SuppressWarnings("unchecked")
        Map<String, Object> params = (Map<String, Object>) this.getLocalMap(ChainContext.PARAMS);
        return params.get(key);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getParams() {
        Map<String, Object> params = (Map<String, Object>) this.getLocalMap(ChainContext.PARAMS);
        if (params == null) {
            params = new HashMap<>(12);
        }
        this.setLocalMap(ChainContext.PARAMS, params);
        return params;
    }

    public boolean isFlag() {
        Boolean flag = (Boolean) this.getLocalMap(ChainContext.FLAG);
        if (flag == null) {
            flag = true;
        }
        return flag.booleanValue();
    }

    public void setFlag(boolean flag) {
        this.setLocalMap(ChainContext.FLAG, flag);
    }

    public TableHook getTableHook() {
        return tableHook;
    }

    public ContextConfigParams getContextParams() {
        return (ContextConfigParams) this.getLocalMap(ChainContext.CONTEXT_CONFIG_PARAMS);
    }

    public void setContextParams(ContextConfigParams contextParams) {
        this.setLocalMap(ChainContext.CONTEXT_CONFIG_PARAMS, contextParams);
    }

    /**
     * 执行下一责任节点
     */
    public void next() {
        Integer index = (Integer) this.getLocalMap(ChainContext.INDEX);
        if (index == null) {
            index = -1;
        }
        if (index < this.size - 1 && this.isFlag()) {
            index++;
            AbstractHandler handler = handlers.get(index);
            handler.init(this);
            this.setLocalMap(ChainContext.INDEX, index);
            handler.process();
        }
    }

    /**
     * 设置线程参数
     * 
     * @param key
     * @param value
     */
    private void setLocalMap(String key, Object value) {
        Map<String, Object> localMap = local.get();
        if (localMap == null) {
            localMap = new HashMap<String, Object>(10);
            local.set(localMap);
        }
        localMap.put(key, value);
    }

    /**
     * 获取线程参数
     * 
     * @param key
     * @return
     */
    private Object getLocalMap(String key) {
        Map<String, Object> localMap = local.get();
        if (localMap != null) {
            return localMap.get(key);
        }
        return null;
    }

    /**
     * 回收线程参数
     */
    public void remove() {
        local.remove();
    }

}
