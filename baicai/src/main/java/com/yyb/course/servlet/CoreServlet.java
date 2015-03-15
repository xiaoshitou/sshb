package com.yyb.course.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yyb.course.business.BaseBusiness;
import com.yyb.course.util.CommonUtilWX;


/**
 * @author yubin
 */
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 验证签名 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将token、timestamp、nonce三个参数进行字典序排序  
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		if (CommonUtilWX.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}else{
			out.print("get token failed");
		}
		out.close();
		out = null;
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
       // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
    	// 调用核心业务类接收消息、处理消息  
        String respMessage = BaseBusiness.processRequest(request); 
        // 响应消息  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close();  
	}

}

