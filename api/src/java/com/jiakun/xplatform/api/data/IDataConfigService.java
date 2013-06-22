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

	public static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	public static final String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

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
	public BooleanResult createDataConfig(List<DataConfig> dataConfigList);

	/**
	 * ����data����
	 * 
	 * @param dataConfig
	 * @return
	 */
	public BooleanResult updateDataConfig(DataConfig dataConfig);

}
