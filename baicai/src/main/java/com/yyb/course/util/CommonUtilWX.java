package com.yyb.course.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;





public class CommonUtilWX {
	static Log log =LogFactory.getLog(CommonUtilWX.class);
	/**
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] paramArr = new String[] { ConstantWX.WEIXIN_TOKEN, timestamp, nonce };
		if(paramArr.length>0&&paramArr[1]!=null&&paramArr[2]!=null){
			Arrays.sort(paramArr);
			// 将排序后的结果拼接成一个字符串
			String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
			MessageDigest md = null;
			String tmpStr = null;
			try {
				md = MessageDigest.getInstance("SHA-1");
				byte[] digest = md.digest(content.toString().getBytes());
				tmpStr = byteToStr(digest);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			content = null;
			return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
		}else{
			log.info("checkSignature fail,signature:["+signature+"] timestamp:"+timestamp+"] nonce:["+nonce+"]");
		}
		return false;
	}

	/**
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}
}
