package com.yyb.manager.action.user;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.yyb.manager.action.BaseAction;
import com.yyb.manager.entity.Tuser;
import com.yyb.manager.pageModel.DataGrid;
import com.yyb.manager.pageModel.Message;
import com.yyb.manager.service.UserManagerI;
import com.yyb.manager.util.Constant;



@Action(value = "userAction")
public class UserAction  extends BaseAction implements ModelDriven<Tuser> {
	@Autowired
    private UserManagerI userManagerI;
	
	Log log =LogFactory.getLog(UserAction.class);
	
	private Tuser tuser = new Tuser();
	
	public void add(){
		
		Tuser s;
		try {
			s =  userManagerI.add(tuser);
			super.writeJson(new Message("0000","注册成功",s));
		} catch (Exception e) {
			super.writeJson(new Message("9999",e.getMessage()));
		}
	
	}
	public void edit(){
		
		Tuser s;
		try {
			s =  userManagerI.edit(tuser);
			super.writeJson(new Message("0000","编辑成功",s));
		} catch (Exception e) {
			super.writeJson(new Message("9999",e.getMessage()));
		}
		
	}
	public void remove(){
		try {
			 userManagerI.remove(tuser);
			super.writeJson(new Message("0000","删除成功！"));
			
		} catch (Exception e) {
			super.writeJson(new Message("9999",e.getMessage()));
		}
	}
	public void login(){
		Tuser u =userManagerI.login(tuser);
		if(u!=null){
			log.info("user:"+tuser.getName()+";pwd:"+tuser.getPwd());
			super.writeJson(new Message("0000","登录成功"));
		}else{
			super.writeJson(new Message("9999","111"));
			
		}
		
	}
	
	public void datagrid(){
	
		try {
			DataGrid d= userManagerI.datagrid(tuser);
			super.writeJson(d);
		} catch (Exception e) {
			super.writeJson(new Message("9999",e.getMessage()));
		}
		
	}
	@Override
	public Tuser getModel() {
		// TODO Auto-generated method stub
		return tuser;
	}
	
}
