package com.jiakun.xplatform.framework.hessian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.remoting.caucho.HessianServiceExporter;

import com.alibaba.common.lang.StringUtil;
import com.alibaba.service.dsa.DSAException;
import com.alibaba.service.dsa.NoSuchKeyPairException;
import com.jiakun.xplatform.api.system.IDSAService;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.ClientUtil;

/**
 * 
 * @author jiakunxu
 * 
 */
public class DSAHessianServiceExporter extends HessianServiceExporter {

	private Logger4jExtend logger = Logger4jCollection.getLogger(DSAHessianServiceExporter.class);

	private IDSAService dsaService;

	/**
	 * �������ʱ��
	 */
	private long timeout;

	/**
	 * ����ԭ��
	 */
	private String secureKey;

	/**
	 * ������ʵĿͻ���
	 */
	private String allowedClients;

	/**
	 * ��Կ���
	 */
	private String keyPairName;

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
		IOException {

		// 1.��������������IP��ַ����Ҫ���пͻ���IP��ַ��֤
		if (StringUtil.isNotBlank(allowedClients)) {
			// String clientIP = getClientIPAddress(request);
			String clientIP = ClientUtil.getIpAddr(request);
			if (!isAllowedClient(clientIP)) {
				logger.error("hessian authentication error:" + clientIP + " not in allowedList " + allowedClients);
				return;
			}
		}

		// 2.������������ƻ��߹���ʱ�䣬��Ҫ�ж�ʱ���ʽ�Ƿ�Ϸ�
		String strTimestamp = request.getParameter("time");
		long timestamp = 0;
		if (timeout > 0 || StringUtil.isNotBlank(secureKey)) {
			if (strTimestamp == null) {
				logger.error("hessian authentication error:timestamp not exist!");
				return;
			}
			try {
				timestamp = Long.parseLong(strTimestamp);
			} catch (NumberFormatException e) {
				logger.error("hessian authentication error:timestamp is not number!");
				return;
			}
		}

		// 3.��������˹���ʱ�䣬��Ҫ�ж������Ƿ����
		if (timeout > 0 && isRequestExpired(timestamp)) {
			logger.error("hessian authentication error:request is expired!");
			return;
		}

		// 4. ������������ƣ���Ҫ����ǩ��У��
		if (StringUtil.isNotBlank(secureKey)) {
			// 4.1 �õ�ǩ��
			String signature = request.getParameter("sign");
			if (signature == null) {
				logger.error("hessian authentication error:signatures not exist!");
				return;
			}
			try {
				// 4.2 ��֤ǩ���Ƿ�һ��
				boolean isRight = dsaService.check(secureKey + "|" + timestamp, signature, keyPairName);
				if (!isRight) {
					logger.error("hessian authentication error:signatures not match!");
					return;
				}
			} catch (NoSuchKeyPairException ne) {
				logger.error("error in DSAHessianServiceExporter.handleRequest,no such key" + keyPairName, ne);
			} catch (DSAException de) {
				logger.error("error in DSAHessianServiceExporter.handleRequest,DSA sign error", de);
			}

		}

		// 5. ����hessian content-type
		response.setContentType("application/octet-stream");

		super.handleRequest(request, response);
	}

	private boolean isAllowedClient(String clientIP) {
		if (StringUtil.isNotEmpty(clientIP)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isRequestExpired(long timestamp) {
		if (timestamp > 0) {
			return true;
		} else {
			return false;
		}
	}

	public IDSAService getDsaService() {
		return dsaService;
	}

	public void setDsaService(IDSAService dsaService) {
		this.dsaService = dsaService;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public String getSecureKey() {
		return secureKey;
	}

	public void setSecureKey(String secureKey) {
		this.secureKey = secureKey;
	}

	public String getAllowedClients() {
		return allowedClients;
	}

	public void setAllowedClients(String allowedClients) {
		this.allowedClients = allowedClients;
	}

	public String getKeyPairName() {
		return keyPairName;
	}

	public void setKeyPairName(String keyPairName) {
		this.keyPairName = keyPairName;
	}

}
