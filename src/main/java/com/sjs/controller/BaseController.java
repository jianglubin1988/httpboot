package com.sjs.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sjs.utils.JsonUtils;

/**
 * @author robin
 */
public class BaseController {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(BaseController.class);

	/**
	 * http 公共响应体
	 * 
	 * @param obj
	 * @return
	 */
	protected String getResponseBody(Object obj) {
		try {
			HttpServletResponse response = getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(obj);
			out.flush();
			out.close();
		} catch (Exception e) {
		}

		return null;
	}

	/**
	 * 解析请求的Json数据并转换为Map
	 * 
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> getJsonParam() throws Exception {
		HttpServletRequest request = getRequest();
		int contentLength = request.getContentLength();
		log.info("Content-type: " + request.getContentType());
		log.info("JSON长度：" + contentLength);
		if (contentLength < 0) {
			throw new Exception("捕获异常：参数为空！");
		}
		byte buffer[] = new byte[contentLength];
		int len = request.getInputStream().read(buffer);
		if (len == -1) {
			throw new Exception("捕获异常：数据流读取失败！");
		}
		String params = new String(buffer, "utf-8");
		log.info("JSON参数：" + params);
		if (StringUtils.isEmpty(params)) {
			throw new Exception("捕获异常：参数解析失败！");
		}
		return JsonUtils.jsonToMap(params);
	}

	/**
	 * 获取http请求参数
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	protected String getParam(String key) throws Exception {
		String param = this.getRequest().getParameter(key);
		if (StringUtils.isEmpty(param)) {
			throw new Exception("捕获异常：参数" + key + " 值为空！");
		}
		return param;
	}

	/**
	 * 根据getJsonParam()接口返回的map获取http请求参数
	 * 
	 * @param map
	 * @param key
	 * @return
	 * @throws Exception
	 */
	protected String getParam(Map<String, Object> map, String key) throws Exception {
		if (!map.containsKey(key)) {
			throw new Exception("捕获异常：参数JSON中没有找到" + key);
		}
		String param = map.get(key).toString();
		if (StringUtils.isEmpty(param)) {
			throw new Exception("捕获异常：参数" + key + " 值为空！");
		}
		return param;
	}

	protected HttpServletRequest getRequest() throws Exception {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		request.setCharacterEncoding("UTF-8");
		return request;
	}

	protected HttpServletResponse getResponse() throws Exception {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletResponse response = sra.getResponse();
		response.setCharacterEncoding("UTF-8");
		return response;
	}

	public String succString(Object obj) {
		return JsonUtils.mapToJson(this.getSuccMap(obj));
	}

	public String failString(Object obj) {
		return JsonUtils.mapToJson(this.getFailMap(obj));
	}

	public String otherString(Object obj, String code, String msg) {
		return JsonUtils.mapToJson(this.getOtherMap(obj, code, msg));
	}

	private Map<String, Object> getSuccMap(Object obj) {
		return this.getOtherMap(obj, "0", "成功");
	}

	private Map<String, Object> getFailMap(Object obj) {
		return this.getOtherMap(obj, "1", "失败");
	}

	private Map<String, Object> getOtherMap(Object obj, String code, String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("msg", msg);
		result.put("data", obj);
		return result;
	}
}
