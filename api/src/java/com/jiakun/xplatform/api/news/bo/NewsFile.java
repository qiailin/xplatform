package com.jiakun.xplatform.api.news.bo;

import java.util.Date;

import com.jiakun.xplatform.framework.bo.SearchInfo;

public class NewsFile extends SearchInfo {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private Long news_file_id;

	/**
	 * �ļ���
	 */
	private String news_file_name;

	/**
	 * ��ϸ��id
	 */
	private Long detail_id;

	/**
	 * �ļ�·��
	 */
	private String news_file_url;

	/**
	 * ��������
	 */
	private Date create_date;

	/**
	 * �޸�����
	 */
	private Date last_modify;

	/**
	 * Y:��Nɾ��
	 */
	private String news_del_flag;

	public Long getNews_file_id() {
		return news_file_id;
	}

	public void setNews_file_id(Long news_file_id) {
		this.news_file_id = news_file_id;
	}

	public String getNews_file_name() {
		return news_file_name;
	}

	public void setNews_file_name(String news_file_name) {
		this.news_file_name = news_file_name;
	}

	public Long getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}

	public String getNews_file_url() {
		return news_file_url;
	}

	public void setNews_file_url(String news_file_url) {
		this.news_file_url = news_file_url;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getLast_modify() {
		return last_modify;
	}

	public void setLast_modify(Date last_modify) {
		this.last_modify = last_modify;
	}

	public String getNews_del_flag() {
		return news_del_flag;
	}

	public void setNews_del_flag(String news_del_flag) {
		this.news_del_flag = news_del_flag;
	}

}