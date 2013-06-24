package com.jiakun.xplatform.api.cache;

import java.util.Date;

/**
 * Cache������������
 * 
 */
public interface ICacheService<K, V> {

	/**
	 * ���������������������
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	V add(K key, V value) throws Exception;

	/**
	 * �������������������������������������������
	 * 
	 * @param key
	 * @param value
	 * @param ��������������
	 * @return
	 * @throws Exception
	 */
	V add(K key, V value, Date expiry) throws Exception;

	/**
	 * �������������������������������������������
	 * 
	 * @param key
	 * @param value
	 * @param �������������������������������
	 * @return
	 * @throws Exception
	 */
	V add(K key, V value, int exp) throws Exception;

	/**
	 * ���������������������
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	V set(K key, V value) throws Exception;

	/**
	 * �������������������������������������������
	 * 
	 * @param key
	 * @param value
	 * @param ��������������
	 * @return
	 * @throws Exception
	 */
	V set(K key, V value, Date expiry) throws Exception;

	/**
	 * �������������������������������������������
	 * 
	 * @param key
	 * @param value
	 * @param �������������������������������
	 * @return
	 * @throws Exception
	 */
	V set(K key, V value, int exp) throws Exception;

	/**
	 * ���������������������,��������������key���������������������������
	 * memcache��������������������������������
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	V replace(K key, V value) throws Exception;

	/**
	 * �����������������������������������������������������������key�����������
	 * ����������������memcache��������������������������������
	 * 
	 * @param key
	 * @param value
	 * @param ��������������
	 * @return
	 * @throws Exception
	 */
	V replace(K key, V value, Date expiry) throws Exception;

	/**
	 * �����������������������������������������������������������key�����������
	 * ����������������memcache��������������������������������
	 * 
	 * @param key
	 * @param value
	 * @param ��������������
	 * @return
	 * @throws Exception
	 */
	V replace(K key, V value, int exp) throws Exception;

	/**
	 * �����������������������������
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	V get(K key) throws Exception;

	/**
	 * �����������������������������
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	V remove(K key) throws Exception;

}
