package com.yyb.weixin.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * 获取Token线程
 * 单例模式
 * @author Alex
 *
 */
public class GetTokenThread {
	
	Log log =LogFactory.getLog(GetTokenThread.class);
	private String token;
	private String expires;
	
	private static GetTokenThread instance;
	
	private GetTokenThread(){}
	
	public static synchronized GetTokenThread getInstance(){          
        if(instance == null){          
            return instance = new GetTokenThread();          
         }else{          
            return instance;          
         }          
	}    
	
	/**
	 * 发送get请求  从微信服务器 获取token
	 */
	public void observerToken(){
		 String jsonResult = HttpHelperBean.sendGet(ConstantWX.GET_ACCESS_TOKEN_URL,null);
		 JSONObject o= JSON.parseObject(jsonResult);
		 this.setToken(o.getString("access_token"));
		 this.setExpires(o.getString("expires_in"));
		 
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 log.info("get token time is:["+df.format(new Date())+"]"+"token:["+token+"]");
	}

   
	public String getToken() {
		return token;
	}

	private void setToken(String token) {
		this.token = token;
	}

	public String getExpires() {
		if(expires==null){
			expires="7000";
		}
		return expires;
	}

	private void setExpires(String expires) {
		this.expires = expires;
	}
	
	
}
