package com.jiakun.xplatform.monitor.task;

import java.util.List;
import java.util.Map;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.api.monitor.IMonitorLogService;
import com.jiakun.xplatform.api.monitor.ISqlMonitorService;
import com.jiakun.xplatform.api.monitor.bo.MonitorLog;
import com.jiakun.xplatform.api.monitor.bo.SqlMonitor;
import com.jiakun.xplatform.api.sms.IMessageService;
import com.jiakun.xplatform.api.sms.bo.Message;
import com.jiakun.xplatform.framework.mail.MailService;

/**
 * 
 * @author xujiakun
 * 
 */
public class SqlMonitorTask {

	private static final int LIMIT = 10;

	private ISqlMonitorService sqlMonitorService;

	private IMonitorLogService monitorLogService;

	private IMessageService messageService;

	private String smtpServer;

	private String from;

	/**
	 * 15.
	 */
	public void sqlMonitorAt15() {
		sqlMonitorAtX(sqlMonitorService, messageService, monitorLogService, ISqlMonitorService.MONITOR_FREQ_15);
	}

	/**
	 * 30.
	 */
	public void sqlMonitorAt30() {
		sqlMonitorAtX(sqlMonitorService, messageService, monitorLogService, ISqlMonitorService.MONITOR_FREQ_30);
	}

	/**
	 * 60.
	 */
	public void sqlMonitorAt60() {
		sqlMonitorAtX(sqlMonitorService, messageService, monitorLogService, ISqlMonitorService.MONITOR_FREQ_60);
	}

	private void sqlMonitorAtX(ISqlMonitorService sqlMonitorService, IMessageService messageService,
		IMonitorLogService monitorLogService, int freq) {
		SqlMonitor sqlMonitor = new SqlMonitor();
		sqlMonitor.setFreq(freq);
		sqlMonitor.setStatus(ISqlMonitorService.MONITOR_STATUS_Y);

		int count = sqlMonitorService.getSqlMonitorCount(sqlMonitor);

		if (count > 0) {
			int start = 0;
			int limit = LIMIT;
			while (start < count) {
				sqlMonitor.setStart(start);

				// 查询需要监控的sql列表
				List<SqlMonitor> list = sqlMonitorService.getSqlMonitorList(sqlMonitor);

				StringBuilder content = new StringBuilder();

				for (SqlMonitor s : list) {
					content.delete(0, content.length());

					// 执行监控sql
					int re = sqlMonitorService.execMonitorSql(s.getSql());

					// 超过阀值
					if (re > s.getThreshold()) {
						String emailAlarm = s.getEmailAlarm();
						// 发送邮件提醒
						if (StringUtil.isNotEmpty(emailAlarm)) {

							// 读取监控sql详细信息 只返回前10条
							List<Map<String, Object>> detailList =
								sqlMonitorService.execMonitorSqlDetail(s.getSqlDetail());

							// 处理 detailList
							for (Map<String, Object> map : detailList) {
								for (Map.Entry<String, Object> entry : map.entrySet()) {
									content.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
								}
								content.append("<br />");
							}

							// 发送邮件
							@SuppressWarnings("unused")
							Map<String, String> map =
								new MailService(smtpServer, from, "monitor", emailAlarm, s.getSqlMonitorTitle(),
									content.toString()).send();
						}

						String smsAlarm = s.getSmsAlarm();
						// 发送短信提醒
						if (StringUtil.isNotEmpty(smsAlarm)) {
							Message message = new Message();
							message.setMobile(smsAlarm);
							message.setSmsContent(s.getSqlMonitorTitle() + ":" + re);
							message.setSmsType(IMessageService.SMS_TYPE_DEFAULT);
							message.setSendFlag(IMessageService.SEND_FLAG_DEFAULT);
							message.setSmsId(IMessageService.SMS_ID_PREFIX_DEFAULT);
							messageService.sendMessage(message);
						}
					}

					// 记录日志־
					if (ISqlMonitorService.MONITOR_LOG_Y.equals(s.getLog())) {
						MonitorLog monitorLog = new MonitorLog();
						monitorLog.setMonitorId(s.getSqlMonitorId());
						monitorLog.setMonitorResult(re);
						monitorLog.setThreshold(s.getThreshold());

						monitorLogService.createMonitorLog(monitorLog);
					}
				}

				start += limit;
			}

		}

	}

	public ISqlMonitorService getSqlMonitorService() {
		return sqlMonitorService;
	}

	public void setSqlMonitorService(ISqlMonitorService sqlMonitorService) {
		this.sqlMonitorService = sqlMonitorService;
	}

	public IMonitorLogService getMonitorLogService() {
		return monitorLogService;
	}

	public void setMonitorLogService(IMonitorLogService monitorLogService) {
		this.monitorLogService = monitorLogService;
	}

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
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

}
