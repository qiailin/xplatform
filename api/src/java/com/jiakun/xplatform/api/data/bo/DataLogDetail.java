package com.jiakun.xplatform.api.data.bo;

import java.util.Date;

/**
 * 
 * @author xujiakun
 * 
 */
public class DataLogDetail {

	private Long dataLogDetailId;

	private Long dataLogTotalId;

	/**
	 * table sequence.
	 */
	private String dataId;

	private Date createDate;

	public Long getDataLogDetailId() {
		return dataLogDetailId;
	}

	public void setDataLogDetailId(Long dataLogDetailId) {
		this.dataLogDetailId = dataLogDetailId;
	}

	public Long getDataLogTotalId() {
		return dataLogTotalId;
	}

	public void setDataLogTotalId(Long dataLogTotalId) {
		this.dataLogTotalId = dataLogTotalId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
