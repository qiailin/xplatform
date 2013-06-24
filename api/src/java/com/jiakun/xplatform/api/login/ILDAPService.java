package com.jiakun.xplatform.api.login;

/**
 * ��
 * 
 * @author xujiakun
 * 
 */
public interface ILDAPService {

	/**
	 * ����֤
	 * 
	 * @param fullDN
	 * @param password
	 * @return
	 */
	boolean authenticate(String fullDN, String password);

}
