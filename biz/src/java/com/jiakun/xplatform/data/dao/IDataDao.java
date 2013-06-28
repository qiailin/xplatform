package com.jiakun.xplatform.data.dao;

import java.util.List;
import java.util.Map;

import com.jiakun.xplatform.api.data.bo.DataInfo;
import com.jiakun.xplatform.api.data.bo.DataLogTotal;
import com.jiakun.xplatform.api.data.bo.TabColumn;

/**
 * 
 * @author jiakunxu
 * 
 */
public interface IDataDao {

	/**
	 * 
	 * @param dataLogTotalId
	 * @param userId
	 * @return
	 */
	List<TabColumn> getTabColumnsByLogId(Long dataLogTotalId, String userId);

	/**
	 * ��ȡ���ֶ���Ϣ
	 * 
	 * @param dataConfigId
	 * @param userId
	 * @return
	 */
	List<TabColumn> getTabColumnsByConfigId(Long dataConfigId, String userId);

	/**
	 * 
	 * @param dataInfoList
	 * @return
	 */
	String createDataInfo(List<DataInfo> dataInfoList);

	/**
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	List<Map<String, Object>> getDataPreviewList(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotalId
	 * @param userId
	 * @param primaryKey
	 * @return
	 */
	int deleteDataInfo(Long dataLogTotalId, String userId, String tableName, String primaryKey);

}
