package com.jiakun.xplatform.api.login;

import com.jiakun.xplatform.api.login.bo.ValidateResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ICAService {

	String RESULT_SUCCESS = "0";

	String RESULT_FAILED = "1";

	String RESULT_ERROR = "2";

	String INCORRECT_NULL = "�û�������벻��Ϊ�գ�";

	String INCORRECT_LOGINID = "���û���ϵͳ�в����ڣ�";

	String INCORRECT_LOGIN = "�û�����������벻��ȷ��";

	String INCORRECT_TOKEN = "token��֤ʧ�ܣ�";

	/**
	 * validateUser.
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	ValidateResult validateUser(String passport, String password);

	/**
	 * validateUserByLDAP.
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	ValidateResult validateUserByLDAP(String passport, String password);

	/**
	 * validateToken.
	 * 
	 * @param token
	 * @return
	 */
	ValidateResult validateToken(String token);

	/**
	 * generateToken.
	 * 
	 * @param object
	 * @return
	 */
	String generateToken(Object object);

}
