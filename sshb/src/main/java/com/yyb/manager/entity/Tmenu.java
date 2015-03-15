package com.yyb.manager.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.yyb.manager.pageModel.DataModel;
import com.yyb.manager.pageModel.Pmenu;

/**
 * Tmenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TMENU", catalog = "sshe")
public class Tmenu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Tmenu tmenu;
	private String text;
	private String iconCls;
	private String url;
	private List<Tmenu> tmenus = new ArrayList<Tmenu>();

	// Constructors

	/** default constructor */
	public Tmenu() {
	}

	/** minimal constructor */
	public Tmenu(String id) {
		this.id = id;
	}

	/** full constructor */
	public Tmenu(String id, Tmenu tmenu, String text, String iconCls,
			String url, List<Tmenu> tmenus) {
		this.id = id;
		this.tmenu = tmenu;
		this.text = text;
		this.iconCls = iconCls;
		this.url = url;
		this.tmenus = tmenus;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public Tmenu getTmenu() {
		return this.tmenu;
	}

	public void setTmenu(Tmenu tmenu) {
		this.tmenu = tmenu;
	}

	@Column(name = "TEXT", length = 100)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "ICONCLS", length = 50)
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "URL", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tmenu")
	public List<Tmenu> getTmenus() {
		return this.tmenus;
	}

	public void setTmenus(List<Tmenu> tmenus) {
		this.tmenus = tmenus;
	}

}