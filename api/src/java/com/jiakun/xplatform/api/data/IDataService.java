package com.jiakun.xplatform.api.data;

import java.util.List;

import com.jiakun.xplatform.api.data.bo.DataInfo;
import com.jiakun.xplatform.api.data.bo.DataLogTotal;
import com.jiakun.xplatform.api.data.bo.TabColumn;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IDataService {

	String ERROR_MESSAGE = "����ʧ�ܣ�";

	/**
	 * getTabColumnsByLogId.
	 * 
	 * @param dataLogTotalId
	 * @param userId
	 * @return
	 */
	List<TabColumn> getTabColumnsByLogId(Long dataLogTotalId, String userId);

	/**
	 * getTabColumnsByConfigId.
	 * 
	 * @param dataConfigId
	 * @param userId
	 * @return
	 */
	List<TabColumn> getTabColumnsByConfigId(Long dataConfigId, String userId);

	/**
	 * createDataInfo.
	 * 
	 * @param dataInfoList
	 * @return
	 */
	BooleanResult createDataInfo(Long dataConfigId, List<DataInfo> dataInfoList);

	/**
	 * getDataPreviewCount.
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	int getDataPreviewCount(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotal
	 * @param userId
	 * @return
	 */
	List<DataInfo> getDataPreviewList(DataLogTotal dataLogTotal);

	/**
	 * deleteDataInfo.
	 * 
	 * @param dataLogTotalId
	 * @param userId
	 * @return
	 */
	BooleanResult deleteDataInfo(Long dataLogTotalId, String userId);

}
