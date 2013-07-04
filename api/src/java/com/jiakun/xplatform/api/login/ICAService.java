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

	String INCORRECT_NULL = "用户名或密码不能为空！";

	String INCORRECT_LOGINID = "该用户在系统中不存在！";

	String INCORRECT_LOGIN = "用户名或密码输入不正确！";

	String INCORRECT_TOKEN = "token验证失败！";

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
