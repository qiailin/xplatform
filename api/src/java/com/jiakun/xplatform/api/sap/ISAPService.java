package com.jiakun.xplatform.api.sap;

import java.util.List;

import com.jiakun.xplatform.framework.exception.SystemException;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ISAPService {

	/**
	 * 根据当前用户的sap角色，更新sap临时帐号权限.
	 * 
	 * @param passport
	 * @param roles
	 * @return
	 * @throws SystemException
	 */
	String updatePermission(String passport, List<String> roles) throws SystemException;

	/**
	 * 清空sap临时帐权限.
	 * 
	 * @param passport
	 * @return
	 */
	boolean removePermission(String passport);

}
