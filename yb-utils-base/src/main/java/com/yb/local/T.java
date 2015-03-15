package com.yb.local;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class T {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
//		Process process = Runtime.getRuntime().exec("ping -c 2 www.baidu.com ");
//		InputStream in = process.getInputStream(); 
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        
//        String c;  
//        while ((c = reader.readLine()) != null) { 
//         	System.out.println(c);
//        }  
		
		OrderedProperties orderedProperties = new OrderedProperties();
		orderedProperties.load(T.class.getResourceAsStream("test1.properties"));
		//System.out.println(orderedProperties.toString());
		
		Map<String, String> values =orderedProperties.getValueMap();
		for (Entry<String, String> v: values.entrySet()) {
			System.out.println(v.getKey() +":"+v.getValue());
		}
		
	}

}
