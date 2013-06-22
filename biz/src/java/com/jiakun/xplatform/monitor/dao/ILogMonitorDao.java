package com.jiakun.xplatform.monitor.dao;

import java.util.List;

import com.jiakun.xplatform.api.monitor.bo.LogMonitor;

public interface ILogMonitorDao {

	/**
	 * 
	 * @param logMonitor
	 * @return
	 */
	public int getLogMonitorCount(LogMonitor logMonitor);

	/**
	 * 
	 * @param logMonitor
	 * @return
	 */
	public List<LogMonitor> getLogMonitorList(LogMonitor logMonitor);

	/**
	 * 
	 * @param logMonitorList
	 * @return
	 */
	public String createLogMonitor(List<LogMonitor> logMonitorList);

	/**
	 * 
	 * @return
	 */
	public List<LogMonitor> getLogMonitorList4SendEmail();

}
