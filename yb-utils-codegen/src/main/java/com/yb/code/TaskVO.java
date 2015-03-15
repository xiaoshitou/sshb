package com.yb.code;

import java.util.HashMap;
import java.util.Map;

public class TaskVO {
	/**
	 * ftl 需要的变量
	 */
	private String packageName;// 包路径
	private String modulePath;// 模块路径
	

	private String daoPath;// dao路径
	private String entityPath;// 实体类路径
	private String modelName;// 实体类名字
	private String pModelPath;// 页面类路径
	private String pModelName;// 页面类名字


	

	/**
	 * 产生文件需要的参数
	 */
	private String ftlName;// 模板名字
	private String generateName;// 输出的文件名
	

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getModulePath() {
		return modulePath;
	}

	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}

	public String getDaoPath() {
		return daoPath;
	}

	public void setDaoPath(String daoPath) {
		this.daoPath = daoPath;
	}

	public String getEntityPath() {
		return entityPath;
	}

	public void setEntityPath(String entityPath) {
		this.entityPath = entityPath;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getpModelPath() {
		return pModelPath;
	}

	public void setpModelPath(String pModelPath) {
		this.pModelPath = pModelPath;
	}

	public String getpModelName() {
		return pModelName;
	}

	public void setpModelName(String pModelName) {
		this.pModelName = pModelName;
	}

	public String getFtlName() {
		return ftlName;
	}

	public void setFtlName(String ftlName) {
		this.ftlName = ftlName;
	}

	public String getGenerateName() {
		return generateName;
	}

	public void setGenerateName(String generateName) {
		this.generateName = generateName;
	}
	

}
