package com.jiakun.xplatform.api.data;

import java.util.List;

import com.jiakun.xplatform.api.data.bo.DataLogTotal;
import com.jiakun.xplatform.api.dict.bo.CmsTbDict;

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

}
