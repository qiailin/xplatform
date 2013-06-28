package com.jiakun.xplatform.framework.bo;

import java.io.Serializable;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.framework.annotation.Decode;

/**
 * SearchInfo
 * 
 * @author xujiakun
 * 
 */
public class SearchInfo implements Serializable {

	public static final String DIR_DESC = "DESC";

	public static final String DIR_ASC = "ASC";

	private static final long serialVersionUID = 235499309845516885L;

	/**
	 * ��ʼʱ��
	 */
	private String gmtStart;

	/**
	 * ����ʱ��
	 */
	private String gmtEnd;

	/**
	 * �ؼ��
	 */
	private String name;

	/**
	 * ���
	 */
	private String code;

	private String[] codes;

	/**
	 * ����ʹ�õ�����ֵ�������ֶ�
	 */
	@Decode
	private String search;

	/**
	 * ÿҳ��ʾ��¼��
	 */
	private int limit = 10;

	/**
	 * ��ǰ��¼�±꣬��0��ʼ
	 */
	private int start;

	/**
	 * �����ֶ�
	 */
	private String sort;

	/**
	 * ��������
	 */
	private String dir;

	/**
	 * ��������
	 */
	private String filter;

	/**
	 * �������
	 */
	private String order;

	/**
	 * ���ؽ������
	 */
	private long totalRows;

	/**
	 * ���ؽ��
	 */
	@SuppressWarnings("rawtypes")
	private List resultList;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start + 1;
	}

	public int getEnd() {
		return this.start + limit;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	@SuppressWarnings("rawtypes")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getGmtStart() {
		return gmtStart;
	}

	public void setGmtStart(String gmtStart) {
		this.gmtStart = gmtStart;
	}

	public String getGmtEnd() {
		return gmtEnd;
	}

	public void setGmtEnd(String gmtEnd) {
		this.gmtEnd = gmtEnd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtil.deleteWhitespace(name);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = StringUtil.deleteWhitespace(code);
	}

	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = StringUtil.deleteWhitespace(search);
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
