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
	public int execMonitorSql(String sql);

	/**
	 * 
	 * @param sqlDetail
	 * @return
	 */
	public List<Map<String, Object>> execMonitorSqlDetail(String sqlDetail);

	/**
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	public int getSqlMonitorCount(SqlMonitor sqlMonitor);

	/**
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	public List<SqlMonitor> getSqlMonitorList(SqlMonitor sqlMonitor);

	/**
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	public Long createSqlMonitor(SqlMonitor sqlMonitor);

	/**
	 * 
	 * @param sqlMonitorId
	 * @return
	 */
	public SqlMonitor getSqlMonitorById(Long sqlMonitorId);

	/**
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	public int updateSqlMonitor(SqlMonitor sqlMonitor);

}
