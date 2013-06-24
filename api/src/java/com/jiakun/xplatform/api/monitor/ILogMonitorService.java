package com.jiakun.xplatform.api.monitor;

import java.util.List;

import com.jiakun.xplatform.api.monitor.bo.LogMonitor;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * log���
 * 
 * @author xujiakun
 * 
 */
public interface ILogMonitorService {

	static final String ERROR_MESSAGE = "����ʧ�ܣ�";

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
	 * ��������log��Ϣ
	 * 
	 * @param logMonitorList
	 * @return
	 */
	BooleanResult createLogMonitor(List<LogMonitor> logMonitorList);

	/**
	 * ��ȡ������־ ���ͼ�ؽ���ʼ�
	 * 
	 * @return
	 */
	List<LogMonitor> getLogMonitorList4SendEmail();

}
