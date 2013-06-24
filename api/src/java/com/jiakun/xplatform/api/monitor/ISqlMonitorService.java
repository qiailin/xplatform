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

	static final int MONITOR_FREQ_15 = 15;

	static final int MONITOR_FREQ_30 = 30;

	static final int MONITOR_FREQ_60 = 60;

	static final String MONITOR_LOG_Y = "Y";

	static final String MONITOR_LOG_N = "N";

	static final String MONITOR_STATUS_N = "N";

	static final String MONITOR_STATUS_Y = "Y";

	static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	static final String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

	static final String ERROR_NULL_MESSAGE = "����ʧ�ܣ������Ѳ����ڣ�";

	/**
	 * ִ�м��sql
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
	 * ��ѯ��Ҫ���sql���б�
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	List<SqlMonitor> getSqlMonitorList(SqlMonitor sqlMonitor);

	/**
	 * ����sql���
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	BooleanResult createSqlMonitor(SqlMonitor sqlMonitor);

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
	BooleanResult updateSqlMonitor(SqlMonitor sqlMonitor);

}
