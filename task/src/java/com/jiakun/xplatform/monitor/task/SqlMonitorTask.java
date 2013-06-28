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

	private ISqlMonitorService sqlMonitorService;

	private IMonitorLogService monitorLogService;

	private IMessageService messageService;

	private String smtpServer;

	private String from;

	/**
	 * ÿ15����job
	 */
	public void sqlMonitorAt15() {
		sqlMonitorAtX(sqlMonitorService, messageService, monitorLogService, ISqlMonitorService.MONITOR_FREQ_15);
	}

	/**
	 * ÿ30����job
	 */
	public void sqlMonitorAt30() {
		sqlMonitorAtX(sqlMonitorService, messageService, monitorLogService, ISqlMonitorService.MONITOR_FREQ_30);
	}

	/**
	 * ÿ60����job
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
			int limit = 10;
			while (start < count) {
				sqlMonitor.setStart(start);

				// ��ѯ��Ҫ��ص�sql�б�
				List<SqlMonitor> list = sqlMonitorService.getSqlMonitorList(sqlMonitor);

				StringBuffer content = new StringBuffer();

				for (SqlMonitor s : list) {
					content.delete(0, content.length());

					// ִ�м��sql
					int re = sqlMonitorService.execMonitorSql(s.getSql());

					// ����ֵ
					if (re > s.getThreshold()) {
						String emailAlarm = s.getEmailAlarm();
						// �����ʼ�����
						if (StringUtil.isNotEmpty(emailAlarm)) {

							// ��ȡ���sql��ϸ��Ϣ ֻ����ǰ10��
							List<Map<String, Object>> detailList =
								sqlMonitorService.execMonitorSqlDetail(s.getSqlDetail());

							// ���� detailList
							for (Map<String, Object> map : detailList) {
								for (String o : map.keySet()) {
									content.append(o).append(":").append(map.get(o)).append(";");
								}
								content.append("<br />");
							}

							// �����ʼ�
							@SuppressWarnings("unused")
							Map<String, String> map =
								new MailService(smtpServer, from, "monitor", emailAlarm, s.getSqlMonitorTitle(),
									content.toString()).send();
						}

						String smsAlarm = s.getSmsAlarm();
						// ���Ͷ�������
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

					// ��¼��־
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
