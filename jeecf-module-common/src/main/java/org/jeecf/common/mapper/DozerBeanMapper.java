package org.jeecf.common.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * 扩展  org.dozer.DozerBeanMapper类 
 * 
 * @author GloryJian
 *
 */
public class DozerBeanMapper extends org.dozer.DozerBeanMapper{
	
	
   private static DozerBeanMapper dozer = null;
   
   
   public static DozerBeanMapper getInstance() {
	   if(dozer == null) {
		   dozer = new DozerBeanMapper();
	   }
	   return dozer;
   }
   

	/**
	 * 基于Dozer转换对象的类型.
	 */
	public static <T> T copy(Object source, Class<T> destinationClass) {
		return DozerBeanMapper.getInstance().map(source, destinationClass);
	}

	/**
	 * 基于Dozer将对象A的值拷贝到对象B中.
	 */
	public static void copy(Object source, Object destinationObject) {
		DozerBeanMapper.getInstance().map(source, destinationObject);
	}
	
	/**
	 * 基于Dozer转换Collection中对象的类型.
	 */
	@SuppressWarnings("rawtypes")
	public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
		List<T> destinationList = new ArrayList<T>();
		for (Object sourceObject : sourceList) {
			T destinationObject = DozerBeanMapper.getInstance().map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	
   
}
