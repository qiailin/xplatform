package com.jiakun.xplatform.login.service.impl;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.api.alluser.IAllUserService;
import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.cache.IMemcachedCacheService;
import com.jiakun.xplatform.api.login.ICAService;
import com.jiakun.xplatform.api.login.ILDAPService;
import com.jiakun.xplatform.api.login.bo.ValidateResult;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.framework.exception.ServiceException;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.EncryptUtil;
import com.jiakun.xplatform.framework.util.LogUtil;
import com.jiakun.xplatform.framework.util.OidUtil;

/**
 * 
 * @author jiakunxu
 * 
 */
public class CAServiceImpl implements ICAService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(CAServiceImpl.class);

	private IAllUserService allUserService;

	private ILDAPService ldapService;

	private IMemcachedCacheService memcachedCacheService;

	public ValidateResult validateUser(String passport, String password) {
		return validateUser(passport, password, false);
	}

	public ValidateResult validateUserByLDAP(String passport, String password) {
		return validateUser(passport, password, true);
	}

	private ValidateResult validateUser(String passport, String password, boolean validate) {

		// ��ʼ������ֵ ״̬ = ʧ��
		ValidateResult result = new ValidateResult();
		result.setResultCode(ICAService.RESULT_FAILED);
		result.setMessage(ICAService.INCORRECT_LOGIN);

		// �˺Ż�����Ϊ��
		if (StringUtil.isEmpty(passport) || StringUtil.isEmpty(password)) {
			result.setMessage(ICAService.INCORRECT_NULL);
			return result;
		}

		// ���passport�����û���Ϣ
		AllUsers loginUser = allUserService.getAllUserByPassport(passport);

		// �жϵ�¼�û��Ƿ���OSAPϵͳ��
		if (loginUser == null) {
			result.setMessage(ICAService.INCORRECT_LOGINID);
			return result;
		}

		// ��������֤ ��������
		if ("G".equals(loginUser.getCustType())) {
			if (password.equals(loginUser.getPassWd())) {

				return setSuccessResult(result, loginUser);
			}
			// ��֤ʧ��
			return result;
		}

		// ϵͳ����Ա���������֤ �������
		if ("admin".equals(passport) || "X".equals(loginUser.getCustType())) {
			try {
				if ((passport.equals(loginUser.getLoginId()) || passport.equals(loginUser.getMobile()) || passport
					.equals(loginUser.getPhone())) && (EncryptUtil.md5Encry(password).equals(loginUser.getPassWd()))) {

					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
			// ��֤ʧ��
			return result;
		}

		if ("V".equals(loginUser.getCustType())) {
			try {
				if ((passport.equals(loginUser.getLoginId()) || passport.equals(loginUser.getMobile()) || passport
					.equals(loginUser.getPhone())) && (EncryptUtil.md5Encry(password).equals(loginUser.getPassWd()))) {

					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
			// ��֤ʧ��
			return result;
		}

		// ����֤
		if (validate) {
			if (ldapService.authenticate(passport, password)) {

				// �ж� ���˺����� �� ims �Ƿ�һ�� / ��һ��ʱ �޸�ims����
				try {
					String pw = EncryptUtil.md5Encry(password);

					// ����һ�� -> �ɹ�
					if (pw.equals(loginUser.getPassWd())) {
						return setSuccessResult(result, loginUser);
					} else {
						AllUsers allUsers = new AllUsers();
						allUsers.setUserId(loginUser.getUserId());
						allUsers.setPassWd(pw);

						BooleanResult r = allUserService.updateAllUser(allUsers);
						// �޸ĳɹ� -> �ɹ�
						// �޸�ʧ�� -> ���µ�¼
						if (r.getResult()) {
							return setSuccessResult(result, loginUser);
						} else {
							logger.error("passport:" + passport + " ���˺�������IMS��һ��");
						}
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
		} else {
			try {
				if (passport.equals(loginUser.getLoginId())
					&& EncryptUtil.md5Encry(password).equals(loginUser.getPassWd())) {
					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return result;
	}

	public ValidateResult validateToken(String token) {
		ValidateResult result = new ValidateResult();
		result.setResultCode(ICAService.RESULT_FAILED);
		result.setMessage(ICAService.INCORRECT_TOKEN);

		try {
			AllUsers user = (AllUsers) memcachedCacheService.get(token);
			if (user != null) {
				// ������֤һ�κ� ʧЧ
				memcachedCacheService.remove(token);
				return setSuccessResult(result, user);
			}
		} catch (ServiceException e) {
			logger.error(e);
		}

		return result;
	}

	public String generateToken(Object object) {
		try {
			String token = OidUtil.newId();
			memcachedCacheService.add(token, object, IMemcachedCacheService.CACHE_KEY_SSO_TOKEN_DEFAULT_EXP);

			return token;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(object), e);
		}

		return null;
	}

	private ValidateResult setSuccessResult(ValidateResult result, AllUsers user) {
		result.setResultCode(ICAService.RESULT_SUCCESS);
		user.setPassWd(null);
		result.setAllUser(user);
		result.setMessage(null);
		return result;
	}

	public IAllUserService getAllUserService() {
		return allUserService;
	}

	public void setAllUserService(IAllUserService allUserService) {
		this.allUserService = allUserService;
	}

	public ILDAPService getLdapService() {
		return ldapService;
	}

	public void setLdapService(ILDAPService ldapService) {
		this.ldapService = ldapService;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

}
