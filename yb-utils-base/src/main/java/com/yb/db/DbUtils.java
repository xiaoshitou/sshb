package com.yb.db;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.yb.db.model.DataGrid;
import com.yb.local.BeanUtils;

public class DbUtils {
	public static final String HQL = "HQL";
	public static final String PARAMS = "PARAMS";
	public static final String COUNTHQL = "COUNTHQL";
	
	 public static Map buildHql(Object t,String sort,String order){
			Map<String,Object> result= new HashMap<String, Object>();
			Map<String, Object> params = new HashMap<String, Object>();
			String hql="from "+t.getClass().getSimpleName()+" t";
			Map<String, Object> prop = new HashMap<String, Object>();
			BeanUtils.model2Map(t,prop,false);
			
			for (Entry<String, Object> entry : prop.entrySet()) {
				if(entry.getValue()!=null&&!"serialVersionUID".equals(entry.getKey())){
					hql += " where t."+entry.getKey()+" like :"+entry.getKey();
					params.put(entry.getKey(), "%"+entry.getValue()+"%");
				}
			}
			
			String countHql ="select count(*) "+hql;
			if(sort!=null&&order!=null){
				hql += " order by " + sort + " " +order;
			}
			result.put(HQL, hql);
			result.put(COUNTHQL, countHql);
			result.put("PARAMS", params);
			return result;
		}
}
