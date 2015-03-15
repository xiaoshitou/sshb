package com.yb.local;

import org.springframework.util.StringUtils;

public class StringUtil extends StringUtils{

	public static boolean isblankSpace(String string){
		if(string==null||string.length()==0){
			return true;
		}
		return false;
	}
}
