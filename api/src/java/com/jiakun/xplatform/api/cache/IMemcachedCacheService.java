package com.jiakun.xplatform.api.cache;

import java.net.InetSocketAddress;
import java.util.List;

import com.jiakun.xplatform.api.cache.bo.CacheStats;

/**
 * MemcachedCache�ӿ�
 * 
 * @author xujiakun
 * 
 */
public interface IMemcachedCacheService extends ICacheService<String, Object> {

	/**
	 * ȱʡcache����ʱ��
	 */
	static final int DEFAULT_EXP = 24 * 60 * 60;

	/**
	 * oa������ҳ
	 */
	static final String CACHE_KEY_NEWS = "key_news";

	static final int CACHE_KEY_NEWS_DEFAULT_EXP = 0;

	/**
	 * �˵���
	 */
	static final String CACHE_KEY_MENU_TREE = "key_menu_tree_";

	static final int CACHE_KEY_MENU_TREE_DEFAULT_EXP = 12 * 60 * 60;

	/**
	 * open api
	 */
	static final String CACHE_KEY_OPEN_API = "key_open_api";

	static final int CACHE_KEY_OPEN_API_DEFAULT_EXP = 0;

	/**
	 * log monitor
	 */
	static final String CACHE_KEY_LOG_MONITOR = "key_log_monitor";

	static final int CACHE_KEY_LOG_MONITOR_DEFAULT_EXP = 0;

	/**
	 * bo parameter
	 */
	static final String CACHE_KEY_BO_PARAMETER = "key_bo_parameter_";

	static final int CACHE_KEY_BO_PARAMETER_DEFAULT_EXP = 0;

	/**
	 * sso token
	 */
	static final int CACHE_KEY_SSO_TOKEN_DEFAULT_EXP = 60;

	/**
	 * session
	 */
	static final int CACHE_KEY_SESSION_DEFAULT_EXP = 2 * 60 * 60;

	/**
	 * session
	 */
	static final int CACHE_KEY_SESSION_EXP = 3 * 60;

	/**
	 * file id
	 */
	static final String CACHE_KEY_FILE_ID = "key_file_id_";

	static final int CACHE_KEY_FILE_ID_DEFAULT_EXP = 1 * 60 * 60;

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
