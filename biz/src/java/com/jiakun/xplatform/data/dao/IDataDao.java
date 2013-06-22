package com.jiakun.xplatform.data.dao;

import java.util.List;
import java.util.Map;

import com.jiakun.xplatform.api.data.bo.DataInfo;
import com.jiakun.xplatform.api.data.bo.DataLogTotal;
import com.jiakun.xplatform.api.data.bo.TabColumn;

public interface IDataDao {

	/**
	 * 
	 * @param dataLogTotalId
	 * @param userId
	 * @return
	 */
	public List<TabColumn> getTabColumnsByLogId(Long dataLogTotalId,
			String userId);

	/**
	 * ��ȡ���ֶ���Ϣ
	 * 
	 * @param dataConfigId
	 * @param userId
	 * @return
	 */
	public List<TabColumn> getTabColumnsByConfigId(Long dataConfigId,
			String userId);

	/**
	 * 
	 * @param dataInfoList
	 * @return
	 */
	public String createDataInfo(List<DataInfo> dataInfoList);

	/**
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	public List<Map<String, Object>> getDataPreviewList(
			DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotalId
	 * @param userId
	 * @param primaryKey
	 * @return
	 */
	public int deleteDataInfo(Long dataLogTotalId, String userId,
			String tableName, String primaryKey);

}
