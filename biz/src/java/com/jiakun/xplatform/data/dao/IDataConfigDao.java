package com.jiakun.xplatform.data.dao;

import java.util.List;

import com.jiakun.xplatform.api.data.bo.DataConfig;

public interface IDataConfigDao {

	/**
	 * 
	 * @param dataConfig
	 * @return
	 */
	int getDataConfigCount(DataConfig dataConfig);

	/**
	 * 
	 * @param dataConfig
	 * @return
	 */
	List<DataConfig> getDataConfigList(DataConfig dataConfig);

	/**
	 * ��������data����
	 * 
	 * @param dataConfigList
	 * @return
	 */
	String createDataConfig(List<DataConfig> dataConfigList);

	/**
	 * ����data����
	 * 
	 * @param dataConfig
	 * @return
	 */
	int updateDataConfig(DataConfig dataConfig);

}
