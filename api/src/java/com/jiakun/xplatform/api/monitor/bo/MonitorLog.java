package com.jiakun.xplatform.api.monitor.bo;

import java.util.Date;

import com.jiakun.xplatform.framework.bo.SearchInfo;

/**
 * �����־
 * 
 * @author xujiakun
 * 
 */
public class MonitorLog extends SearchInfo {

	private static final long serialVersionUID = -8861157710341110910L;

	private Long monitorLogId;

	/**
	 * �������
	 */
	private Long monitorId;

	
	private Date createDate;
	private Date modifyDate;
	
	private String sqlMonitorTitle;

	/**
	 * ��ؽ��
	 */
	private int monitorResult;

	/**
	 * ��ֵ
	 */
	private int threshold;

	public Long getMonitorLogId() {
		return monitorLogId;
	}

	public void setMonitorLogId(Long monitorLogId) {
		this.monitorLogId = monitorLogId;
	}

	public Long getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getMonitorResult() {
		return monitorResult;
	}

	public void setMonitorResult(int monitorResult) {
		this.monitorResult = monitorResult;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSqlMonitorTitle() {
		return sqlMonitorTitle;
	}

	public void setSqlMonitorTitle(String sqlMonitorTitle) {
		this.sqlMonitorTitle = sqlMonitorTitle;
	}
	
}
