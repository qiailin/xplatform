package com.jiakun.xplatform.openapi.dao;

import java.util.List;

import com.jiakun.xplatform.api.openapi.bo.ResponseStats;

/**
 * 
 * @author jiakunxu
 * 
 */
public interface IResponseDao {

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
	 * 
	 * @param responseStatsList
	 * @return
	 */
	String createResponseStats(List<ResponseStats> responseStatsList);

}
