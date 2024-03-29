package com.jiakun.xplatform.data.dao;

import java.util.List;

import com.jiakun.xplatform.api.data.bo.DataConfig;

/**
 * 
 * @author jiakunxu
 * 
 */
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
	 * createDataConfig.
	 * 
	 * @param dataConfigList
	 * @return
	 */
	String createDataConfig(List<DataConfig> dataConfigList);

	/**
	 * updateDataConfig.
	 * 
	 * @param dataConfig
	 * @return
	 */
	int updateDataConfig(DataConfig dataConfig);

}
