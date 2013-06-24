package com.jiakun.xplatform.api.system;

import com.alibaba.service.ServiceInitializationException;
import com.alibaba.service.dsa.DSAException;
import com.alibaba.service.dsa.NoSuchKeyPairException;

/**
 * DSA���ܺ���ӿ�
 * 
 * @author xujiakun
 * 
 */
public interface IDSAService {

	/**
	 * ��ָ���ַ����ǩ��
	 * 
	 * @param content
	 *            Ҫǩ����ַ�
	 * @param keyPairName
	 *            key pair
	 * 
	 * @return base64�����ǩ��
	 */
	String sign(String content, String keyPairName)
			throws NoSuchKeyPairException, DSAException;

	/**
	 * ��ָ���ַ����ǩ��
	 * 
	 * @param content
	 *            Ҫǩ����ַ�
	 * @param keyPairName
	 *            key pair
	 * @param charset
	 *            �ַ�ı����ַ�
	 * 
	 * @return base64�����ǩ��
	 */
	String sign(String content, String keyPairName, String charset)
			throws NoSuchKeyPairException, DSAException;

	/**
	 * ����content��ǩ��
	 * 
	 * @param content
	 *            Ҫ���������
	 * @param signature
	 *            ǩ��
	 * @param keyPairName
	 *            key pair
	 * 
	 * @return ���ǩ����ȷ���򷵻�<code>true</code>
	 */
	boolean check(String content, String signature, String keyPairName)
			throws NoSuchKeyPairException, DSAException;

	/**
	 * ����content��ǩ��
	 * 
	 * @param content
	 *            Ҫ���������
	 * @param signature
	 *            ǩ��
	 * @param keyPairName
	 *            key pair
	 * @param �ַ�ı����ַ�
	 * 
	 * @return ���ǩ����ȷ���򷵻�<code>true</code>
	 */
	boolean check(String content, String signature, String keyPairName,
			String charset) throws NoSuchKeyPairException, DSAException;

	/**
	 * @throws ServiceInitializationException
	 */
	void init() throws ServiceInitializationException;

}
