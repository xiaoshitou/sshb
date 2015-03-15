package com.yyb.manager.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.local.BeanUtils;
import com.yyb.manager.dao.MenuDaoI;
import com.yyb.manager.entity.Tmenu;
import com.yyb.manager.pageModel.Pmenu;
import com.yyb.manager.service.MenuManagerI;


@Service("menuManager")
@Transactional
public class MenuManagerImpl implements MenuManagerI {

	@Autowired
	private MenuDaoI menuDao;
	@Override
	public Serializable add(Pmenu pmenu) {
		Tmenu menu = new Tmenu();
		menu.setId(UUID.randomUUID().toString());
		menu.setText("root");
		return menuDao.save(menu);
	}

	@Override
	public void remove(Pmenu pmenu) {
		Tmenu menu = new Tmenu();
		BeanUtils.copyProperties(pmenu, menu);
		menuDao.delete(menu);		
	}

	@Override
	public void uodate(Pmenu pmenu) {
		Tmenu menu = new Tmenu();
		BeanUtils.copyProperties(pmenu, menu);
		menuDao.delete(menu);		
	}

	/*
	 * 异步加载(non-Javadoc)
	 * @see sy.service.MenuManagerI#getTree(java.lang.String)
	 */
	@Override
	public List<Pmenu> getTree(String pid) {
		List<Pmenu> list=new ArrayList<Pmenu>();
		String hql="";
		Map<String,Object> params = new HashMap<String,Object>();
		if(pid==null||"".equals(pid)){
			hql="from Tmenu where pid is null";
		}else{
			hql ="from Tmenu where pid =:pid";
			params.put("pid", pid);
		}
		List<Tmenu> l=menuDao.find(hql, params);
		if(l!=null&&l.size()>0){
			for (Tmenu tmenu : l) {
				Pmenu p = new Pmenu();
				BeanUtils.copyProperties(tmenu, p);
				List<Tmenu> sets= tmenu.getTmenus();
				if(sets!=null&&sets.size()>0){
					p.setState("closed");
				}else{
					p.setState("open");
				}
				list.add(p);
			}
		}
		
		return list;
	}
	
	public List<Pmenu> getAllTreeNode() {
		List<Pmenu> list=new ArrayList<Pmenu>();
		String hql="from Tmenu";
		List<Tmenu> l=menuDao.find(hql);
		Map<String,Object> attributes;
		if(l!=null&&l.size()>0){
			for (Tmenu tmenu : l) {
				Pmenu p = new Pmenu();
				BeanUtils.copyProperties(tmenu, p);
				Tmenu t=tmenu.getTmenu();//父节点
				if(t!=null){
					p.setParentId(tmenu.getTmenu().getId());
				}
				attributes = new HashMap<String, Object>();
				attributes.put("url", tmenu.getUrl());
				p.setAttributes(attributes);
				list.add(p);
			}
		}
		return list;
	}

}
