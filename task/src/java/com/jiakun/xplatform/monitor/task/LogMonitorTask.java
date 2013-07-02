package com.jiakun.xplatform.monitor.task;

import java.util.List;
import java.util.Map;

import com.jiakun.xplatform.api.cache.IMemcachedCacheService;
import com.jiakun.xplatform.api.monitor.ILogMonitorService;
import com.jiakun.xplatform.api.monitor.bo.LogMonitor;
import com.jiakun.xplatform.framework.mail.MailService;
import com.jiakun.xplatform.framework.util.DateUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class LogMonitorTask {

	private IMemcachedCacheService memcachedCacheService;

	private ILogMonitorService logMonitorService;

	private String smtpServer;

	private String from;

	private String to;

	@SuppressWarnings("unchecked")
	public void logMonitor() {
		List<LogMonitor> list = null;
		try {
			list = (List<LogMonitor>) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_LOG_MONITOR);
			memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_LOG_MONITOR);
		} catch (Exception e) {
		}

		if (list != null && list.size() != 0) {
			logMonitorService.createLogMonitor(list);
		}
	}

	public void logMonitor2SendEmail() {

		List<LogMonitor> list = logMonitorService.getLogMonitorList4SendEmail();

		StringBuffer content = new StringBuffer();
		content.append("<table border=1><tr>");
		content.append("<td>ID</td>");
		content.append("<td>class Name</td>");
		content.append("<td>Method Name</td>");
		content.append("<td>Line Number</td>");
		content.append("<td>Message</td>");
		content.append("<td>e</td>");
		content.append("<td>Log Date</td>");
		content.append("<td>Create Date</td>");
		content.append("</tr>");

		if (list != null && list.size() != 0) {
			for (LogMonitor monitor : list) {
				content.append("<tr>");
				content.append("<td>").append(monitor.getLogMonitorId()).append("</td>");
				content.append("<td>").append(monitor.getClassName()).append("</td>");
				content.append("<td>").append(monitor.getMethodName()).append("</td>");
				content.append("<td>").append(monitor.getLineNumber()).append("</td>");
				content.append("<td>").append(monitor.getMessage()).append("</td>");
				content.append("<td>").append(monitor.getE()).append("</td>");
				content.append("<td>").append(monitor.getLogDate()).append("</td>");
				content.append("<td>")
					.append(DateUtil.datetime(monitor.getCreateDate(), DateUtil.DEFAULT_DATETIME_FORMAT))
					.append("</td>");
				content.append("</tr>");
			}
		}

		content.append("</table>");

		if (list != null && list.size() != 0) {
			new MailService(smtpServer, from, "monitor", to, "xplatform��̨������־", content.toString()).send();
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public ILogMonitorService getLogMonitorService() {
		return logMonitorService;
	}

	public void setLogMonitorService(ILogMonitorService logMonitorService) {
		this.logMonitorService = logMonitorService;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
