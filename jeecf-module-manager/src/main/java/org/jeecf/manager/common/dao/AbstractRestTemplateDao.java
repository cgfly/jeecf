package org.jeecf.manager.common.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * rest 请求dao
 * @author jianyiming
 *
 * @param <T>
 * @param <R>
 */
public abstract class AbstractRestTemplateDao<T, R> {

	@Autowired
	private RestTemplate restTemplate;
    /**
     * 初始化get请求url
     * @return
     */
	public abstract String initGetUrl();
    /**
     * 初始化post请求url
     * @return
     */
	public abstract String initPostUrl();

	public ResponseEntity<JSONObject> getForJson(Map<String, Object> uriVariables) {
		return restTemplate.getForEntity(this.initGetUrl(), JSONObject.class, uriVariables);
	}

	public ResponseEntity<R> getForObject(Map<String, Object> uriVariables, Class<R> clazz) {
		return restTemplate.getForEntity(this.initGetUrl(), clazz, uriVariables);
	}

	public ResponseEntity<JSONObject> postForJson(T t) {
		return restTemplate.postForEntity(this.initPostUrl(), t, JSONObject.class);
	}

	public ResponseEntity<R> postForObject(T t, Class<R> clazz) {
		return restTemplate.postForEntity(this.initPostUrl(), t, clazz);
	}

	public ResponseEntity<JSONObject> getForJson(Map<String, String> headers, Map<String, Object> uriVariables) {
		HttpHeaders requestHeaders = new HttpHeaders();
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			requestHeaders.add(entry.getKey(), entry.getValue());
		}
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
		return restTemplate.exchange(this.initGetUrl(), HttpMethod.GET, requestEntity, JSONObject.class, uriVariables);
	}
	
	public ResponseEntity<R> getForObject(Map<String, String> headers, Map<String, Object> uriVariables,Class<R> clazz) {
		HttpHeaders requestHeaders = new HttpHeaders();
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			requestHeaders.add(entry.getKey(), entry.getValue());
		}
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
		return restTemplate.exchange(this.initGetUrl(), HttpMethod.GET, requestEntity, clazz, uriVariables);
	}
	
	public ResponseEntity<JSONObject> postForJson(Map<String, String> headers, Map<String, Object> uriVariables,T t) {
		HttpHeaders requestHeaders = new HttpHeaders();
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			requestHeaders.add(entry.getKey(), entry.getValue());
		}
		HttpEntity<T> requestEntity = new HttpEntity<T>(t, requestHeaders);
		return restTemplate.exchange(this.initPostUrl(), HttpMethod.POST, requestEntity, JSONObject.class, uriVariables);
	}
	
	public ResponseEntity<R> postForObject(Map<String, String> headers, Map<String, Object> uriVariables,T t,Class<R> clazz) {
		HttpHeaders requestHeaders = new HttpHeaders();
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			requestHeaders.add(entry.getKey(), entry.getValue());
		}
		HttpEntity<T> requestEntity = new HttpEntity<T>(t, requestHeaders);
		return restTemplate.exchange(this.initPostUrl(), HttpMethod.POST, requestEntity, clazz, uriVariables);
	}

}
