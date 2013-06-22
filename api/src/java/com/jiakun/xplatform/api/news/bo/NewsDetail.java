package com.jiakun.xplatform.api.news.bo;

import java.util.Date;

import com.jiakun.xplatform.framework.bo.SearchInfo;

public class NewsDetail extends SearchInfo {

	private static final long serialVersionUID = 1L;

	/**
	 * ��ϸ��id
	 */
	private Long delail_id;

	/**
	 * �ܱ�id
	 */
	private Long total_id;

	/**
	 * ����
	 */
	private String delail_title;

	/**
	 * ����
	 */
	private String delail_content;

	/**
	 * ������
	 */
	private String delail_operator;

	/**
	 * ��������
	 */
	private Date detail_date;

	/**
	 * �����
	 */
	private Long clicks_ratio;

	/**
	 * ������֯
	 */
	private String org_name;

	/**
	 * ɾ��״̬��Y����N��ɾ��
	 */
	private String delail_flag;

	/**
	 * �޸�����
	 */
	private Date last_modify;

	/**
	 * YΪ����css��NΪû��
	 */
	private String css_flag;

	private String total_name;

	private String new_flag;

	/**
	 * �Ƿ�Ϊ�����״̬��Y���ǣ�N����
	 */
	private String total_sign;

	public Long getDelail_id() {
		return delail_id;
	}

	public void setDelail_id(Long delail_id) {
		this.delail_id = delail_id;
	}

	public Long getTotal_id() {
		return total_id;
	}

	public void setTotal_id(Long total_id) {
		this.total_id = total_id;
	}

	public String getDelail_title() {
		return delail_title;
	}

	public void setDelail_title(String delail_title) {
		this.delail_title = delail_title;
	}

	public String getDelail_content() {
		return delail_content;
	}

	public void setDelail_content(String delail_content) {
		this.delail_content = delail_content;
	}

	public String getDelail_operator() {
		return delail_operator;
	}

	public void setDelail_operator(String delail_operator) {
		this.delail_operator = delail_operator;
	}

	public Date getDetail_date() {
		return detail_date;
	}

	public void setDetail_date(Date detail_date) {
		this.detail_date = detail_date;
	}

	public Long getClicks_ratio() {
		return clicks_ratio;
	}

	public void setClicks_ratio(Long clicks_ratio) {
		this.clicks_ratio = clicks_ratio;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getDelail_flag() {
		return delail_flag;
	}

	public void setDelail_flag(String delail_flag) {
		this.delail_flag = delail_flag;
	}

	public Date getLast_modify() {
		return last_modify;
	}

	public void setLast_modify(Date last_modify) {
		this.last_modify = last_modify;
	}

	public String getCss_flag() {
		return css_flag;
	}

	public void setCss_flag(String css_flag) {
		this.css_flag = css_flag;
	}

	public String getTotal_name() {
		return total_name;
	}

	public void setTotal_name(String total_name) {
		this.total_name = total_name;
	}

	public String getNew_flag() {
		return new_flag;
	}

	public void setNew_flag(String new_flag) {
		this.new_flag = new_flag;
	}

	public String getTotal_sign() {
		return total_sign;
	}

	public void setTotal_sign(String total_sign) {
		this.total_sign = total_sign;
	}

}
