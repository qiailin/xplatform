package com.jiakun.xplatform.sap.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.api.sap.ISSOService;
import com.jiakun.xplatform.framework.exception.SystemException;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.HttpUtil;

public class SSOServiceImpl implements ISSOService {

	private static final String PARAM_CLIENT = "sap-client";

	private static final String PARAM_USER = "sap-user";

	private static final String PARAM_PASSWORD = "sap-password";

	private static final String STRING_START = "action=\"";

	private Logger4jExtend logger = Logger4jCollection.getLogger(SSOServiceImpl.class);

	/**
	 * http://192.168.160.28:8000.
	 */
	private String portalDomain;

	/**
	 * /sap/bc/gui/sap/its/webgui.
	 */
	private String portalUrl;

	/**
	 * 800.
	 */
	private String sapClient;

	public String getSSOUrl(String user, String password) throws SystemException {
		String ssoUrl = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put(PARAM_CLIENT, sapClient);
			params.put(PARAM_USER, user);
			params.put(PARAM_PASSWORD, password);
			String responseStr = HttpUtil.post(portalDomain + portalUrl, params);

			int start = responseStr.indexOf(STRING_START);
			if (start != -1) {
				start = start + STRING_START.length();
				ssoUrl = portalDomain + responseStr.substring(start, start + portalUrl.length() + 102);
			}
		} catch (Exception e) {
			throw new SystemException("免登失败", e);
		}

		return ssoUrl;
	}

	public String getMySAPSSO2Ticket(String user, String password) throws SystemException {
		String ticket = null;
		StringBuilder str = new StringBuilder();
		try {
			str.append(portalDomain).append(portalUrl).append("?").append(PARAM_CLIENT).append("=").append(sapClient)
				.append("&").append(PARAM_USER).append("=").append(user).append("&").append(PARAM_PASSWORD).append("=")
				.append(password);

			URL url = new URL(str.toString());
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("content-type", "application/binary;charset=UTF-8");
			int responseCode = ((HttpURLConnection) connection).getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				String headerName = connection.getHeaderFieldKey(1);

				for (int i = 1; StringUtil.isNotEmpty(headerName); i++) {
					if (headerName.equals("set-cookie")) {
						String cookie = connection.getHeaderField(i);
						if (cookie.indexOf("MYSAPSSO2") != -1) {
							ticket = cookie.substring(cookie.indexOf('=') + 1, cookie.indexOf(';'));
							break;
						}
					}
					headerName = connection.getHeaderFieldKey(i);
				}
			}
		} catch (Exception e) {
			logger.error("str:" + str.toString(), e);
			throw new SystemException("免登失败", e);
		}

		return ticket;
	}

	public String getPortalDomain() {
		return portalDomain;
	}

	public void setPortalDomain(String portalDomain) {
		this.portalDomain = portalDomain;
	}

	public String getPortalUrl() {
		return portalUrl;
	}

	public void setPortalUrl(String portalUrl) {
		this.portalUrl = portalUrl;
	}

	public String getSapClient() {
		return sapClient;
	}

	public void setSapClient(String sapClient) {
		this.sapClient = sapClient;
	}

}
