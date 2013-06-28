package com.jiakun.xplatform.monitor.service.impl;

import java.util.List;

import com.jiakun.xplatform.api.monitor.IMonitorLogService;
import com.jiakun.xplatform.api.monitor.ISqlMonitorService;
import com.jiakun.xplatform.api.monitor.bo.MonitorLog;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;
import com.jiakun.xplatform.monitor.dao.IMonitorLogDao;

/**
 * 
 * @author jiakunxu
 * 
 */
public class MonitorLogServiceImpl implements IMonitorLogService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(MonitorLogServiceImpl.class);

	private IMonitorLogDao monitorLogDao;

	public int getMonitorLogCount(MonitorLog monitorLog) {
		try {
			return monitorLogDao.getMonitorLogCount(monitorLog);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(monitorLog), e);
		}

		return 0;
	}

	public List<MonitorLog> getMonitorLogList(MonitorLog monitorLog) {
		try {
			return monitorLogDao.getMonitorLogList(monitorLog);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(monitorLog), e);
		}

		return null;
	}

	public BooleanResult createMonitorLog(MonitorLog monitorLog) {
		BooleanResult result = new BooleanResult();

		try {
			monitorLogDao.createMonitorLog(monitorLog);
			result.setCode(monitorLog.getMonitorLogId().toString());
			result.setResult(true);
		} catch (Exception e) {
			result.setCode(ISqlMonitorService.ERROR_MESSAGE);
			result.setResult(false);
			logger.error(LogUtil.parserBean(monitorLog), e);
		}

		return result;
	}

	public IMonitorLogDao getMonitorLogDao() {
		return monitorLogDao;
	}

	public void setMonitorLogDao(IMonitorLogDao monitorLogDao) {
		this.monitorLogDao = monitorLogDao;
	}

}
