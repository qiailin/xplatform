package com.jiakun.xplatform.api.system.bo;

/**
 * ��ʹ�÷ǶԳƼ��ܷ�ʽ���м��ܡ�ǩ����֤�Ȳ���ʱ��������ȡ��˽Կ�Ե�model
 * 
 * @author
 * 
 */
public class KeyInfoElement {

	/**
	 * ��˽Կ�Ե�����
	 */
	private String keyPairName;

	/**
	 * ��Ź�Կ���ļ�·��
	 */
	private String pubKeyFileName;

	/**
	 * ���˽Կ���ļ�·��
	 */
	private String priKeyFileName;

	public String getKeyPairName() {
		return keyPairName;
	}

	public void setKeyPairName(String keyPairName) {
		this.keyPairName = keyPairName;
	}

	public String getPriKeyFileName() {
		return priKeyFileName;
	}

	public void setPriKeyFileName(String priKeyFileName) {
		this.priKeyFileName = priKeyFileName;
	}

	public String getPubKeyFileName() {
		return pubKeyFileName;
	}

	public void setPubKeyFileName(String pubKeyFileName) {
		this.pubKeyFileName = pubKeyFileName;
	}

	private KeyInfoElement(String keyPairName, String pubKeyFileName,
			String priKeyFileName) {
		super();
		this.keyPairName = keyPairName;
		this.pubKeyFileName = pubKeyFileName;
		this.priKeyFileName = priKeyFileName;
	}

	static public KeyInfoElement createKeyInfoElement(String keyPairName,
			String pubKeyFileName, String priKeyFileName) {
		if (keyPairName == null) {
			return null;
		}
		return new KeyInfoElement(keyPairName, pubKeyFileName, priKeyFileName);
	}

}
