package com.yyb.weixin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;
import com.yb.local.StringUtil;

@ParentPackage("basePackageWX")
@Namespace("/")
public class BaseActionWX{
	
	
	public void writeJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			HttpServletResponse response =ServletActionContext.getResponse();
			HttpServletRequest request =ServletActionContext.getRequest();
			PrintWriter out = response.getWriter();       
			response.setCharacterEncoding("utf8");
			request.setCharacterEncoding("utf8");
			response.setContentType("text/html;charset=utf8");
	        response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        String jsonpCallback = request.getParameter("callback");//客户端跨域请求参数 
	        //判断是否是跨域请求
	        if(StringUtil.isblankSpace(jsonpCallback)){
	        	out.write(json);
	        }else{
	        	out.println(jsonpCallback+"("+json+")");
	        }
	        out.flush();  
	        out.close();  
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
