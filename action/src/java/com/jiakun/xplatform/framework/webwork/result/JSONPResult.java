package com.jiakun.xplatform.framework.webwork.result;

import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.WebWorkStatics;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.Result;

/**
 * JSON with Padding Result
 * 
 * @author tingjia.chentj
 * 
 */
public abstract class JSONPResult implements Result, WebWorkStatics {

	private static final long serialVersionUID = -7892028541361553348L;

	private static final String APPLICATION_JAVASCRIPT = "application/x-javascript";

	/**
	 * Callback name.
	 */
	protected String callback;

	/**
	 * Charset.
	 */
	protected String charset;

	/**
	 * 
	 * @param charset
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * 
	 * @param callback
	 *            the name of callback
	 */
	public void setCallback(String callback) {
		this.callback = callback;
	}

	/**
	 * 
	 * @see com.opensymphony.xwork.Result#execute(com.opensymphony.xwork.ActionInvocation)
	 */
	public void execute(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		// ����ContentType
		StringBuffer contentType = new StringBuffer();
		contentType.append(APPLICATION_JAVASCRIPT);
		contentType.append(isLegalCharSet() ? ("; charset=" + charset) : "; charset=GBK");
		response.setContentType(contentType.toString());

		StringBuffer jsonp = new StringBuffer();
		jsonp.append(request.getParameter(this.callback == null ? "jsonp" : this.callback));
		jsonp.append("(");
		jsonp.append(jsonRepresentation(invocation));
		jsonp.append(");");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonp.toString());
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	/**
	 * ���JSON�ַ�
	 * 
	 * @param invocation
	 * @return json
	 */
	protected abstract String jsonRepresentation(ActionInvocation invocation);

	/**
	 * ��֤�ַ����Ч��
	 * 
	 * @return
	 */
	protected boolean isLegalCharSet() {
		Charset tmp = null;
		if (charset != null) {
			if (Charset.isSupported(charset)) {
				tmp = Charset.forName(charset);
			} else {
				tmp = null;
			}
		}
		return tmp != null;
	}

}
