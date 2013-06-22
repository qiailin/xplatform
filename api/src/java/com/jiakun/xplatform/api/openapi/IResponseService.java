package com.jiakun.xplatform.api.openapi;

import java.util.List;

import com.jiakun.xplatform.api.openapi.bo.ResponseStats;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IResponseService {

	public static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	/**
	 * 
	 * @param responseStats
	 * @return
	 */
	public int getResponseStatsCount(ResponseStats responseStats);

	/**
	 * 
	 * @param responseStats
	 * @return
	 */
	public List<ResponseStats> getResponseStatsList(ResponseStats responseStats);

	/**
	 * ��������ӿ���Ӧ��Ϣ
	 * 
	 * @param responseStatsList
	 * @return
	 */
	public BooleanResult createResponseStats(
			List<ResponseStats> responseStatsList);

}