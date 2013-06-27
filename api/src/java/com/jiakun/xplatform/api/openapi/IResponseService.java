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

	String ERROR_MESSAGE = "����ʧ�ܣ�";

	/**
	 * 
	 * @param responseStats
	 * @return
	 */
	int getResponseStatsCount(ResponseStats responseStats);

	/**
	 * 
	 * @param responseStats
	 * @return
	 */
	List<ResponseStats> getResponseStatsList(ResponseStats responseStats);

	/**
	 * createResponseStats.
	 * 
	 * @param responseStatsList
	 * @return
	 */
	BooleanResult createResponseStats(List<ResponseStats> responseStatsList);

}
