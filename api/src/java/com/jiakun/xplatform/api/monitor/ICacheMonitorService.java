package com.jiakun.xplatform.api.monitor;

import java.util.List;

import com.jiakun.xplatform.api.cache.bo.CacheStats;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * cache���
 * 
 * @author xujiakun
 * 
 */
public interface ICacheMonitorService {

	static final String ERROR_MESSAGE = "����ʧ�ܣ�";

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
	 * ��������cacheStats��Ϣ
	 * 
	 * @param cacheStatsList
	 * @return
	 */
	BooleanResult createCacheMonitor(List<CacheStats> cacheStatsList);

}
