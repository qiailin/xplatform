package com.jiakun.xplatform.api.data;

import java.util.List;

import com.jiakun.xplatform.api.data.bo.DataLogTotal;

/**
 * ��ݲ�����־�ӿ�
 * 
 * @author xujiakun
 * 
 */
public interface IDataLogService {

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

}
