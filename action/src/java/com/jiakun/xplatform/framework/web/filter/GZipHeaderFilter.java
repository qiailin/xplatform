package com.jiakun.xplatform.framework.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * GZip Filter.
 * 
 * @author xujiakun
 * 
 */
public class GZipHeaderFilter implements Filter {
	private Map<String, String> headers = new HashMap<String, String>();

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
		ServletException {
		if (req instanceof HttpServletRequest) {
			doFilter((HttpServletRequest) req, (HttpServletResponse) res, chain);
		} else {
			chain.doFilter(req, res);
		}
	}

	@SuppressWarnings("rawtypes")
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		for (Iterator it = headers.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			response.addHeader((String) entry.getKey(), (String) entry.getValue());
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		String headersStr = config.getInitParameter("headers");
		String[] header = headersStr.split(",");
		for (int i = 0; i < header.length; i++) {
			String[] temp = header[i].split("=");
			this.headers.put(temp[0].trim(), temp[1].trim());
		}
	}
}
