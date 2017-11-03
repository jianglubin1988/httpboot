package com.sjs.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * 获取kafka配置信息（该类暂时未用）
 * @author robin
 *
 */
public class LogProperties {
	
	private final static String RESOURCE = "/log4kafka.properties";
	
	public Properties getProp() {
		Properties prop = new Properties();
		try {
			InputStream in = getClass().getResourceAsStream(RESOURCE);
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public String getByKey(String key) {
		return getProp().getProperty(key).trim();
	}
	
	public static void main(String[] args) {
		LogProperties lp = new LogProperties();
		String s = lp.getByKey("kafka.value.serializer");
		System.out.println(s);
	}
}
