package com.jiakun.xplatform.data.dao;

import java.util.List;

import com.jiakun.xplatform.api.data.bo.DataLogDetail;
import com.jiakun.xplatform.api.data.bo.DataLogTotal;
import com.jiakun.xplatform.api.dict.bo.CmsTbDict;

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
	public Long createDataLogTotal(DataLogTotal dataLogTotal);

	/**
	 * ������ϸ
	 * 
	 * @param dataLogDetailList
	 * @return
	 */
	public String createDataLogDetail(List<DataLogDetail> dataLogDetailList);

	/**
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	public int getDataLogTotalCount(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	public List<DataLogTotal> getDataLogTotalList(DataLogTotal dataLogTotal);

	/**
	 * ��ȡ��ǰ���������Ӧtable
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	public int getDBTableCount(DataLogTotal dataLogTotal);

	/**
	 * ��ȡ��ǰ���������Ӧtable
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	public List<CmsTbDict> getDBTableList(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	public int getDataLogDetailCount(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotalId
	 * @param userId
	 * @return
	 */
	public int updateDataLogTotal(Long dataLogTotalId, String userId,
			String flag);

}
