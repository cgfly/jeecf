package org.jeecf.manager.engine.utils;

import org.jeecf.manager.engine.exception.SqlJniException;
/**
 * sql jni valiedate
 * @author jianyiming
 *
 */
public class JniValidate {

	private static String[] SPECIAL_CHAR = {"--", ";","(",")"};
	
	public static String columnValidate(String field) {
		  if(field.contains(" ")) {
			  throw new SqlJniException("sql jni column validate not pass");
		  }
		  for(int i = 0; i < SPECIAL_CHAR.length;i++) {
			  if(field.equals(SPECIAL_CHAR[i])) {
				  throw new SqlJniException("sql jni column validate not pass");
			  }
		  }
		  return field;
	}

}
