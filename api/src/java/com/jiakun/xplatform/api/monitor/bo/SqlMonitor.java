package com.jiakun.xplatform.api.monitor.bo;

import java.util.Date;

import com.jiakun.xplatform.framework.bo.SearchInfo;

/**
 * sql���
 * 
 * @author xujiakun
 * 
 */
public class SqlMonitor extends SearchInfo {

	private static final long serialVersionUID = 1014620173517472055L;

	private Long sqlMonitorId;

	/**
	 * ��Ҫ��ص�sql ������ count
	 */
	private String sql;

	/**
	 * ��ص�sql����ֵ ���ڱ���log
	 */
	private String sqlDetail;

	/**
	 * ���Ƶ��
	 */
	private int freq;

	/**
	 * ��ֵ
	 */
	private int threshold;

	/**
	 * �ʼ����� ����ʼ���ַ �� ; �ָ�
	 */
	private String emailAlarm;

	/**
	 * ���ű���
	 */
	private String smsAlarm;

	/**
	 * �Ƿ���Ҫ��¼��־ Y or N
	 */
	private String log;
	
	/**
	 * ��������
	 */
	private Date createDate;
	/**
	 * �޸�����
	 */
	private Date modifyDate;

	/**
	 * ���״̬ Y ��Ч; N ��Ч
	 */
	private String status;

	/**
	 * ��ر���
	 */
	private String sqlMonitorTitle;

	public Long getSqlMonitorId() {
		return sqlMonitorId;
	}

	public void setSqlMonitorId(Long sqlMonitorId) {
		this.sqlMonitorId = sqlMonitorId;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSqlDetail() {
		return sqlDetail;
	}

	public void setSqlDetail(String sqlDetail) {
		this.sqlDetail = sqlDetail;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public String getEmailAlarm() {
		return emailAlarm;
	}

	public void setEmailAlarm(String emailAlarm) {
		this.emailAlarm = emailAlarm;
	}

	public String getSmsAlarm() {
		return smsAlarm;
	}

	public void setSmsAlarm(String smsAlarm) {
		this.smsAlarm = smsAlarm;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSqlMonitorTitle() {
		return sqlMonitorTitle;
	}

	public void setSqlMonitorTitle(String sqlMonitorTitle) {
		this.sqlMonitorTitle = sqlMonitorTitle;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
