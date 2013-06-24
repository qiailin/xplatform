package com.jiakun.xplatform.monitor.dao;

import java.util.List;
import java.util.Map;

import com.jiakun.xplatform.api.monitor.bo.SqlMonitor;

public interface ISqlMonitorDao {

	/**
	 * 
	 * @param sql
	 * @return
	 */
	int execMonitorSql(String sql);

	/**
	 * 
	 * @param sqlDetail
	 * @return
	 */
	List<Map<String, Object>> execMonitorSqlDetail(String sqlDetail);

	/**
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	int getSqlMonitorCount(SqlMonitor sqlMonitor);

	/**
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	List<SqlMonitor> getSqlMonitorList(SqlMonitor sqlMonitor);

	/**
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	Long createSqlMonitor(SqlMonitor sqlMonitor);

	/**
	 * 
	 * @param sqlMonitorId
	 * @return
	 */
	SqlMonitor getSqlMonitorById(Long sqlMonitorId);

	/**
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	int updateSqlMonitor(SqlMonitor sqlMonitor);

}
