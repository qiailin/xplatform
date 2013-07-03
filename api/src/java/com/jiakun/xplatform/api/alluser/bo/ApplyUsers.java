package com.jiakun.xplatform.api.alluser.bo;

import java.util.Arrays;
import java.util.Date;

import com.jiakun.xplatform.framework.bo.SearchInfo;

/**
 * SMSUSER.APPLYUSERS.
 * 
 * @author xujiakun
 * 
 */
public class ApplyUsers extends SearchInfo {

	private static final long serialVersionUID = -6103728926926948913L;

	private Long id;

	private String userCode;

	private String pwd;

	private String userName;

	private String userShowName;

	private String workPhone;

	private String workFax;

	private String mobilePhone;

	private String homePhone;

	private String address;

	private String mailAddress;

	private String sex;

	private String haveMail;

	private Long organiseId;

	private Long squserId;

	private String squserShowName;

	private String hrsh;

	private String infosh;

	private String remark;

	private String stationId;

	private Date createTime;

	private String jjReason;

	private Date lastModify;

	private String empIdCard;

	private String empStartDate;

	private String beginDate;

	private String endDate;

	private String[] orgIds;

	private String orgStr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserShowName() {
		return userShowName;
	}

	public void setUserShowName(String userShowName) {
		this.userShowName = userShowName;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getWorkFax() {
		return workFax;
	}

	public void setWorkFax(String workFax) {
		this.workFax = workFax;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHaveMail() {
		return haveMail;
	}

	public void setHaveMail(String haveMail) {
		this.haveMail = haveMail;
	}

	public Long getOrganiseId() {
		return organiseId;
	}

	public void setOrganiseId(Long organiseId) {
		this.organiseId = organiseId;
	}

	public Long getSquserId() {
		return squserId;
	}

	public void setSquserId(Long squserId) {
		this.squserId = squserId;
	}

	public String getSquserShowName() {
		return squserShowName;
	}

	public void setSquserShowName(String squserShowName) {
		this.squserShowName = squserShowName;
	}

	public String getHrsh() {
		return hrsh;
	}

	public void setHrsh(String hrsh) {
		this.hrsh = hrsh;
	}

	public String getInfosh() {
		return infosh;
	}

	public void setInfosh(String infosh) {
		this.infosh = infosh;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getJjReason() {
		return jjReason;
	}

	public void setJjReason(String jjReason) {
		this.jjReason = jjReason;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public String getEmpIdCard() {
		return empIdCard;
	}

	public void setEmpIdCard(String empIdCard) {
		this.empIdCard = empIdCard;
	}

	public String getEmpStartDate() {
		return empStartDate;
	}

	public void setEmpStartDate(String empStartDate) {
		this.empStartDate = empStartDate;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String[] getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String[] orgIds) {
		if (orgIds != null) {
			this.orgIds = Arrays.copyOf(orgIds, orgIds.length);
		}
	}

	public String getOrgStr() {
		return orgStr;
	}

	public void setOrgStr(String orgStr) {
		this.orgStr = orgStr;
	}

}
