package com.jiakun.xplatform.framework.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.common.lang.StringUtil;

/**
 * 
 * @author
 * 
 */
public final class SplitString {

	private SplitString() {

	}

	/**
	 * 
	 * @param ids
	 * @return
	 */
	public static String[] getRoleIds(String ids) {
		if (ids == null) {
			return new String[0];
		}
		return ids.split(",");
	}

	/**
	 * 
	 * @param roleIds
	 * @return
	 */
	public static String joinIds(String[] roleIds) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < roleIds.length; i++) {
			if (i == 0) {
				sb.append(roleIds[i]);
			} else {
				sb.append(",");
				sb.append(roleIds[i]);
			}
		}
		return sb.toString();
	}

	public static String buildBooksiteIdCacheKey(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append("booksiteId_").append(id);
		return sb.toString();
	}

	public static String parseCacheKey(String key) {
		if (StringUtil.isBlank(key)) {
			return null;
		}
		int index = key.lastIndexOf('_');
		return key.substring(index + 1);
	}

	/**
	 * 
	 * @param ctrl
	 * @return
	 */
	public static Map<String, String> parseCtrlParams(String ctrl) {
		Map<String, String> paraMap = new HashMap<String, String>();
		if (ctrl != null) {
			String[] params = ctrl.split("&");
			for (Object p : params) {
				String[] keyValues = p.toString().split("=");
				if (keyValues.length > 2 || keyValues.length < 2) {
					continue;
				}
				paraMap.put(keyValues[0], keyValues[1]);
			}
		}
		return paraMap;
	}
	
}
