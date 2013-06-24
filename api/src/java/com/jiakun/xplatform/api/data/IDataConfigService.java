package com.jiakun.xplatform.api.data;

import java.util.List;

import com.jiakun.xplatform.api.data.bo.DataConfig;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * data����Ȩ������
 * 
 * @author xujiakun
 * 
 */
public interface IDataConfigService {

	static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	static final String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

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
	BooleanResult createDataConfig(List<DataConfig> dataConfigList);

	/**
	 * ����data����
	 * 
	 * @param dataConfig
	 * @return
	 */
	BooleanResult updateDataConfig(DataConfig dataConfig);

}
