package org.jeecf.manager.common.thymeleaf.expression;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.enums.EnumEntity;
import org.jeecf.manager.common.enums.EnumUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 枚举 表达式
 * @author jianyiming
 *
 */
public final class Enums {
   /**
    * 获取枚举类 name
    * @param type  枚举类
    * @param code 值
    * @param defaultName 默认名称
    * @return
    */
	public String getName(String type,Integer code,String defaultName) {
		return EnumUtils.getName(type, code, defaultName);
	}
	/**
	 * 获取枚举类 value
	 * @param type 枚举类
	 * @param name 枚举名称
	 * @param defaultCode 默认value
	 * @return
	 */
	public Integer getCode(String type,String name,Integer defaultCode) {
		return EnumUtils.getCode(type, name, defaultCode);
	}
	
	/**
	 * 获取名称和value 
	 * @param type 类型
	 * @return
	 */
	public List<EnumEntity> getArray(String type) {
		List<EnumEntity> result = new ArrayList<EnumEntity>();
		String resultStr = EnumUtils.getJsonString(type);
		if(StringUtils.isNotEmpty(resultStr)) {
			JSONArray array = JSONArray.parseArray(resultStr);
			for(int i = 0; i < array.size(); i++) {
				JSONObject obj = array.getJSONObject(i);
				EnumEntity entity = new EnumEntity();
				entity.setName(obj.getString("name"));
				entity.setValue(obj.getIntValue("code"));
				result.add(entity);
			}
		}
		return result;
	}
	
}
