package com.jiakun.xplatform.api.monitor;

import java.util.List;

import com.jiakun.xplatform.api.cache.bo.CacheStats;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * cacheMonitor.
 * 
 * @author xujiakun
 * 
 */
public interface ICacheMonitorService {

	String ERROR_MESSAGE = "操作失败！";

	/**
	 * 
	 * @param cacheStats
	 * @return
	 */
	int getCacheMonitorCount(CacheStats cacheStats);

	/**
	 * 
	 * @param cacheStats
	 * @return
	 */
	List<CacheStats> getCacheMonitorList(CacheStats cacheStats);

	/**
	 * createCacheMonitor.
	 * 
	 * @param cacheStatsList
	 * @return
	 */
	BooleanResult createCacheMonitor(List<CacheStats> cacheStatsList);

}
