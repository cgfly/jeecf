package org.jeecf.osgi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 插件入参
 * @author jianyiming
 *
 */
public class PluginRequest {
	
	Map<String, Object> request = new HashMap<String, Object>();
	
	public Object getAttribute(String key) {
		return request.get(key);
	}

	public void setAttribute(String key, Object value) {
		request.put(key, value);
	}
	
	public List<Object> getValues() {
		List<Object> result = new ArrayList<>();
		for (Object value : request.values()) {
			result.add(value);
		}
		return result;
	}
	
	public List<String> getKeys() {
		List<String> result = new ArrayList<>();
		for (String value : request.keySet()) {
			result.add(value);
		}
		return result;
	}
	
	public Map<String,Object> attr() {
		return request;
	}

}
