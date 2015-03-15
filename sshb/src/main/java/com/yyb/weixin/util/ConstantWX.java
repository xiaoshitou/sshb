package com.yyb.weixin.util;

public class ConstantWX {
	/**
	 * 微信开发常量
	 */
	public final static String WEIXIN_TOKEN ="xiaobaicai";
	//baicai190真是号
	/*public final static String APP_ID ="wxd9c541e8aaabe86e";
	public final static String APP_SECRET ="3ac52964fceedb800b00fc826c4021e4";*/
	//测试号
	public final static String APP_ID ="wxcd06219d85f9c6e0";
	public final static String APP_SECRET ="09ee9a9572255bbd78e14c7b5e66e40b";
	
	
	public final static String GET_ACCESS_TOKEN_URL ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+"&secret="+APP_SECRET;
	
	
}
