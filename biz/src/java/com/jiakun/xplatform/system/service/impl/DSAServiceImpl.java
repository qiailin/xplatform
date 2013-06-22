package com.jiakun.xplatform.system.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.common.lang.StringUtil;
import com.alibaba.service.ServiceInitializationException;
import com.alibaba.service.dsa.DSA;
import com.alibaba.service.dsa.DSAException;
import com.alibaba.service.dsa.NoSuchKeyPairException;
import com.jiakun.xplatform.api.system.IDSAService;
import com.jiakun.xplatform.api.system.bo.KeyInfoElement;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;

public class DSAServiceImpl implements IDSAService {

	private Logger4jExtend logger = Logger4jCollection
			.getLogger(DSAServiceImpl.class);

	/**
	 * �����ļ���
	 */
	static private final String KEY_CONFIG_FILE = "dsaConfig.xml";

	static private final String CONF_SUB_DIR = "/dsa/";

	/**
	 * �������ļ��еĽڵ��ʶ
	 */
	static private final String KEY_TOKEN_TEXT = "key";

	static private final String KEY_NAME_TEXT = "name";

	static private final String PUBKEY_FILE_TEXT = "pubKey";

	static private final String PRIKEY_FILE_TEXT = "priKey";

	private DSA dsa;

	/**
	 * ��������ļ���ȡ��˽Կ��Ϣ�б�
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<KeyInfoElement> getKeyInformation() {
		ArrayList<KeyInfoElement> keyInfoList = new ArrayList<KeyInfoElement>();
		InputStream inputStream = this.getClass().getResourceAsStream(
				CONF_SUB_DIR + KEY_CONFIG_FILE);
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(inputStream);
			if (doc == null) {
				throw new Exception("Bad dsa configuration file: doc is null");
			}

			Element root = doc.getRootElement();
			if (root == null) {
				throw new Exception("Bad dsa configuration file: root is null");
			}

			String tempKeyName = null;
			String tempPubKey = null;
			String tempPriKey = null;
			for (Iterator i = root.elementIterator(KEY_TOKEN_TEXT); i.hasNext();) {
				Element element = (Element) i.next();
				if (element == null) {
					continue;
				}

				tempKeyName = element.elementText(KEY_NAME_TEXT);
				tempPubKey = element.elementText(PUBKEY_FILE_TEXT);
				if (tempPubKey != null) {
					tempPubKey = CONF_SUB_DIR + tempPubKey;
				}

				tempPriKey = element.elementText(PRIKEY_FILE_TEXT);
				if (tempPriKey != null) {
					tempPriKey = CONF_SUB_DIR + tempPriKey;
				}

				KeyInfoElement keyInfoElement = KeyInfoElement
						.createKeyInfoElement(tempKeyName, tempPubKey,
								tempPriKey);

				if (keyInfoElement != null) {
					keyInfoList.add(keyInfoElement);
				}
			}
		} catch (Exception ex) {
			logger.error(
					"exception in getKeyInformation: "
							+ LogUtil.parserBean(keyInfoList), ex);
		}

		return keyInfoList;
	}

	/**
	 * ��ʼ��service��
	 * 
	 * @throws ServiceInitializationException
	 *             ����ʼ��ʧ��
	 */
	@SuppressWarnings("rawtypes")
	public void init() throws ServiceInitializationException {
		dsa = new DSA();
		List<KeyInfoElement> keyInfoList = getKeyInformation();

		if (keyInfoList != null) {
			String pubKeyFileName = null;
			String priKeyFileName = null;
			String keyPairName = null;

			// ��������ÿ�� keyPairName ��Ӧ�Ĺ�˽Կ��ֻ���ȡһ��
			Set<String> keys = new HashSet<String>();

			for (Iterator i = keyInfoList.iterator(); i.hasNext();) {
				KeyInfoElement keyInfoElement = (KeyInfoElement) i.next();

				keyPairName = keyInfoElement.getKeyPairName();
				pubKeyFileName = keyInfoElement.getPubKeyFileName();
				priKeyFileName = keyInfoElement.getPriKeyFileName();

				if (StringUtil.isNotEmpty(keyPairName)
						&& !keys.contains(keyPairName)) {
					keys.add(keyPairName);
					logger.debug("Reading DSA key pair: name=" + keyPairName);

					if (pubKeyFileName != null) {
						logger.debug("public key file: " + pubKeyFileName);
					}

					if (priKeyFileName != null) {
						logger.debug("private key file: " + priKeyFileName);
					}

					if (pubKeyFileName != null) {
						try {
							dsa.setPublicKey(keyPairName, this.getClass()
									.getResourceAsStream(pubKeyFileName));
						} catch (DSAException e) {
							throw new ServiceInitializationException(
									"Failed to read public key file: "
											+ pubKeyFileName, e);
						} catch (IOException e) {
							throw new ServiceInitializationException(
									"Failed to read public key file: "
											+ pubKeyFileName, e);
						}
					}

					if (priKeyFileName != null) {
						try {
							dsa.setPrivateKey(keyPairName, this.getClass()
									.getResourceAsStream(priKeyFileName));
						} catch (DSAException e) {
							throw new ServiceInitializationException(
									"Failed to read private key file: "
											+ priKeyFileName, e);
						} catch (IOException e) {
							throw new ServiceInitializationException(
									"Failed to read public key file: "
											+ pubKeyFileName, e);
						}
					}
				}
			}
		}
	}

	public String sign(String content, String keyPairName, String charset)
			throws NoSuchKeyPairException, DSAException {
		return dsa.sign(content, keyPairName, charset);
	}

	public String sign(String content, String keyPairName)
			throws NoSuchKeyPairException, DSAException {
		return dsa.sign(content, keyPairName);
	}

	public boolean check(String content, String signature, String keyPairName,
			String charset) throws NoSuchKeyPairException, DSAException {
		return dsa.check(content, signature, keyPairName, charset);
	}

	public boolean check(String content, String signature, String keyPairName)
			throws NoSuchKeyPairException, DSAException {
		return dsa.check(content, signature, keyPairName);
	}

}
