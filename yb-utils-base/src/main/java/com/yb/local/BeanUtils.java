package com.yb.local;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

public class BeanUtils extends org.springframework.beans.BeanUtils {
	/**
	 * 
	 * @param obj 传入的对象实例 、result存放结果的map 、isCascade 是否将父类属性读出来
	 * @return 将对象的属性放入map中
	 * @throws Exception
	 */
	public static void model2Map(Object obj,Map<String, Object> result,Boolean isCascade) {
		Class class1 = obj.getClass();
		if(isCascade){
			Class superCls = class1.getSuperclass();
			//递归查询父类 
			if(superCls!=null&&!superCls.getName().equals("java.lang.Object")){
				try {
					Object superOj = superCls.newInstance();
					model2Map(superOj,result,true);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
			}
		}
		Field[] fields = class1.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				result.put(field.getName(), field.get(obj) == null ? null : field.get(obj).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void model2Map(List objs,List<Map<String, Object>> results,Boolean isCascade) {
		for (Object obj : objs) {
			Map<String, Object> result = new HashMap<String, Object>();
			model2Map(obj,result,isCascade);
			if(!CollectionUtils.isEmpty(result)){
				results.add(result);
			}
			
		}
	}

}
