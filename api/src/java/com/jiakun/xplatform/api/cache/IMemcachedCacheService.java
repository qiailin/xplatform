package com.jiakun.xplatform.api.cache;

import java.net.InetSocketAddress;
import java.util.List;

import com.jiakun.xplatform.api.cache.bo.CacheStats;

/**
 * MemcachedCache.
 * 
 * @author xujiakun
 * 
 */
public interface IMemcachedCacheService extends ICacheService<String, Object> {

	/**
	 * ȱʡcache����ʱ��
	 */
	int DEFAULT_EXP = 24 * 60 * 60;

	/**
	 * oa������ҳ
	 */
	String CACHE_KEY_NEWS = "key_news";

	int CACHE_KEY_NEWS_DEFAULT_EXP = 0;

	/**
	 * �˵���
	 */
	String CACHE_KEY_MENU_TREE = "key_menu_tree_";

	int CACHE_KEY_MENU_TREE_DEFAULT_EXP = 12 * 60 * 60;

	/**
	 * open api
	 */
	String CACHE_KEY_OPEN_API = "key_open_api";

	int CACHE_KEY_OPEN_API_DEFAULT_EXP = 0;

	/**
	 * log monitor
	 */
	String CACHE_KEY_LOG_MONITOR = "key_log_monitor";

	int CACHE_KEY_LOG_MONITOR_DEFAULT_EXP = 0;

	/**
	 * bo parameter
	 */
	String CACHE_KEY_BO_PARAMETER = "key_bo_parameter_";

	int CACHE_KEY_BO_PARAMETER_DEFAULT_EXP = 0;

	/**
	 * sso token
	 */
	int CACHE_KEY_SSO_TOKEN_DEFAULT_EXP = 60;

	/**
	 * session
	 */
	int CACHE_KEY_SESSION_DEFAULT_EXP = 2 * 60 * 60;

	/**
	 * session
	 */
	int CACHE_KEY_SESSION_EXP = 3 * 60;

	/**
	 * file id
	 */
	String CACHE_KEY_FILE_ID = "key_file_id_";

	int CACHE_KEY_FILE_ID_DEFAULT_EXP = 1 * 60 * 60;

	/**
	 * key���Ӧ����һ����������ʵ������inc������
	 * 
	 * @param key
	 * @param inc
	 * @return
	 * @throws Exception
	 */
	long incr(String key, int inc) throws Exception;

	/**
	 * key���Ӧ����һ����������ʵ������inc������
	 * 
	 * @param key
	 * @param inc
	 * @return
	 * @throws Exception
	 */
	long incr(String key, long inc) throws Exception;

	/**
	 * key���Ӧ����һ����������ʵ�ּ���decr������
	 * 
	 * @param key
	 * @param decr
	 * @return
	 * @throws Exception
	 */
	long decr(String key, int decr) throws Exception;

	/**
	 * key���Ӧ����һ����������ʵ�ּ���decr������
	 * 
	 * @param key
	 * @param decr
	 * @return
	 * @throws Exception
	 */
	long decr(String key, long decr) throws Exception;

	/**
	 * 
	 * @param address
	 * @throws Exception
	 */
	void flushAll(InetSocketAddress address) throws Exception;

	/**
	 * ����cache״̬
	 * 
	 * @return
	 * @throws Exception
	 */
	List<CacheStats> getStats() throws Exception;

}
