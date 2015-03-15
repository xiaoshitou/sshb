package com.yyb.manager.service;

import com.yyb.manager.entity.Tuser;
import com.yyb.manager.pageModel.DataGrid;

public interface UserManagerI {
	public Tuser add(Tuser user);

	public void remove(Tuser user);

	public Tuser login(Tuser user);

	public DataGrid datagrid(Tuser Tuser);

	public Tuser edit(Tuser Tuser);
}
