package com.jiakun.xplatform.api.login;

import com.jiakun.xplatform.api.login.bo.ValidateResult;

/**
 * Ȩ����֤
 * 
 * @author xujiakun
 * 
 */
public interface ICAService {

	/**
	 * �ɹ�
	 */
	static final String RESULT_SUCCESS = "0";

	/**
	 * ʧ��
	 */
	static final String RESULT_FAILED = "1";

	/**
	 * ϵͳ������
	 */
	static final String RESULT_ERROR = "2";

	static final String INCORRECT_NULL = "�û�������벻��Ϊ�գ�";

	static final String INCORRECT_LOGINID = "���û���ϵͳ�в����ڣ�";

	static final String INCORRECT_LOGIN = "�û�����������벻��ȷ��";

	static final String INCORRECT_TOKEN = "token��֤ʧ�ܣ�";

	/**
	 * ��֤��¼����ͨ������֤��
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	ValidateResult validateUser(String passport, String password);

	/**
	 * ��֤��¼��ͨ������֤��
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	ValidateResult validateUserByLDAP(String passport, String password);

	/**
	 * ��֤token
	 * 
	 * @param token
	 * @return
	 */
	ValidateResult validateToken(String token);

	/**
	 * ������token
	 * 
	 * @param object
	 * @return
	 */
	String generateToken(Object object);

}
