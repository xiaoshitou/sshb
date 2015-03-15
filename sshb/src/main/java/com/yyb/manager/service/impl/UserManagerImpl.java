package com.yyb.manager.service.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.yb.db.DbUtils;
import com.yb.local.BeanUtils;
import com.yb.local.DateUtil;
import com.yb.local.PasswordUtil;
import com.yyb.manager.dao.UserDaoI;
import com.yyb.manager.entity.Tuser;
import com.yyb.manager.pageModel.DataGrid;
import com.yyb.manager.service.UserManagerI;


@Service("userService")
@Transactional
public class UserManagerImpl  implements UserManagerI {
	private static final Logger logger = Logger.getLogger(UserManagerImpl.class);
  
	
	@Autowired
	private UserDaoI userDao;
	

	@Override
	public Tuser  add(Tuser user) {
		user.setId(UUID.randomUUID().toString());
		user.setCreatedatetime(DateUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		user.setPwd(PasswordUtil.md5Password(user.getPwd()));//加密密码
		userDao.save(user);
		return user;
		
	}

	@Override
	public void remove(Tuser user) {
		logger.info("remove");
		String[] nids = user.getIds().split(",");
		String hql = "delete Tuser t where t.id in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		userDao.executeHql(hql);
	}

	@Override
	public Tuser login(Tuser user) {
		logger.info("login");
		if(user.getName()!=null&&user.getPwd()!=null){
			String hql="from Tuser where name = '"+user.getName()+"' and pwd = '"+PasswordUtil.md5Password(user.getPwd())+"'";
			return userDao.get(hql);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public DataGrid datagrid(Tuser tuser) {
		System.out.println("tuser.getPage()"+tuser.getPage());
		DataGrid d = new DataGrid();
		Map map = DbUtils.buildHql(tuser,tuser.getSort(),tuser.getOrder());
	    List<Tuser> l=userDao.find(map.get(DbUtils.HQL).toString(),(Map)map.get(DbUtils.PARAMS), tuser.getPage(),tuser.getRows());
	    d.setRows(l);
	    d.setTotal(userDao.count(map.get(DbUtils.COUNTHQL).toString(),(Map)map.get(DbUtils.PARAMS)));
		return d;
	}

	@Override
	public Tuser edit(Tuser user) {
		Tuser t= userDao.get(Tuser.class, user.getId());
		user.setPwd(PasswordUtil.md5Password(user.getPwd()));
		BeanUtils.copyProperties(user, t,new String[]{"id,pwd"});
		return user;
	}

}
