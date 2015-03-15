package com.yyb.manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.yyb.manager.pageModel.DataGrid;
import com.yyb.manager.pageModel.DataModel;

@Entity
@Table(name = "TUSER", schema = "SSHE", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Tuser extends DataModel implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String pwd;
	private String createdatetime;
	private String modifydatetime;
	
	
	// Constructors

	/** default constructor */
	public Tuser() {
	}

	/** minimal constructor */
	public Tuser(String id, String name, String pwd) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}

	/** full constructor */
	public Tuser(String id, String name, String pwd, String createdatetime, String modifydatetime) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.createdatetime = createdatetime;
		this.modifydatetime = modifydatetime;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PWD", nullable = false, length = 36)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "CREATEDATETIME", length = 20)
	public String getCreatedatetime() {
		return this.createdatetime;
	}

	public void setCreatedatetime(String createdatetime) {
		this.createdatetime = createdatetime;
	}

	@Column(name = "MODIFYDATETIME", length = 20)
	public String getModifydatetime() {
		return this.modifydatetime;
	}

	public void setModifydatetime(String modifydatetime) {
		this.modifydatetime = modifydatetime;
	}


}