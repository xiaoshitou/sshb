package com.yb.code;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 class BeanUtils extends org.springframework.beans.BeanUtils {
	/**
	 * 
	 * @param obj 传入的对象实例
	 * @return 将对象的属性放入map中
	 * @throws Exception
	 */
	public static Map<String, Object> model2Map(Object obj) {
		Map<String, Object> result = new HashMap<String, Object>();
		Class class1 = obj.getClass();
		Field[] fields = class1.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				result.put(field.getName(), field.get(obj) == null ? null : field.get(obj).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public static List<Map<String, Object>> model2Map(List objs) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for (Object obj : objs) {
			result.add(model2Map(obj)) ;
		}
		return result;
	}

}
