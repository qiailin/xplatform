package com.jiakun.xplatform.framework.webwork.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.framework.context.SecurityContext;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

/**
 * ���õ�ǰӦ�������ĵ�������
 * 
 * VM�п���ʹ�õı����� currentUser: ��ǰ�û����� currentUrl: ��ǰ�����URL�����������
 * 
 * @author jacky.chenb
 * 
 */
public class SetAppContextInterceptor implements Interceptor {

	private static final long serialVersionUID = -7498838714747075663L;

	private static final String CURRENT_USER = "currentUser";

	private static final String CURRENT_URL = "currentUrl";

	public void init() {
	}

	public void destroy() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		// ���õ�ǰ�û�
		ActionContext ctx = ActionContext.getContext();
		ctx.put(CURRENT_USER, SecurityContext.getUser());

		// ���õ�ǰ�����URL
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);

		StringBuffer url = request.getRequestURL();
		String queryString = request.getQueryString();
		if (StringUtil.isNotBlank(queryString)) {
			url.append('?');
			url.append(queryString);
		}

		ctx.put(CURRENT_URL, url);

		return invocation.invoke();
	}

}
