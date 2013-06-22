package com.jiakun.xplatform.api.news.bo;

import java.util.Date;
import java.util.List;

import com.jiakun.xplatform.framework.bo.SearchInfo;

public class NewsTotal extends SearchInfo {

	private static final long serialVersionUID = 1L;

	/**
	 * ��Ŀid
	 */
	private Long total_id;

	/**
	 * ��ID
	 */
	private Long total_parent_id;

	/**
	 * ��Ŀ���
	 */
	private String total_name;

	/**
	 * ��Ŀ˵��
	 */
	private String total_title;

	/**
	 * ����ʱ��
	 */
	private Date total_date;

	/**
	 * ��Ŀ������
	 */
	private String total_code;

	/**
	 * �Ƿ�����ҳ��ʾ��Y���ǣ�N����
	 */
	private String total_show;

	/**
	 * �Ƿ�Ϊ�����״̬��Y���ǣ�N����
	 */
	private String total_sign;

	/**
	 * ɾ��״̬��Y����N��ɾ��
	 */
	private String total_flag;

	/**
	 * �޸�ʱ��
	 */
	private Date last_modify;

	/**
	 * �ļ��ϴ���ʶ�ֶΣ�Y���ǣ�N����
	 */
	private String total_upload_sign;

	private List<NewsDetail> newsdet_list;

	public Long getTotal_id() {
		return total_id;
	}

	public void setTotal_id(Long total_id) {
		this.total_id = total_id;
	}

	public Long getTotal_parent_id() {
		return total_parent_id;
	}

	public void setTotal_parent_id(Long total_parent_id) {
		this.total_parent_id = total_parent_id;
	}

	public String getTotal_name() {
		return total_name;
	}

	public void setTotal_name(String total_name) {
		this.total_name = total_name;
	}

	public String getTotal_title() {
		return total_title;
	}

	public void setTotal_title(String total_title) {
		this.total_title = total_title;
	}

	public Date getTotal_date() {
		return total_date;
	}

	public void setTotal_date(Date total_date) {
		this.total_date = total_date;
	}

	public String getTotal_code() {
		return total_code;
	}

	public void setTotal_code(String total_code) {
		this.total_code = total_code;
	}

	public String getTotal_show() {
		return total_show;
	}

	public void setTotal_show(String total_show) {
		this.total_show = total_show;
	}

	public String getTotal_sign() {
		return total_sign;
	}

	public void setTotal_sign(String total_sign) {
		this.total_sign = total_sign;
	}

	public String getTotal_flag() {
		return total_flag;
	}

	public void setTotal_flag(String total_flag) {
		this.total_flag = total_flag;
	}

	public Date getLast_modify() {
		return last_modify;
	}

	public void setLast_modify(Date last_modify) {
		this.last_modify = last_modify;
	}

	public String getTotal_upload_sign() {
		return total_upload_sign;
	}

	public void setTotal_upload_sign(String total_upload_sign) {
		this.total_upload_sign = total_upload_sign;
	}

	public List<NewsDetail> getNewsdet_list() {
		return newsdet_list;
	}

	public void setNewsdet_list(List<NewsDetail> newsdet_list) {
		this.newsdet_list = newsdet_list;
	}

}