package com.jiakun.xplatform.login.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.common.lang.StringUtil;
import com.crystaldecisions.sdk.framework.IEnterpriseSession;
import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.cache.IMemcachedCacheService;
import com.jiakun.xplatform.api.login.ICAService;
import com.jiakun.xplatform.api.login.bo.ValidateResult;
import com.jiakun.xplatform.framework.action.BaseAction;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.EncryptUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 7498561926934442624L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(LoginAction.class);

	private ICAService caService;

	private String passport;

	private String password;

	private boolean validate;

	private String domain;

	private int eventCount;

	private String eventSummary;

	private String fchCookieKey;

	private String fchCookieValue;

	private String fchCookieDomain;

	private IMemcachedCacheService memcachedCacheService;

	public String index() {
		return SUCCESS;
	}

	public String login() {

		ValidateResult result = null;

		// true:��Ҫ����֤ false:����Ҫ����֤
		if (validate) {
			result = caService.validateUserByLDAP(passport, password);
		} else {
			result = caService.validateUser(passport, password);
		}

		// ��֤ʧ��
		if (ICAService.RESULT_FAILED.equals(result.getResultCode())
			|| ICAService.RESULT_ERROR.equals(result.getResultCode())) {
			this.setFailMessage(result.getMessage());
			return "incorrect";
		}

		// ��֤ͨ��
		// 1 or Y or U ����
		AllUsers loginUser = result.getAllUser();

		if (("1".equals(loginUser.getUserState()) || "Y".equals(loginUser.getUserState()) || "U".equals(loginUser
			.getUserState()))) {
			HttpSession session = this.getSession();

			session.setAttribute("ACEGI_SECURITY_LAST_USERNAME", loginUser.getLoginId());
			session.setAttribute("ACEGI_SECURITY_LAST_LOGINUSER", loginUser);

			// if (validate) {
			// // �ж�����ʧЧ���ڣ���ǰ����ʧЧ�Ļ���������
			// if (new Date().getTime() > getUser().getPaswdSignDate()
			// .getTime()) { // ʧЧ
			// // allAsers = getUser();
			// // cust_type = getUser().getCustType();
			// return "modifyPasswd";
			// }
			// }

			// modify by xujiakun 2010-11-6
			HttpServletResponse response = getServletResponse();
			if (response != null) {
				Cookie ps = new Cookie("PS", loginUser.getLoginId());
				// ps.setMaxAge(-1);
				ps.setPath("/");
				ps.setDomain(domain);
				response.addCookie(ps);

				try {
					password = EncryptUtil.md5Encry(password);
				} catch (Exception e) {
					password = null;
				}
				Cookie pw = new Cookie("PW", password);
				// pw.setMaxAge(-1);
				pw.setPath("/");
				pw.setDomain(domain);
				response.addCookie(pw);

				// modify by xujiakun 2012-8-17 add cookie
				Cookie cookie = new Cookie(fchCookieKey, passport);
				// cookie.setPath("/");
				cookie.setDomain(fchCookieDomain);
				response.addCookie(cookie);
			}

			result = null;

			return SUCCESS;
		} else {
			result = null;

			return LOGIN;
		}

	}

	public String logout() {
		HttpSession session = this.getSession();

		try {
			memcachedCacheService.remove(session.getId());
		} catch (Exception e) {
			logger.error(e);
		}

		try {
			IEnterpriseSession enterpriseSession = (IEnterpriseSession) session.getAttribute("EnterpriseSession");
			if (enterpriseSession != null) {
				enterpriseSession.logoff();
			}

			enterpriseSession = (IEnterpriseSession) session.getAttribute("Bo4EnterpriseSession");
			if (enterpriseSession != null) {
				enterpriseSession.logoff();
			}
		} catch (Exception e) {
			logger.error(e);
		}

		try {
			session.removeAttribute("ACEGI_SECURITY_LAST_USERNAME");
			session.removeAttribute("ACEGI_SECURITY_LAST_LOGINUSER");
			session.removeAttribute("EnterpriseSession");
			session.removeAttribute("Bo4EnterpriseSession");
			session.removeAttribute("LogonToken");
			session.removeAttribute("Bo4LogonToken");

			session.invalidate();
		} catch (Exception e) {
			logger.error(e);
		}

		return LOGOUT;
	}

	private void initToken() {
		// ��ʼ��
		token = caService.generateToken(this.getUser());
	}

	public String homePage() {
		initToken();
		return "homePage";
	}

	public String headMenu() {

		AllUsers user = getUser();

		// �û���Ϣ��ʾ
		passport = user.getLoginId();
		this.setName(user.getUserName());

		initToken();
		return "headMenu";
	}

	public String forgetPasswd() {

		if (StringUtil.isNotEmpty(passport) && StringUtil.isNotEmpty(passport.trim())) {

			try {
				passport = new String(passport.trim().getBytes("ISO8859-1"), "UTF-8");
			} catch (Exception e) {
				logger.error(passport, e);
			}
		}

		return SUCCESS;
	}

	public ICAService getCaService() {
		return caService;
	}

	public void setCaService(ICAService caService) {
		this.caService = caService;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getEventCount() {
		return eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}

	public String getEventSummary() {
		return eventSummary;
	}

	public void setEventSummary(String eventSummary) {
		this.eventSummary = eventSummary;
	}

	public String getFchCookieKey() {
		return fchCookieKey;
	}

	public void setFchCookieKey(String fchCookieKey) {
		this.fchCookieKey = fchCookieKey;
	}

	public String getFchCookieValue() {
		return fchCookieValue;
	}

	public void setFchCookieValue(String fchCookieValue) {
		this.fchCookieValue = fchCookieValue;
	}

	public String getFchCookieDomain() {
		return fchCookieDomain;
	}

	public void setFchCookieDomain(String fchCookieDomain) {
		this.fchCookieDomain = fchCookieDomain;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

}
