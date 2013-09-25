package com.jiakun.xplatform.sap.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.api.sap.ISSOService;
import com.jiakun.xplatform.framework.exception.SystemException;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;

public class SSOServiceImpl implements ISSOService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(SSOServiceImpl.class);

	/**
	 * http://192.168.160.28:8000/sap/bc/gui/sap/its/webgui.
	 */
	private String portalUrl;

	/**
	 * 800.
	 */
	private String sapClient;

	public String getSSOTicket(String user, String password) throws SystemException {
		String ticket = null;
		StringBuilder str = new StringBuilder();
		try {
			str.append(portalUrl).append("?sap-client=").append(sapClient).append("&sap-user=").append(user)
				.append("&sap-password=").append(password);
			URL url = new URL(str.toString());
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("content-type", "application/binary;charset=UTF-8");
			int responseCode = ((HttpURLConnection) connection).getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				String headerName = connection.getHeaderFieldKey(0);

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
