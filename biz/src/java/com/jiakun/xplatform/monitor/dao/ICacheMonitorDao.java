package com.jiakun.xplatform.monitor.dao;

import java.util.List;

import com.jiakun.xplatform.api.cache.bo.CacheStats;

public interface ICacheMonitorDao {

	/**
	 * 
	 * @param cacheStats
	 * @return
	 */
	public int getCacheMonitorCount(CacheStats cacheStats);

	/**
	 * 
	 * @param cacheStats
	 * @return
	 */
	public List<CacheStats> getCacheMonitorList(CacheStats cacheStats);

	/**
	 * 
	 * @param cacheStatsList
	 * @return
	 */
	public String createCacheMonitor(List<CacheStats> cacheStatsList);

}
