package com.yb.local;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordUtil {
	public static String md5Password(String password)
	  {
	    return new Md5Hash(password).toHex();
	  }

	
	  public static String md5Password(String password, String salt) {
	    return new Md5Hash(password, salt).toHex();
	  }

	  public static String randomPassword()
	  {
	    char[] pwdchs = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
	      'o', 'p', 'q', 'r', 's', 'd', 'u', 'v', 'w', 'x', 'y', 'z', 
	      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 
	      'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
	      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	    Random rand = new Random();
	    String pwd = "";

	    for (int j = 0; j < 6; j++) {
	      pwd = pwd + pwdchs[rand.nextInt(62)];
	    }
	    return pwd;
	  }
	  
	  public static String getSHA1Pwd(String content){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.getBytes());
			return  byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	  }
	  
	
	/**
	* 将字节数组转换为十六进制字符串
	*
	* @param bytearray
	* @return
	*/
	private static String byteToStr(byte[] bytearray) {
		String strDigest = "";
		for (int i = 0; i < bytearray.length; i++) {
		strDigest += byteToHexStr(bytearray[i]);
		}
		return strDigest;
	}
	/**
	* 将字节转换为十六进制字符串
	*
	* @param ib
	* @return
	*/
	private static String byteToHexStr(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
		'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}
	  
}
