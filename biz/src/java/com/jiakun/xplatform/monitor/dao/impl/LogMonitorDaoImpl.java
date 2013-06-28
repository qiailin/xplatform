package com.jiakun.xplatform.monitor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jiakun.xplatform.api.monitor.bo.LogMonitor;
import com.jiakun.xplatform.framework.dao.impl.BaseDaoImpl;
import com.jiakun.xplatform.monitor.dao.ILogMonitorDao;

/**
 * 
 * @author jiakunxu
 * 
 */
public class LogMonitorDaoImpl extends BaseDaoImpl implements ILogMonitorDao {

	public int getLogMonitorCount(LogMonitor logMonitor) {
		return (Integer) getSqlMapClientTemplate().queryForObject("monitor.getLogMonitorCount", logMonitor);
	}

	@SuppressWarnings("unchecked")
	public List<LogMonitor> getLogMonitorList(LogMonitor logMonitor) {
		return (List<LogMonitor>) getSqlMapClientTemplate().queryForList("monitor.getLogMonitorList", logMonitor);
	}

	public String createLogMonitor(final List<LogMonitor> logMonitorList) {
		return (String) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			StringBuilder sb = new StringBuilder();

			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();

				for (LogMonitor s : logMonitorList) {
					if (sb.length() != 0) {
						sb.append(",");
					}
					sb.append(executor.insert("monitor.createLogMonitor", s));
				}
				executor.executeBatch();

				return sb.toString();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<LogMonitor> getLogMonitorList4SendEmail() {
		return (List<LogMonitor>) getSqlMapClientTemplate().queryForList("monitor.getLogMonitorList4SendEmail");
	}

}
