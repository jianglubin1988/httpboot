package com.sjs.utils;

import java.util.UUID;

/**
 * UUID工具类
 * @author robin
 *
 */
public class UIDUtils {

	/**
	 * 获取32位唯一ID
	 * @return
	 */
	public static String getUid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
