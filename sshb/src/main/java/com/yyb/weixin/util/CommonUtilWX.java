package com.yyb.weixin.util;

import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.yb.local.PasswordUtil;
import com.yb.local.StringUtil;

public class CommonUtilWX {
	static Log log =LogFactory.getLog(CommonUtilWX.class);
	/**
	 * 检验是否是来自微信服务器
	 * 结果0-来自微信检验，已返回echostr给微信，开发者不需要在做业务上的返回
	 * 1-来自用户提交，并安全检验通过，开发者需要做业务上的返回
	 * 2-来自恶意请求，弄他
	 */
	public static boolean checkSignature(){
		HttpServletResponse response =ServletActionContext.getResponse();
		HttpServletRequest request =ServletActionContext.getRequest();
		PrintWriter out =null ;       
		try {
			out=response.getWriter();
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			// 对token、timestamp 和nonce 按字典排序
			String[] paramArr = new String[] {ConstantWX.WEIXIN_TOKEN, timestamp, nonce };
			if(paramArr.length>0&&paramArr[1]!=null&&paramArr[2]!=null){
				Arrays.sort(paramArr);
				// 将排序后的结果拼接成一个字符串
				String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
				log.info("sort str from weixin:"+content);
				String ciphertext = PasswordUtil.getSHA1Pwd(content);
				if(ciphertext.equalsIgnoreCase(signature)&&!StringUtil.isblankSpace(echostr)){
					log.info("checkSignature success "+echostr);
					//out.write(echostr);
					return true;
				}else{
					return false;
				}
			}else{
				log.info("checkSignature fail,signature:["+signature+"] timestamp:"+timestamp+"] nonce:["+nonce+"]");
			}
	          
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.flush();  
		        out.close(); 
			}
			
		}
		return false;
	}
	
	/**
	 * 获取解析后的字符串 
	 * @param eg:grant_type=client_credential&appid=appid&secret=secret"
	 * @param var new String[]{"qq","1234567","asdaffiihi12b"}
	 * @return grant_type=client_credential&appid=1234567&secret=asdaffiihi12b"
	 * 只替换变量和值相等的参数 、参数数组必须和变量个数一致
	 */
	public static String getStr(String constant,String[] var){
		   if(var==null||var.length==0){
			   return constant;
		   }
		   StringBuffer result = new StringBuffer(constant.substring(0, constant.indexOf("?")+1));
		   String paramStr = constant.substring(constant.indexOf("?")+1, constant.length());
		   String[] prarmArr =paramStr.split("&");
		   if(prarmArr.length!=var.length){
			   return constant;
		   }
		   //grant_type=client_credential&appid=APPID&secret=APPSECRET"
		   for (int i=0;i< prarmArr.length ;i++) {
			 String[] paramItem =  prarmArr[i].split("=");
			 if(paramItem[0].equals(paramItem[1])){
				 result.append(paramItem[0]).append("=").append(var[i]).append("&");
			 }else{
				 result.append(prarmArr[i]).append("&");
			 }
		   }
		   return result.substring(0, result.length()-1);
	   }

}
