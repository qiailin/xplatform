package com.jiakun.xplatform.monitor.dao;

import java.util.List;

import com.jiakun.xplatform.api.monitor.bo.MonitorLog;

public interface IMonitorLogDao {

	/**
	 * 
	 * @param monitorLog
	 * @return
	 */
	public int getMonitorLogCount(MonitorLog monitorLog);

	/**
	 * 
	 * @param monitorLog
	 * @return
	 */
	public List<MonitorLog> getMonitorLogList(MonitorLog monitorLog);

	/**
	 * 
	 * @param monitorLog
	 * @return
	 */
	public Long createMonitorLog(MonitorLog monitorLog);

}
