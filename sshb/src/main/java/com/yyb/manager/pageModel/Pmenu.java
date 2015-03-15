package com.yyb.manager.pageModel;
import java.util.Map;

/**
 * 页面tree控件所需的参数  不能继承，只能赋值给该类，以后优化--20141229
 * @author yyb
 *
 */
public class Pmenu implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String parentId;
	private String ptext;
	private String state;
	private Map<String ,Object> attributes ;
	private String text;
	private String iconCls;
	private String url;
	
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getPtext() {
		return ptext;
	}
	public void setPtext(String ptext) {
		this.ptext = ptext;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
