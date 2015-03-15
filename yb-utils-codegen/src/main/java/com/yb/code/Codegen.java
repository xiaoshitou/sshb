package com.yb.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.yb.code.BeanUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class Codegen {
	public static void main(String[] args) throws Exception {
		Codegen f = new Codegen();
		f.genModule();
	}

	
	/**
	 * 根据Constant里配置的module和entity产生 一套  三层结构
	 */
	public void genModule() {
		genDao();genDaoImpl();genServiceI();genServiceImpl(); genAction();
	}

	public void genDao() {
		for (String entity : Constant.ENTITYNAMES) {
			TaskVO t = new TaskVO();
			t.setFtlName("Dao.ftl");
			t.setGenerateName(entity.substring(0, 1).toUpperCase() + entity.substring(1) + "DaoI.java");
			t.setPackageName(Constant.MODULEPATH + ".dao");// com.yyb.manager.dao
			t.setEntityPath(Constant.MODULEPATH + ".entity");// com.yyb.manager.entity
			t.setModelName("T" + entity);// Tuser
			generateFile(t);
		}
	}

	public void genDaoImpl() {
		for (String entity : Constant.ENTITYNAMES) {
			TaskVO t = new TaskVO();
			t.setFtlName("DaoImpl.ftl");
			t.setGenerateName(entity.substring(0, 1).toUpperCase()+ entity.substring(1) + "DaoImpl.java");// UserDaoImpl.java
			t.setPackageName(Constant.MODULEPATH + ".dao.impl");
			t.setEntityPath(Constant.MODULEPATH + ".entity");
			t.setModelName("T" + entity);
			t.setDaoPath(Constant.MODULEPATH + ".dao");
			generateFile(t);
		}
	}

	public void genServiceI() {
		for (String entity : Constant.ENTITYNAMES) {
			TaskVO t = new TaskVO();
			t.setFtlName("ServerI.ftl");
			t.setGenerateName(entity.substring(0, 1).toUpperCase()+ entity.substring(1) + "ServiceI.java");
			t.setPackageName(Constant.MODULEPATH +".service");
			t.setpModelPath(Constant.MODULEPATH +".pageModel");
			t.setpModelName("P"+entity);
			generateFile(t);
		}
	}

	public void genServiceImpl() {
		for (String entity : Constant.ENTITYNAMES) {
			TaskVO t = new TaskVO();
			t.setFtlName("ServerImpl.ftl");
			t.setGenerateName(entity.substring(0, 1).toUpperCase()+ entity.substring(1) + "ServiceImpl.java");
			t.setPackageName(Constant.MODULEPATH + ".service.impl");
			t.setEntityPath(Constant.MODULEPATH + ".entity");
			t.setpModelPath(Constant.MODULEPATH + ".pageModel");
			t.setModelName("T" + entity);
			t.setpModelName("P" + entity);
			generateFile(t);
		}
	}

	public void genAction() {
		for (String entity : Constant.ENTITYNAMES) {
			TaskVO t = new TaskVO();
			t.setFtlName("Action.ftl");
			t.setGenerateName(entity.substring(0, 1).toUpperCase()+ entity.substring(1) + "Action.java");
			t.setPackageName(Constant.MODULEPATH + ".action");
			t.setEntityPath(Constant.MODULEPATH + ".entity");
			t.setpModelPath(Constant.MODULEPATH + ".pageModel");
			t.setModelName("T" + entity);
			t.setpModelName("P" + entity);
			generateFile(t);
		}
	}

	/**
	 * ftl 必须放在src/main/resources目录下
	 * 
	 * @param param
	 *            <generateName ftlName root>
	 */
	private  void generateFile(TaskVO t) {
		if (t.getGenerateName() == null || t.getFtlName() == null) {
			System.err
					.println("oh!lack of some params  that is must,Be sure 'generateName' 'ftlName' is passed");
			return;
		}
		// 生产模板需要的参数
		Map<String, Object> param = BeanUtils.model2Map(t);
		Configuration cfg = new Configuration();
		Writer fileWriter = null;
		
		// Some other recommended settings:
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		try {
			//输出文件路径和文件，如果不存在就创建
			String packagePath = Constant.GENERATEDIR+t.getPackageName().replaceAll("\\.", File.separator)+File.separator;
			File dir = new File(packagePath);
			if (!(dir.isDirectory() && dir.exists())) {
				dir.mkdirs();
			}
			File outputFile = new File(packagePath+ param.get("generateName").toString());
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			//获取模板，生成输出文件
			cfg.setDirectoryForTemplateLoading(new File(Constant.RESOURCESDIR));
			Template template = cfg.getTemplate(param.get("ftlName").toString());
			fileWriter = new FileWriter(outputFile);
			template.process(param, fileWriter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
