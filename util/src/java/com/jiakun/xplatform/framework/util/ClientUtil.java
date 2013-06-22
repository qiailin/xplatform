package com.jiakun.xplatform.framework.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.common.lang.StringUtil;

/**
 * ��ȡ����ͻ�����Ϣ������
 * 
 * @author xiaodan.daixd
 */
public class ClientUtil {

	static Logger logger = Logger.getLogger(ClientUtil.class);

	/**
	 * ��ȡ�ͻ���IP��ַ
	 * 
	 * @param request
	 * @return ip
	 */
	public static String getIpAddr(HttpServletRequest request) {

		String ip = request.getRemoteAddr();
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		return ip;
	}

	/**
	 * �����������ܰ�ȫ�� ������� param1=abc&param2=cde;
	 * 
	 * @param ctrl
	 * @return map
	 */
	public static ConcurrentHashMap<String, String> parseParams(String ctrl) {
		ConcurrentHashMap<String, String> paraMap = new ConcurrentHashMap<String, String>();
		if (ctrl != null) {
			String[] params = ctrl.split("&");
			for (Object p : params) {
				String[] keyValues = p.toString().split("=");
				if (keyValues.length > 2) {
					// logger.error("parse ctrlParams failed! {"+ctrl+"}");
					return paraMap;
				}
				paraMap.put(keyValues[0], keyValues[1]);
			}
		}
		return paraMap;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @param request
	 * @return
	 */
	public static String getReqDomain(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		return getDomainForURI(url);
	}

	/**
	 * 
	 * @param uri
	 *            https://fi.alisoft.com
	 * @return
	 */
	public static String getDomainForURI(String uri) {
		String prefix = ".com";
		int index = uri.indexOf(prefix);
		return uri.substring(0, index + prefix.length());
	}

	/**
	 * IP����ʽ�����ָ�ʽ���ַ�
	 * 
	 * @param ip
	 * @return String 12λ���ֵ��ַ�. �쳣�������"-1"
	 */
	public static double ip2number(String ip) {
		double IPString = 0.0;
		String[] ipblocks = ip.split("\\.");
		double ip1 = 0.0;
		double ip2 = 0.0;
		double ip3 = 0.0;
		double ip4 = 0.0;
		ip1 = Double.parseDouble(ipblocks[0]) * 16777216;
		ip2 = Double.parseDouble(ipblocks[1]) * 65536;
		ip3 = Double.parseDouble(ipblocks[2]) * 256;
		ip4 = Double.parseDouble(ipblocks[3]);
		IPString = ip1 + ip2 + ip3 + ip4;
		return IPString;
	}

	public static boolean isIP(String str) {
		Pattern p = Pattern
				.compile("^([01]?[0-9][0-9]|[01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])\\.([01]?[0-9][0-9]|[01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])\\.([01]?[0-9][0-9]|[01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])\\.([01]?[0-9][0-9]|[01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static boolean comparedUrl(String url1, String url2) {
		String prefix = "://";
		if (StringUtil.isNotBlank(url1) && StringUtil.isNotBlank(url2)) {
			int index1 = url1.indexOf(prefix);
			int index2 = url2.indexOf(prefix);
			String nUrl1 = url1.substring(index1 + prefix.length());
			String nUrl2 = url2.substring(index2 + prefix.length());
			return nUrl1.equals(nUrl2);
		}
		return false;
	}

	public static void sendRedirect(HttpServletResponse response, String url)
			throws IOException {
		try {
			url = new String(url.getBytes("GBK"), "ISO8859_1");
			response.sendRedirect(url);
		} catch (UnsupportedEncodingException e) {
			logger.error("�ַ�ת������" + e);
		}
	}
}
