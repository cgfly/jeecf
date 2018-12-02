package org.jeecf.manager.subject;

import java.util.Map;
/**
 * 主题 上下文信息
 * @author jianyiming
 *
 */
public class SubjectContext {
	
	private Map<String,String> data;

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	public void set(String key,String value) {
		this.data.put(key, value);
	}
	
	public String get(String key) {
		return this.data.get(key);
	}
	
	

}
