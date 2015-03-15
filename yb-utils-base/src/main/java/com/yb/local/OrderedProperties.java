package com.yb.local;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 
 * @author yyb
 */
public class OrderedProperties {

	/** Keys*/
	private List<String> keys = new ArrayList<String>();
	

	/** ValueMap
	 * LinkedHashMap 按放入的顺序输出
	 * TreeMap默认的顺序  也可以指定排序的比较器
	 * HashMap  无序
	 */
	private Map<String, String> valueMap = new LinkedHashMap<String, String>();

	public String getProperty(String key) {
		return valueMap.get(key);
	}

	public List<String> getKeys(String keyPattern) {
		Pattern pat = Pattern.compile(keyPattern);
		List<String> kl = new ArrayList<String>();
		for (String k : keys) {
			if (pat.matcher(k).matches()) {
				kl.add(k);
			}
		}
		return kl;
	}

	/**
	 * 加载Properties文件
	 * @param istream
	 * @throws Exception
	 * @see amosryan.util.IOUtils
	 */
	public synchronized void load(InputStream istream) throws Exception {
		List<String> lines = IOUtils.toLines(istream);
		
		// parse key-value
		for (String l : lines) {
			if (l.trim().startsWith("#")) {
				keys.add(l);
			} else {
				if (l.indexOf("=") > -1) {
					String k = l.substring(0, l.indexOf("=")).trim();
					String v = l.substring(l.indexOf("=") + 1).trim();
					keys.add(k);
					valueMap.put(k, v);
				} else {
					keys.add(l);
				}
			}
		}
	}

	public List<String> getKeys() {
		return keys;
	}
	public Map getValueMap() {
		return valueMap;
	}

	@Override
	public String toString() {
		return valueMap.toString();
	}

}
