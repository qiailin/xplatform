package com.jiakun.xplatform.monitor.service.impl;

import java.util.List;

import com.jiakun.xplatform.api.monitor.ILogMonitorService;
import com.jiakun.xplatform.api.monitor.bo.LogMonitor;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;
import com.jiakun.xplatform.monitor.dao.ILogMonitorDao;

public class LogMonitorServiceImpl implements ILogMonitorService {

	private Logger4jExtend logger = Logger4jCollection
			.getLogger(LogMonitorServiceImpl.class);

	private ILogMonitorDao logMonitorDao;

	public int getLogMonitorCount(LogMonitor logMonitor) {
		try {
			return logMonitorDao.getLogMonitorCount(logMonitor);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(logMonitor), e);
		}

		return 0;
	}

	public List<LogMonitor> getLogMonitorList(LogMonitor logMonitor) {
		try {
			return logMonitorDao.getLogMonitorList(logMonitor);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(logMonitor), e);
		}

		return null;
	}

	public BooleanResult createLogMonitor(List<LogMonitor> logMonitorList) {
		BooleanResult res = new BooleanResult();

		try {
			String result = logMonitorDao.createLogMonitor(logMonitorList);
			res.setResult(true);
			res.setCode(result);
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setCode(ILogMonitorService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(logMonitorList), e);
		}

		return res;
	}

	public List<LogMonitor> getLogMonitorList4SendEmail() {
		try {
			return logMonitorDao.getLogMonitorList4SendEmail();
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ILogMonitorDao getLogMonitorDao() {
		return logMonitorDao;
	}

	public void setLogMonitorDao(ILogMonitorDao logMonitorDao) {
		this.logMonitorDao = logMonitorDao;
	}

}
