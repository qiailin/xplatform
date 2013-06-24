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

	static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	/**
	 * �����־id��ѯ��Ӧ���ֶ���Ϣ
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
	 * ��������datainfo
	 * 
	 * @param dataInfoList
	 * @return
	 */
	BooleanResult createDataInfo(Long dataConfigId, List<DataInfo> dataInfoList);

	/**
	 * ���dataLogTotalId ���Ԥ��
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
	 * ɾ�����
	 * 
	 * @param dataLogTotalId
	 * @param userId
	 * @return
	 */
	BooleanResult deleteDataInfo(Long dataLogTotalId, String userId);

}
