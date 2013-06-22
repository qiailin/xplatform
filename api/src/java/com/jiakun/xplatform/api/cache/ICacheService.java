package com.jiakun.xplatform.api.cache;

import java.util.Date;

/**
 * Cacheͳһ�ӿ�
 * 
 */
public interface ICacheService<K, V> {

	/**
	 * �������
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public V add(K key, V value) throws Exception;

	/**
	 * ��������Ч�ڵ����
	 * 
	 * @param key
	 * @param value
	 * @param ��Ч��
	 * @return
	 * @throws Exception
	 */
	public V add(K key, V value, Date expiry) throws Exception;

	/**
	 * ��������Ч�ڵ����
	 * 
	 * @param key
	 * @param value
	 * @param ��ݳ�ʱ������
	 * @return
	 * @throws Exception
	 */
	public V add(K key, V value, int exp) throws Exception;

	/**
	 * �������
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public V set(K key, V value) throws Exception;

	/**
	 * ��������Ч�ڵ����
	 * 
	 * @param key
	 * @param value
	 * @param ��Ч��
	 * @return
	 * @throws Exception
	 */
	public V set(K key, V value, Date expiry) throws Exception;

	/**
	 * ��������Ч�ڵ����
	 * 
	 * @param key
	 * @param value
	 * @param ��ݳ�ʱ������
	 * @return
	 * @throws Exception
	 */
	public V set(K key, V value, int exp) throws Exception;

	/**
	 * �������,ǰ����key���������memcache�У����򱣴治�ɹ�
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public V replace(K key, V value) throws Exception;

	/**
	 * ��������Ч�ڵ���ݣ�ǰ����key���������memcache�У����򱣴治�ɹ�
	 * 
	 * @param key
	 * @param value
	 * @param ��Ч��
	 * @return
	 * @throws Exception
	 */
	public V replace(K key, V value, Date expiry) throws Exception;

	/**
	 * ��������Ч�ڵ���ݣ�ǰ����key���������memcache�У����򱣴治�ɹ�
	 * 
	 * @param key
	 * @param value
	 * @param ��Ч��
	 * @return
	 * @throws Exception
	 */
	public V replace(K key, V value, int exp) throws Exception;

	/**
	 * ��ȡ�������
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public V get(K key) throws Exception;

	/**
	 * �Ƴ��������
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public V remove(K key) throws Exception;

}
