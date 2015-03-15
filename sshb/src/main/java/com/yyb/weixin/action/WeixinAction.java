package com.yyb.weixin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.yyb.weixin.business.BaseBusiness;
import com.yyb.weixin.util.CommonUtilWX;

@Action(value = "weixinAction")
public class WeixinAction  extends BaseActionWX {
	//http://localhost/sshe/weixinAction!checkSignature.action?signature="a"&timestamp="123"&nonce="b"&echostr="yyb"
	Log log =LogFactory.getLog(WeixinAction.class);
	
	public void coreAction(){
		HttpServletResponse response =ServletActionContext.getResponse();
		HttpServletRequest request =ServletActionContext.getRequest();
		PrintWriter out=null;
		try {
			request.setCharacterEncoding("UTF-8");  
		    response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			String echostr = request.getParameter("echostr");
			log.info("request.getMethod() is["+request.getMethod()+"]");
			if("GET".equals(request.getMethod())){
				if(CommonUtilWX.checkSignature()){
					out.write(echostr);
				}
			}else if("POST".equals(request.getMethod())){
				// 调用核心业务类接收消息、处理消息  
		        String respMessage = BaseBusiness.processRequest(request);  
		        // 响应消息  
		        out.print(respMessage);  
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		
	}
	
}
