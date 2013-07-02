package com.jiakun.xplatform.login.service.impl;

import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPException;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.api.login.ILDAPService;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;

/**
 * 
 * @author jiakunxu
 * 
 */
public class LDAPServiceImpl implements ILDAPService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(LDAPServiceImpl.class);

	private String ldapHost;

	private String ldapPort;

	private String domain;

	public boolean authenticate(String fullDN, String password) {

		boolean flag = false;

		if (StringUtil.isEmpty(fullDN) || StringUtil.isEmpty(password)) {
			return false;
		}

		LDAPConnection ld = new LDAPConnection();

		try {
			ld.connect(3, ldapHost, Integer.parseInt(ldapPort), fullDN + domain, password);
			flag = true;
		} catch (LDAPException e) {
			flag = false;
			logger.info("LDAP����" + e.getMessage());
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (ld.isConnected()) {
				try {
					ld.disconnect();
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}

		return flag;
	}

	public String getLdapHost() {
		return ldapHost;
	}

	public void setLdapHost(String ldapHost) {
		this.ldapHost = ldapHost;
	}

	public String getLdapPort() {
		return ldapPort;
	}

	public void setLdapPort(String ldapPort) {
		this.ldapPort = ldapPort;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
