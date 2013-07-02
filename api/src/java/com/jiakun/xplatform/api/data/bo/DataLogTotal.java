package com.jiakun.xplatform.api.data.bo;

import java.util.Date;

import com.jiakun.xplatform.framework.bo.SearchInfo;

/**
 * 
 * @author xujiakun
 * 
 */
public class DataLogTotal extends SearchInfo {

	private static final long serialVersionUID = 1525621712711723826L;

	private Long dataLogTotalId;

	private Long dataConfigId;

	private String userName;

	private String tableName;

	private Date createDate;

	private String userId;

	private String primaryKey;

	/**
	 * U or D.
	 */
	private String flag;

	private Date modifyDate;

	private int total;

	public Long getDataLogTotalId() {
		return dataLogTotalId;
	}

	public void setDataLogTotalId(Long dataLogTotalId) {
		this.dataLogTotalId = dataLogTotalId;
	}

	public Long getDataConfigId() {
		return dataConfigId;
	}

	public void setDataConfigId(Long dataConfigId) {
		this.dataConfigId = dataConfigId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
