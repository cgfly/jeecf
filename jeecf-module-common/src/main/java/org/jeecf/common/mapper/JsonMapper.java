package org.jeecf.common.mapper;

import java.io.IOException;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

/**
 * 封装 jackson
 * 
 * @author GloryJ
 *
 */
public class JsonMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * CLOB 类型数据
	 */
	private static final String CLOB = "<CLOB>";

	private static JsonMapper mapper;

	public static JsonMapper getInstance() {
		if (mapper == null) {
			mapper = new JsonMapper().enableSimple();
			mapper.setSerializationInclusion(Include.NON_EMPTY);
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
				@Override
				public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
						throws IOException, JsonProcessingException {
					jsonGenerator.writeString("");
				}
			});
		}
		return mapper;
	}

	/**
	 * 允许单引号 允许不带引号的字段名称
	 */
	public JsonMapper enableSimple() {
		this.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		this.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		return this;
	}

	/**
	 * Object可以是POJO，也可以是Collection或数组。 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
	 */
	public String toJsonString(Object object) {
		try {
			return this.writeValueAsString(object);
		} catch (IOException e) {
			throw new BusinessException(SysErrorEnum.IO_ERROR);
		}
	}

	/**
	 * Object可以是POJO，也可以是Collection或数组。 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
	 */
	public static String toJson(Object object) {
		return getInstance().toJsonString(object);
	}

	/**
	 * 输出JSONP格式数据.
	 */
	public String toJsonpString(String functionName, Object object) {
		return toJsonString(new JSONPObject(functionName, object));
	}

	/**
	 * 获取JsonNodes.
	 */
	public JsonNode getJsonNodeString(String jsonString) {
		try {
			return this.readTree(jsonString);
		} catch (JsonProcessingException e) {
			return null;
		} catch (IOException e) {
			throw new BusinessException(SysErrorEnum.IO_ERROR);
		}
	}

	public static JsonNode getJsonNode(String jsonString) {
		try {
			return getInstance().readTree(jsonString);
		} catch (JsonProcessingException e) {
			return null;
		} catch (IOException e) {
			throw new BusinessException(SysErrorEnum.IO_ERROR);
		}
	}

	/**
	 * 反序列化POJO或简单Collection如List<String>. 如果JSON字符串为Null或"null"字符串, 返回Null.
	 * 如果JSON字符串为"[]", 返回空集合. 如需反序列化复杂Collection如List<MyBean>,
	 * 请使用fromJson(String,JavaType)
	 * 
	 * @see #fromJson(String, JavaType)
	 */
	public <T> T fromJsonString(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString) || CLOB.equals(jsonString)) {
			return null;
		}
		try {
			return this.readValue(jsonString, clazz);
		} catch (IOException e) {
			throw new BusinessException(SysErrorEnum.IO_ERROR);
		}
	}

	/**
	 * 反序列化复杂Collection如List<Bean>, 先使用函数createCollectionType构造类型,然后调用本函数.
	 * 
	 * @see #createCollectionType(Class, Class...)
	 */
	@SuppressWarnings("unchecked")
	public <T> T fromJsonString(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString) || CLOB.equals(jsonString)) {
			return null;
		}
		try {
			return (T) this.readValue(jsonString, javaType);
		} catch (IOException e) {
			throw new BusinessException(SysErrorEnum.IO_ERROR);
		}
	}

	/**
	 * 构造泛型的Collection Type如: ArrayList<MyBean>,
	 * 则调用constructCollectionType(ArrayList.class,MyBean.class)
	 * HashMap<String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)
	 */
	public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return this.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	/**
	 * 当JSON里只含有Bean的部分属性時，更新一个已存在Bean，只覆盖该部分的属性.
	 */
	@SuppressWarnings("unchecked")
	public <T> T update(String jsonString, T object) {
		try {
			return (T) this.readerForUpdating(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
			return null;
		} catch (IOException e) {
			throw new BusinessException(SysErrorEnum.IO_ERROR);
		}
	}

	/**
	 * 设定是否使用Enum的toString函数来读写Enum, 为False实时使用Enum的name()函数来读写Enum, 默认为False.
	 * 注意本函数一定要在Mapper创建后, 所有的读写动作之前调用.
	 */
	public JsonMapper enableEnumUseToString() {
		this.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		this.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		return this;
	}

	/**
	 * 支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合。
	 * 默认会先查找jaxb的annotation，如果找不到再找jackson的。
	 */
	public JsonMapper enableJaxbAnnotation() {
		JaxbAnnotationModule module = new JaxbAnnotationModule();
		this.registerModule(module);
		return this;
	}

}
