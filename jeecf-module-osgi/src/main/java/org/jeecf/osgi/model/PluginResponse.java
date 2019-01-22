package org.jeecf.osgi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插件出参
 * 
 * @author jianyiming
 *
 */
public class PluginResponse {

    Map<String, Object> response = new HashMap<String, Object>();

    public Object getAttribute(String key) {
        return response.get(key);
    }

    public void setAttribute(String key, Object value) {
        response.put(key, value);
    }

    public List<Object> getValues() {
        List<Object> result = new ArrayList<>();
        for (Object value : response.values()) {
            result.add(value);
        }
        return result;
    }

    public List<String> getKeys() {
        List<String> result = new ArrayList<>();
        for (String value : response.keySet()) {
            result.add(value);
        }
        return result;
    }

    public Map<String, Object> attr() {
        return response;
    }

}
