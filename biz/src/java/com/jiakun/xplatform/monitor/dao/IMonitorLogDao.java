package com.jiakun.xplatform.monitor.dao;

import java.util.List;

import com.jiakun.xplatform.api.monitor.bo.MonitorLog;

public interface IMonitorLogDao {

	/**
	 * 
	 * @param monitorLog
	 * @return
	 */
	int getMonitorLogCount(MonitorLog monitorLog);

	/**
	 * 
	 * @param monitorLog
	 * @return
	 */
	List<MonitorLog> getMonitorLogList(MonitorLog monitorLog);

	/**
	 * 
	 * @param monitorLog
	 * @return
	 */
	Long createMonitorLog(MonitorLog monitorLog);

}
