package com.yyb.manager.dao.impl;


import org.springframework.stereotype.Repository;

import com.yb.db.dao.impl.BaseDaoImpl;
import com.yyb.manager.dao.UserDaoI;
import com.yyb.manager.entity.Tuser;
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<Tuser> implements UserDaoI {

	

}
