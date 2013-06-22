package com.jiakun.xplatform.openapi.dao;

import java.util.List;

import com.jiakun.xplatform.api.openapi.bo.ResponseStats;

public interface IResponseDao {

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
	 * 
	 * @param responseStatsList
	 * @return
	 */
	public String createResponseStats(List<ResponseStats> responseStatsList);

}
