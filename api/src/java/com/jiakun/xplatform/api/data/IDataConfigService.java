package com.jiakun.xplatform.api.data;

import java.util.List;

import com.jiakun.xplatform.api.data.bo.DataConfig;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * dataConfig.
 * 
 * @author xujiakun
 * 
 */
public interface IDataConfigService {

	String ERROR_MESSAGE = "����ʧ�ܣ�";

	String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

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
	BooleanResult createDataConfig(List<DataConfig> dataConfigList);

	/**
	 * updateDataConfig.
	 * 
	 * @param dataConfig
	 * @return
	 */
	BooleanResult updateDataConfig(DataConfig dataConfig);

}
