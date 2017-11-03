package com.sjs.utils;

import java.util.Map;

import net.sf.json.JSONObject;

/**
 * json工具类
 * @author robin
 *
 */
public class JsonUtils {

//	public static Map<String, Object> json2Map(String json) {
//		return JSON.parseObject(json, Map.class);
//	}
//
//	public static String obj2JsonString(Object obj) {
//		return JSON.toJSONString(obj);
//	}
	
	/**
     * json string 转换为 map 对象
     * @param jsonObj
     * @return
     */
    public static Map<String, Object> jsonToMap(Object jsonObj) {
        JSONObject jsonObject = JSONObject.fromObject(jsonObj);
        Map<String, Object> map = (Map)jsonObject;
        return map;
    }

    /**
     * json string to object
     * @param jsonObj
     * @param type
     * @return
     */
    public static <T> T jsonToBean(Object jsonObj, Class<T> type) {
        JSONObject jsonObject = JSONObject.fromObject(jsonObj);
        T obj =(T)JSONObject.toBean(jsonObject, type);
        return obj;
    }
    
    /**
     * Map to json string
     * @param jsonObj
     * @param type
     * @return
     */
    public static String mapToJson(Map map) {
    	return JSONObject.fromObject(map).toString();
    }
}
