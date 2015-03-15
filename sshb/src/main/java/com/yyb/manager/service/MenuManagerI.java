package com.yyb.manager.service;
import java.io.Serializable;
import java.util.List;

import com.yyb.manager.pageModel.Pmenu;

public interface MenuManagerI {
	   public Serializable add(Pmenu user);
	   public void remove(Pmenu user);
	   public void uodate(Pmenu user);
	   public List<Pmenu> getTree(String pid);
	   public List<Pmenu> getAllTreeNode();
}
