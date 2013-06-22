package com.jiakun.xplatform.api.monitor;

import java.util.List;
import java.util.Map;

import com.jiakun.xplatform.api.monitor.bo.SqlMonitor;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * sql���
 * 
 * @author xujiakun
 * 
 */
public interface ISqlMonitorService {

	public static final int MONITOR_FREQ_15 = 15;

	public static final int MONITOR_FREQ_30 = 30;

	public static final int MONITOR_FREQ_60 = 60;

	public static final String MONITOR_LOG_Y = "Y";

	public static final String MONITOR_LOG_N = "N";

	public static final String MONITOR_STATUS_N = "N";

	public static final String MONITOR_STATUS_Y = "Y";

	public static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	public static final String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

	public static final String ERROR_NULL_MESSAGE = "����ʧ�ܣ������Ѳ����ڣ�";

	/**
	 * ִ�м��sql
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
	 * ��ѯ��Ҫ���sql���б�
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	public List<SqlMonitor> getSqlMonitorList(SqlMonitor sqlMonitor);

	/**
	 * ����sql���
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	public BooleanResult createSqlMonitor(SqlMonitor sqlMonitor);

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
	public BooleanResult updateSqlMonitor(SqlMonitor sqlMonitor);

}
