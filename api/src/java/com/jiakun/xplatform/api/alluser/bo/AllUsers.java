package com.jiakun.xplatform.api.alluser.bo;

import java.util.Arrays;
import java.util.Date;

import com.jiakun.xplatform.framework.bo.SearchInfo;

/**
 * user.
 * 
 * @author xujiakun
 * 
 */
public class AllUsers extends SearchInfo {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String passWd;

	/**
	 * emp_name.
	 */
	private String userName;

	/**
	 * emp_showname.
	 */
	private String userShowName;

	/**
	 * EMP_STATE.
	 */
	private String userState;

	private String custType;

	/**
	 * emp_code.
	 */
	private String loginId;

	/**
	 * emp_mobile_phone.
	 */
	private String mobile;

	/**
	 * emp_shortmsgbook.
	 */
	private String empShortmsgbook;

	private String smsUserTypeId;

	private Long chargeId;

	/**
	 * emp_phone.
	 */
	private String phone;

	/**
	 * bus_mobilephone.
	 */
	private String busMobilephone;

	private String hqSign;

	private String kunnrSign;

	private String isOffice;

	private Date paswdSignDate;

	private String orgId;

	private String orgName;

	/**
	 * emp_id_card.
	 */
	private String idCard;

	/**
	 * emp_workfax.
	 */
	private String workFax;

	/**
	 * emp_homephone.
	 */
	private String homePhone;

	/**
	 * emp_start_date.
	 */
	private String startDate;

	/**
	 * emp_address.
	 */
	private String address;

	/**
	 * emp_email.
	 */
	private String email;

	/**
	 * emp_sex.
	 */
	private String sex;

	/**
	 * pos_name.
	 */
	private String posName;

	private String posId;

	/**
	 * havemail.
	 */
	private String haveMail;

	/**
	 * emp_remark.
	 */
	private String remark;

	/**
	 * position_type_name.
	 */
	private String positionTypeName;

	private String[] orgIds;

	private Long id;

	private String orgStr;

	private String supplierNumber;

	private String questionLinkId;

	private String reason;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
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

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHqSign() {
		return hqSign;
	}

	public void setHqSign(String hqSign) {
		this.hqSign = hqSign;
	}

	public String getKunnrSign() {
		return kunnrSign;
	}

	public void setKunnrSign(String kunnrSign) {
		this.kunnrSign = kunnrSign;
	}

	public String getIsOffice() {
		return isOffice;
	}

	public void setIsOffice(String isOffice) {
		this.isOffice = isOffice;
	}

	public Date getPaswdSignDate() {
		return paswdSignDate != null ? (Date) paswdSignDate.clone() : null;
	}

	public void setPaswdSignDate(Date paswdSignDate) {
		if (paswdSignDate != null) {
			this.paswdSignDate = (Date) paswdSignDate.clone();
		}
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getWorkFax() {
		return workFax;
	}

	public void setWorkFax(String workFax) {
		this.workFax = workFax;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getHaveMail() {
		return haveMail;
	}

	public void setHaveMail(String haveMail) {
		this.haveMail = haveMail;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPositionTypeName() {
		return positionTypeName;
	}

	public void setPositionTypeName(String positionTypeName) {
		this.positionTypeName = positionTypeName;
	}

	public String[] getOrgIds() {
		return orgIds != null ? Arrays.copyOf(orgIds, orgIds.length) : null;
	}

	public void setOrgIds(String[] orgIds) {
		if (orgIds != null) {
			this.orgIds = Arrays.copyOf(orgIds, orgIds.length);
		}
	}

	public String getBusMobilephone() {
		return busMobilephone;
	}

	public void setBusMobilephone(String busMobilephone) {
		this.busMobilephone = busMobilephone;
	}

	public String getEmpShortmsgbook() {
		return empShortmsgbook;
	}

	public void setEmpShortmsgbook(String empShortmsgbook) {
		this.empShortmsgbook = empShortmsgbook;
	}

	public String getSmsUserTypeId() {
		return smsUserTypeId;
	}

	public void setSmsUserTypeId(String smsUserTypeId) {
		this.smsUserTypeId = smsUserTypeId;
	}

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgStr() {
		return orgStr;
	}

	public void setOrgStr(String orgStr) {
		this.orgStr = orgStr;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getQuestionLinkId() {
		return questionLinkId;
	}

	public void setQuestionLinkId(String questionLinkId) {
		this.questionLinkId = questionLinkId;
	}

	public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
