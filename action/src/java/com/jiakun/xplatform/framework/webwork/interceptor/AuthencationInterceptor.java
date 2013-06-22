package com.jiakun.xplatform.framework.webwork.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.common.lang.StringUtil;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

/**
 * Ȩ����֤������
 * 
 * ���û���δ��¼������ת����¼ҳ��
 * 
 * @author xujiakun
 * 
 */
public class AuthencationInterceptor implements Interceptor {

	private static final long serialVersionUID = -7498838714747075663L;

	private static final String LOGIN = "login";

	// private static final String AUTHORIZATION_ERROR = "authorization_error";

	public void init() {
	}

	public void destroy() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {

		@SuppressWarnings("rawtypes")
		Map session = invocation.getInvocationContext().getSession();
		String name = (String) session.get("ACEGI_SECURITY_LAST_USERNAME");

		if (StringUtil.isEmpty(name)) {
			return LOGIN;
		}

		getActionName();
		getRequetSessionId();
		getRequestParam();

		return invocation.invoke();

		// return AUTHORIZATION_ERROR;

	}

	/**
	 * ������ǰ����actionName
	 * 
	 * @return
	 */
	private String getActionName() {
		String actionName = null;
		// ��ȡ��ǰapplicationContex
		ActionContext ctx = ActionContext.getContext();
		// Map map = ctx.getSession();
		// ���õ�ǰ�����URL
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		StringBuffer url = request.getRequestURL();
		int index = url.lastIndexOf(request.getContextPath())
				+ request.getContextPath().length();
		actionName = url.substring(index, url.length());
		return actionName;
	}

	private String getRequetSessionId() {
		// ��ȡ��ǰapplicationContex
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		return request.getRequestedSessionId();
	}

	@SuppressWarnings("rawtypes")
	private Map getRequestParam() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		return request.getParameterMap();
	}

}
