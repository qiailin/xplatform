package com.jiakun.xplatform.data.dao;

import java.util.List;

import com.jiakun.xplatform.api.data.bo.DataConfig;

public interface IDataConfigDao {

	/**
	 * 
	 * @param dataConfig
	 * @return
	 */
	public int getDataConfigCount(DataConfig dataConfig);

	/**
	 * 
	 * @param dataConfig
	 * @return
	 */
	public List<DataConfig> getDataConfigList(DataConfig dataConfig);

	/**
	 * ��������data����
	 * 
	 * @param dataConfigList
	 * @return
	 */
	public String createDataConfig(List<DataConfig> dataConfigList);

	/**
	 * ����data����
	 * 
	 * @param dataConfig
	 * @return
	 */
	public int updateDataConfig(DataConfig dataConfig);

}
