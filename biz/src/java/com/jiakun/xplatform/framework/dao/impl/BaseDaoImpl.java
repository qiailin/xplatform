package com.jiakun.xplatform.framework.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.jiakun.xplatform.framework.dao.IBaseDao;

public class BaseDaoImpl extends SqlMapClientDaoSupport implements IBaseDao {

	/**
	 * sql������namespace
	 */
	protected String namespace;

	private final static String PFIX = ",";

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	/**
	 * ��list��valueƴװΪ value1��value2��value3 ��ʽ
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private String listToString(List list) {
		StringBuffer sb = new StringBuffer();
		if (list != null) {
			int i = 0;
			for (Object id : list) {
				if (i > 0) {
					sb.append(PFIX + id);
				} else {
					sb.append(id);
				}
				i++;
			}
		}
		return sb.toString();
	}

}
