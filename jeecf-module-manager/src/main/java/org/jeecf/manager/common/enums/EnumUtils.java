package org.jeecf.manager.common.enums;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.mapper.JsonMapper;

/**
 * 枚举工具
 * @author GloryJian
 * @version 1.0
 */
public class EnumUtils {

	public static int getCode(String type, String name,int defaultCode) {
	    switch(type.toUpperCase()) {
		   case "IFTYPE":
			 return IfType.getCode(name);
		   case "FORMTYPE":
			 return FormType.getCode(name);
		   case "USABLE":
			 return Usable.getCode(name);
		   case "LANGUAGE":
			 return Language.getCode(name);
		   case "QUERYTYPE":
			 return QueryType.getCode(name);
		   default:
			 return defaultCode;
		}
	}

	public static String getName(String type, int code,String defaultName) {
		switch(type.toUpperCase()) {
		   case "IFTYPE":
			 return IfType.getName(code);
		   case "FORMTYPE":
			 return FormType.getName(code);
		   case "USABLE":
			 return Usable.getName(code);
		   case "LANGUAGE":
			 return Language.getName(code);
		   case "QUERYTYPE":
			 return QueryType.getName(code);
		   default:
			 return defaultName;
		}
	}

	public static String getJsonString(String type) {
		switch(type.toUpperCase()) {
		   case "IFTYPE":
			 return IfType.toJsonString();
		   case "FORMTYPE":
			 return FormType.toJsonString();
		   case "USABLE":
			 return Usable.toJsonString();
		   case "LANGUAGE":
			 return Language.toJsonString();
		   case "QUERYTYPE":
			 return QueryType.toJsonString();
		   default:
			 return null;
		}
	}
	
	/**
	 * 条件类型
	 * @author jianyiming
	 *
	 */
    public static enum IfType {
    	 NO(0,"否"),
        YES(1,"是"),
        ;
        
        private final int code;
		private final String name;

		public int getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		private IfType(int code, String name) {
			this.code = code;
			this.name = name;
		}

		public static Integer getCode(String name) {
			for (IfType e : IfType.values()) {
				if (e.getName().equals(name) ) {
					return e.getCode();
				}
			}
			return null;
		}
		
		public static String getName(int code) {
			for (IfType e :  IfType.values()) {
				if (e.getCode() == code ) {
					return e.getName();
				}
			}
			return null;
		}
		
		public static String toJsonString() {
			List<Map<String,Object>> dataMap = new ArrayList<>();
	        for (IfType e : IfType.values()) {
	            Map<String,Object> map = new HashMap<String,Object>(10);
	            map.put("code", e.getCode());
	            map.put("name", e.getName());
	            dataMap.add(map);
	        }
	        return JsonMapper.toJson(dataMap);
		}
    }
    /**
     * 表单类型
     * @author jianyiming
     *
     */
    public static enum FormType {
        TEXT(0,"文本框"),
        AREA(1,"文本域"),
        NUMBER(2,"数字框"),
        TIME(3,"时间框"),
        SELECT(4,"下拉框"),
        TABLE_SELECT(5,"表格下拉框"),
        ;
        
        private final int code;
		private final String name;

		public int getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		private FormType(int code, String name) {
			this.code = code;
			this.name = name;
		}

		public static Integer getCode(String name) {
			for (FormType e : FormType.values()) {
				if (e.getName().equals(name) ) {
					return e.getCode();
				}
			}
			return null;
		}
		
		public static String getName(int code) {
			for (FormType e :  FormType.values()) {
				if (e.getCode() == code ) {
					return e.getName();
				}
			}
			return null;
		}
		
		public static String toJsonString() {
			List<Map<String,Object>> dataMap = new ArrayList<>();
	        for (FormType e : FormType.values()) {
	            Map<String,Object> map = new HashMap<String,Object>(10);
	            map.put("code", e.getCode());
	            map.put("name", e.getName());
	            dataMap.add(map);
	        }
	        return JsonMapper.toJson(dataMap);
		}
    }
    /**
     * 可用性
     * @author jianyiming
     *
     */
    public static enum Usable {
        YES(1,"可用"),
        NO(2,"不可用"),
        ;
        
        private final int code;
		private final String name;

		public int getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		private Usable(int code, String name) {
			this.code = code;
			this.name = name;
		}

		public static Integer getCode(String name) {
			for (Usable e : Usable.values()) {
				if (e.getName().equals(name) ) {
					return e.getCode();
				}
			}
			return null;
		}
		
		public static String getName(int code) {
			for (Usable e :  Usable.values()) {
				if (e.getCode() == code ) {
					return e.getName();
				}
			}
			return null;
		}
		
