package com.jiakun.xplatform.data.dao;

import java.util.List;

import com.jiakun.xplatform.api.data.bo.DataLogDetail;
import com.jiakun.xplatform.api.data.bo.DataLogTotal;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IDataLogDao {

	/**
	 * �����ܱ�
	 * 
	 * @param dataLogTotalList
	 * @return
	 */
	Long createDataLogTotal(DataLogTotal dataLogTotal);

	/**
	 * ������ϸ
	 * 
	 * @param dataLogDetailList
	 * @return
	 */
	String createDataLogDetail(List<DataLogDetail> dataLogDetailList);

	/**
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	int getDataLogTotalCount(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	List<DataLogTotal> getDataLogTotalList(DataLogTotal dataLogTotal);

	/**
	 * ��ȡ��ǰ���������Ӧtable
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	int getDBTableCount(DataLogTotal dataLogTotal);

	/**
	 * ��ȡ��ǰ���������Ӧtable
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	// List<CmsTbDict> getDBTableList(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	int getDataLogDetailCount(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotalId
	 * @param userId
	 * @return
	 */
	int updateDataLogTotal(Long dataLogTotalId, String userId, String flag);

}
