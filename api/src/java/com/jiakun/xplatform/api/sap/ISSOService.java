package com.jiakun.xplatform.api.sap;

import com.jiakun.xplatform.framework.exception.SystemException;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ISSOService {

	/**
	 * 根据帐号密码免登sap.
	 * 
	 * @param user
	 * @param password
	 * @return
	 * @throws SystemException
	 */
	String getSSOTicket(String user, String password) throws SystemException;

}
