package com.jiakun.xplatform.api.monitor;

import java.util.List;

import com.jiakun.xplatform.api.monitor.bo.LogMonitor;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * logMonitor.
 * 
 * @author xujiakun
 * 
 */
public interface ILogMonitorService {

	String ERROR_MESSAGE = "����ʧ�ܣ�";

	/**
	 * 
	 * @param logMonitor
	 * @return
	 */
	int getLogMonitorCount(LogMonitor logMonitor);

	/**
	 * 
	 * @param logMonitor
	 * @return
	 */
	List<LogMonitor> getLogMonitorList(LogMonitor logMonitor);

	/**
	 * createLogMonitor.
	 * 
	 * @param logMonitorList
	 * @return
	 */
	BooleanResult createLogMonitor(List<LogMonitor> logMonitorList);

	/**
	 * getLogMonitorList4SendEmail.
	 * 
	 * @return
	 */
	List<LogMonitor> getLogMonitorList4SendEmail();

}
