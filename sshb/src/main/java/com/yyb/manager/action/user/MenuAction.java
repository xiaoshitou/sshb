package com.yyb.manager.action.user;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.yyb.manager.action.BaseAction;
import com.yyb.manager.entity.Tmenu;
import com.yyb.manager.pageModel.Message;
import com.yyb.manager.pageModel.Pmenu;
import com.yyb.manager.service.MenuManagerI;



@Action(value = "menuAction")
public class MenuAction extends BaseAction  implements ModelDriven<Pmenu>  {
	
	@Autowired
	MenuManagerI menuManagerI;
    private Pmenu menu =new Pmenu();
	public void getTree() {

		List<Pmenu> s;
		try {
			s=menuManagerI.getAllTreeNode();
			super.writeJson(s);
		} catch (Exception e) {
			super.writeJson(new Message("9999", e.getMessage()));
		}

	}

	@Override
	public Pmenu getModel() {
		// TODO Auto-generated method stub
		return menu;
	}
}
