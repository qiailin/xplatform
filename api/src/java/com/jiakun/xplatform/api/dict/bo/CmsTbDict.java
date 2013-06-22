package com.jiakun.xplatform.api.dict.bo;

import java.util.Date;

import com.jiakun.xplatform.framework.bo.SearchInfo;

/**
 * �ֵ䌦��
 * 
 */
public class CmsTbDict extends SearchInfo {

	private static final long serialVersionUID = -31231206571094321L;

	/**
	 * �ֵ���ID
	 */
	private Long itemId;

	/**
	 * �ֵ�����ID
	 */
	private Long dictTypeId;

	/**
	 * �ֵ����ID
	 */
	private Long parentItemId;

	/**
	 * �ֵ������
	 */
	private String itemName;

	/**
	 * �ֵ�������
	 */
	private String itemDescription;

	/**
	 * ֵ
	 */
	private String itemValue;

	/**
	 * ��ע
	 */
	private String remark;

	/**
	 * ״̬
	 */
	private String itemState;

	private Date lastModify;

	/**
	 * �ƷѶ�ӦID
	 */
	private Long chargeId;

	/**
	 * Ӧ�ö�����ȼ�
	 */
	private Long appobjectLevel;

	private Date modifyDate;

	private CmsTbDictType cmsTbDictType;

	// 2012-03-16 anwang���5���ֶ�����ְλ����
	/**
	 * �涨�ܴ���
	 */
	private String tot;

	/**
	 * Э�ô���
	 */
	private String xf;

	/**
	 * ��ͳ������
	 */
	private String ct;

	/**
	 * KA������
	 */
	private String ka;

	/**
	 * �ֵ��������
	 */
	private String dictTypeName;

	private String dictTypeValue;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(Long dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public Long getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(Long parentItemId) {
		this.parentItemId = parentItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getItemState() {
		return itemState;
	}

	public void setItemState(String itemState) {
		this.itemState = itemState;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	public Long getAppobjectLevel() {
		return appobjectLevel;
	}

	public void setAppobjectLevel(Long appobjectLevel) {
		this.appobjectLevel = appobjectLevel;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public CmsTbDictType getCmsTbDictType() {
		return cmsTbDictType;
	}

	public void setCmsTbDictType(CmsTbDictType cmsTbDictType) {
		this.cmsTbDictType = cmsTbDictType;
	}

	public String getTot() {
		return tot;
	}

	public void setTot(String tot) {
		this.tot = tot;
	}

	public String getXf() {
		return xf;
	}

	public void setXf(String xf) {
		this.xf = xf;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getKa() {
		return ka;
	}

	public void setKa(String ka) {
		this.ka = ka;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getDictTypeValue() {
		return dictTypeValue;
	}

	public void setDictTypeValue(String dictTypeValue) {
		this.dictTypeValue = dictTypeValue;
	}

}