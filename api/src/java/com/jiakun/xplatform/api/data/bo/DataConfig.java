package com.jiakun.xplatform.api.data.bo;

import java.util.Date;

import com.jiakun.xplatform.framework.bo.SearchInfo;

/**
 * ��ݲ�������
 * 
 * @author xujiakun
 * 
 */
public class DataConfig extends SearchInfo {

	private static final long serialVersionUID = 880248670893730552L;

	private Long dataConfigId;

	private String userId;

	private String userName;

	/**
	 * �ֵ�id
	 */
	private Long itemId;

	private String tableName;

	private String sequenceValue;

	/**
	 * file ״̬ - U:��Ч; D:ɾ��;
	 */
	private String flag;

	private Date createDate;

	private Date modifyDate;

	private String primaryKey;

	public Long getDataConfigId() {
		return dataConfigId;
	}

	public void setDataConfigId(Long dataConfigId) {
		this.dataConfigId = dataConfigId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSequenceValue() {
		return sequenceValue;
	}

	public void setSequenceValue(String sequenceValue) {
		this.sequenceValue = sequenceValue;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

}
