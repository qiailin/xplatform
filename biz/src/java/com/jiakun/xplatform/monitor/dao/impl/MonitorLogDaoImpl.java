package com.jiakun.xplatform.monitor.dao.impl;

import java.util.List;

import com.jiakun.xplatform.api.monitor.bo.MonitorLog;
import com.jiakun.xplatform.framework.dao.impl.BaseDaoImpl;
import com.jiakun.xplatform.monitor.dao.IMonitorLogDao;

/**
 * 
 * @author jiakunxu
 * 
 */
public class MonitorLogDaoImpl extends BaseDaoImpl implements IMonitorLogDao {

	public int getMonitorLogCount(MonitorLog monitorLog) {
		return (Integer) getSqlMapClientTemplate().queryForObject("monitor.getMonitorLogCount", monitorLog);
	}

	@SuppressWarnings("unchecked")
	public List<MonitorLog> getMonitorLogList(MonitorLog monitorLog) {
		return (List<MonitorLog>) getSqlMapClientTemplate().queryForList("monitor.getMonitorLogList", monitorLog);
	}

	public Long createMonitorLog(MonitorLog monitorLog) {
		return (Long) getSqlMapClientTemplate().insert("monitor.createMonitorLog", monitorLog);
	}

}
