package com.jiakun.xplatform.framework.bo;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

import org.apache.log4j.Logger;

public class SAPConnectionBean {

	private static Logger logger = Logger.getLogger(SAPConnectionBean.class);

	private String poolName;

	private int maximumConnectionCount;

	private String clientName;

	private String user;

	private String password;

	private String language;

	private String hostName;

	private String sysnr;

	// Name of the group of application servers
	private String group;

	private String byGroup;

	private String repositoryName;

	private String funcName;

	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public SAPConnectionBean() {

	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getMaximumConnectionCount() {
		return maximumConnectionCount;
	}

	public void setMaximumConnectionCount(int maximumConnectionCount) {
		this.maximumConnectionCount = maximumConnectionCount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public String getSysnr() {
		return sysnr;
	}

	public void setSysnr(String sysnr) {
		this.sysnr = sysnr;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getByGroup() {
		return byGroup;
	}

	public void setByGroup(String byGroup) {
		this.byGroup = byGroup;
	}

	// �����ӳ��л�ȡSAP�ͻ�������
	@SuppressWarnings("deprecation")
	public JCO.Client getSAPClientFromPool() {
		logger.debug("����getSAPClientFromPool����");
		JCO.Client sapclient = null;
		try {
			// �ж����ӳ��Ƿ����
			JCO.Pool pool = null;
			pool = JCO.getClientPoolManager().getPool(poolName);
			if (pool == null) {
				// δ�ҵ�ָ�������ӳ�������һ�����ӳ�
				addClientPool();
				pool = JCO.getClientPoolManager().getPool(poolName);
			}
			int maxConnections = pool.getMaxConnections();
			int numUserd = pool.getNumUsed();
			if (maxConnections - numUserd < 1) {
				logger.debug("�̳߳���û�п��õĿͻ������ӣ�����ֱ�Ӵ������Ӻ���getSAPClientDirect()");
				sapclient = this.getSAPClientDirect();
			} else {
				logger.debug("����" + SAPConnectionBean.class + "�еķ���getSAPClientFromPool()���̳߳��л�ȡSAP�ͻ�������");
				sapclient = JCO.getClient(poolName);
			}
		} catch (JCO.Exception e) {
			throw new JCO.Exception(1, "t", e.getMessage());
			// logger.error("����" + SAPConnectionBean.class
			// + "�ķ���getSAPClientFromPool���?" + e.getMessage());
		}
		return sapclient;
	}

	// ֱ�ӻ�ȡSAP�ͻ�������
	public JCO.Client getSAPClientDirect() {
		JCO.Client sapclient = null;
		try {
			if ("1".equals(this.byGroup)) {
				sapclient = JCO.createClient(clientName,// SAP logon client
					user,// SAP logon user
					password,// SAP logon password
					language,// SAP logon language
					hostName,// Host name of the message server
					sysnr,// Name of the SAP system
					group// Name of the group of application servers
					);
			} else {
				sapclient = JCO.createClient(clientName, user, password, language, hostName, sysnr);
			}
			sapclient.connect();
		} catch (JCO.Exception e) {
			throw new RuntimeException("SAP���Ӵ���" + e.getMessage());
		}
		return sapclient;
	}

	// ����ķ�ʽֱ������SAP
	public JCO.Client getSAPClientDirectByGroup() {
		JCO.Client sapclient = null;
		try {
			sapclient = JCO.createClient(clientName,// SAP logon client
				user,// SAP logon user
				password,// SAP logon password
				language,// SAP logon language
				hostName,// Host name of the message server
				sysnr,// Name of the SAP system
				group// Name of the group of application servers
				);
			sapclient.connect();
		} catch (JCO.Exception e) {
			throw new RuntimeException("SAP���Ӵ���" + e.getMessage());
		}
		return sapclient;
	}

	public JCO.Function getFunction() {
		logger.debug("����getFunction����");
		if (this.funcName == null || this.funcName.equals("")) {
			return null;
		}
		JCO.Function func = null;
		try {
			IFunctionTemplate ft = this.getRepository().getFunctionTemplate(funcName);
			func = ft.getFunction();
		} catch (JCO.Exception e) {
			logger.error("����" + SAPConnectionBean.class + "�ķ���getFunction���?" + e.getMessage(), e);
		}
		return func;
	}

	public JCO.Function getFunction(JCO.Client client) {
		logger.debug("����getFunction(client)����,����SAP�ӿ�" + funcName);
		if (this.funcName == null || this.funcName.equals("")) {
			return null;
		}
		JCO.Function func = null;
		try {
			JCO.Repository repository = new JCO.Repository(this.repositoryName, client);
			IFunctionTemplate ft = repository.getFunctionTemplate(funcName);
			func = ft.getFunction();
		} catch (JCO.Exception e) {
			logger.error("����" + SAPConnectionBean.class + "�ķ���getFunction���?" + e.getMessage(), e);
		}
		return func;
	}

	// ��ȡSAP��repository
	@SuppressWarnings("deprecation")
	private JCO.Repository getRepository() {
		logger.debug("����getRepository����");
		JCO.Repository repository = null;
		try {
			JCO.Pool pool = JCO.getClientPoolManager().getPool(poolName);
			if (pool == null) {
				// δ�ҵ�ָ�������ӳ�������һ�����ӳ�
				addClientPool();
			}
			logger.debug("����" + SAPConnectionBean.class + "�з���getRepository��ȡSAP����ֿ�");
			repository = new JCO.Repository(this.repositoryName, this.poolName);
		} catch (JCO.Exception e) {
			logger.error("����" + SAPConnectionBean.class + "�ķ���getRepository���?" + e.getMessage(), e);
		}
		return repository;
	}

	@SuppressWarnings("deprecation")
	private void addClientPool() {
		logger.debug("���̳߳�" + this.poolName + "������һ���µ�SAP�ͻ�������");
		try {
			JCO.addClientPool(poolName, maximumConnectionCount, clientName, user, password, language, hostName, sysnr);
		} catch (JCO.Exception e) {
			throw new RuntimeException("SAP���Ӵ���" + e.getMessage());
		}

	}
}