		public static String toJsonString() {
			List<Map<String,Object>> dataMap = new ArrayList<>();
	        for (Usable e : Usable.values()) {
	            Map<String,Object> map = new HashMap<String,Object>(10);
	            map.put("code", e.getCode());
	            map.put("name", e.getName());
	            dataMap.add(map);
	        }
	        return JsonMapper.toJson(dataMap);
		}
    }
    /**
     * 语言
     * @author jianyiming
     *
     */
    public static enum Language {
        JAVA(1,"java"),
        GO(2,"go"),
        ;
        
        private final int code;
		private final String name;

		public int getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		private Language(int code, String name) {
			this.code = code;
			this.name = name;
		}

		public static Integer getCode(String name) {
			for (Language e : Language.values()) {
				if (e.getName().equals(name) ) {
					return e.getCode();
				}
			}
			return null;
		}
		
		public static String getName(int code) {
			for (Language e :  Language.values()) {
				if (e.getCode() == code ) {
					return e.getName();
				}
			}
			return null;
		}
		
		public static String toJsonString() {
			List<Map<String,Object>> dataMap = new ArrayList<>();
	        for (Language e : Language.values()) {
	            Map<String,Object> map = new HashMap<String,Object>(10);
	            map.put("code", e.getCode());
	            map.put("name", e.getName());
	            dataMap.add(map);
	        }
	        return JsonMapper.toJson(dataMap);
		}
    }
    /**
     * 查询类型
     * @author jianyiming
     *
     */
    public static enum QueryType {
        EQUALS(0,"="),
        NO_EQUALS(1,"!="),
        OPEN_INTERVAL(2,"> or <"),
        CLOSE_INTERVAL(3,">= or <="),
        LIKE(4,"like"),
        LIKE$(5,"like%"),
        ;
        
        private final int code;
		private final String name;

		public int getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		private QueryType(int code, String name) {
			this.code = code;
			this.name = name;
		}

		public static Integer getCode(String name) {
			for (QueryType e : QueryType.values()) {
				if (e.getName().equals(name) ) {
					return e.getCode();
				}
			}
			return null;
		}
		
		public static String getName(int code) {
			for (QueryType e :  QueryType.values()) {
				if (e.getCode() == code ) {
					return e.getName();
				}
			}
			return null;
		}
		
		public static String toJsonString() {
			List<Map<String,Object>> dataMap = new ArrayList<>();
	        for (QueryType e : QueryType.values()) {
	            Map<String,Object> map = new HashMap<String,Object>(10);
	            map.put("code", e.getCode());
	            map.put("name", e.getName());
	            dataMap.add(map);
	        }
	        return JsonMapper.toJson(dataMap);
		}
    }
    
    /**
     * 操作类型
     * @author jianyiming
     *
     */
    public static enum ActionType {
        LOGIN(1,"登录"),
        LOGOUT(2,"退出"),
        ;
        
        private final int code;
		private final String name;

		public int getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		private ActionType(int code, String name) {
			this.code = code;
			this.name = name;
		}

		public static Integer getCode(String name) {
			for (ActionType e : ActionType.values()) {
				if (e.getName().equals(name) ) {
					return e.getCode();
				}
			}
			return null;
		}
		
		public static String getName(int code) {
			for (ActionType e :  ActionType.values()) {
				if (e.getCode() == code ) {
					return e.getName();
				}
			}
			return null;
		}
		
		public static String toJsonString() {
			List<Map<String,Object>> dataMap = new ArrayList<>();
	        for (ActionType e : ActionType.values()) {
	            Map<String,Object> map = new HashMap<String,Object>(10);
	            map.put("code", e.getCode());
	            map.put("name", e.getName());
	            dataMap.add(map);
	        }
	        return JsonMapper.toJson(dataMap);
		}
    }
    
    /**
     * 操作类型
     * @author jianyiming
     *
     */
    public static enum DelFlag {
        NO(0,"正常"),
        YES(1,"删除"),
        ;
        
        private final int code;
		private final String name;

		public int getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		private DelFlag(int code, String name) {
			this.code = code;
			this.name = name;
		}

		public static Integer getCode(String name) {
			for (DelFlag e : DelFlag.values()) {
				if (e.getName().equals(name) ) {
					return e.getCode();
				}
			}
			return null;
		}
		
		public static String getName(int code) {
			for (DelFlag e :  DelFlag.values()) {
				if (e.getCode() == code ) {
					return e.getName();
				}
			}
			return null;
		}
		
		public static String toJsonString() {
			List<Map<String,Object>> dataMap = new ArrayList<>();
	        for (DelFlag e : DelFlag.values()) {
	            Map<String,Object> map = new HashMap<String,Object>(10);
	            map.put("code", e.getCode());
	            map.put("name", e.getName());
	            dataMap.add(map);
	        }
	        return JsonMapper.toJson(dataMap);
		}
    }
}