package com.jiakun.xplatform.api.monitor;

import java.util.List;

import com.jiakun.xplatform.api.monitor.bo.MonitorLog;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * �O�����I�ӿ�
 * 
 * @author xujiakun
 * 
 */
public interface IMonitorLogService {

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
	 * ���������־
	 * 
	 * @param monitorLog
	 * @return
	 */
	BooleanResult createMonitorLog(MonitorLog monitorLog);

}
