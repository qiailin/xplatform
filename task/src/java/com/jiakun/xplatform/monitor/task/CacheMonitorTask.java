package com.jiakun.xplatform.monitor.task;

import com.jiakun.xplatform.api.cache.IMemcachedCacheService;
import com.jiakun.xplatform.api.monitor.ICacheMonitorService;

/**
 * 
 * @author xujiakun
 * 
 */
public class CacheMonitorTask {

	private IMemcachedCacheService memcachedCacheService;

	private ICacheMonitorService cacheMonitorService;

	public void cacheMonitor() {

		try {
			cacheMonitorService.createCacheMonitor(memcachedCacheService.getStats());
		} catch (Exception e) {
		}

	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public ICacheMonitorService getCacheMonitorService() {
		return cacheMonitorService;
	}

	public void setCacheMonitorService(ICacheMonitorService cacheMonitorService) {
		this.cacheMonitorService = cacheMonitorService;
	}

}
