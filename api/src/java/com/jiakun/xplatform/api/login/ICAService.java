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
	public static final String RESULT_SUCCESS = "0";

	/**
	 * ʧ��
	 */
	public static final String RESULT_FAILED = "1";

	/**
	 * ϵͳ������
	 */
	public static final String RESULT_ERROR = "2";

	public static final String INCORRECT_NULL = "�û�������벻��Ϊ�գ�";

	public static final String INCORRECT_LOGINID = "���û���ϵͳ�в����ڣ�";

	public static final String INCORRECT_LOGIN = "�û�����������벻��ȷ��";

	public static final String INCORRECT_TOKEN = "token��֤ʧ�ܣ�";

	/**
	 * ��֤��¼����ͨ������֤��
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public ValidateResult validateUser(String passport, String password);

	/**
	 * ��֤��¼��ͨ������֤��
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public ValidateResult validateUserByLDAP(String passport, String password);

	/**
	 * ��֤token
	 * 
	 * @param token
	 * @return
	 */
	public ValidateResult validateToken(String token);

	/**
	 * ������token
	 * 
	 * @param object
	 * @return
	 */
	public String generateToken(Object object);

}
