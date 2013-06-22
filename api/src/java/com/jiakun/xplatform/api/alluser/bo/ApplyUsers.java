package com.jiakun.xplatform.api.alluser.bo;

import java.util.Date;

import com.jiakun.xplatform.framework.bo.SearchInfo;

/**
 * SMSUSER.APPLYUSERS
 * 
 * @author xujiakun
 * 
 */
public class ApplyUsers extends SearchInfo {

	private static final long serialVersionUID = -6103728926926948913L;

	private Long id;

	/**
	 * �û�id
	 */
	private String userCode;

	/**
	 * ����
	 */
	private String pwd;

	/**
	 * �û�����
	 */
	private String userName;

	/**
	 * ��ʾ��
	 */
	private String userShowName;

	/**
	 * ��ʾ��
	 */
	private String workPhone;

	/**
	 * �칫����
	 */
	private String workFax;

	/**
	 * �ֻ�
	 */
	private String mobilePhone;

	/**
	 * լ��
	 */
	private String homePhone;

	/**
	 * ��ַ
	 */
	private String address;

	/**
	 * �����ַ
	 */
	private String mailAddress;

	/**
	 * �Ա�M��FŮ��
	 */
	private String sex;

	/**
	 * �Ƿ�ͨ���䣨1��ͨ0����ͨ��
	 */
	private String haveMail;

	/**
	 * ��֯id
	 */
	private Long organiseId;

	/**
	 * ������id
	 */
	private Long squserId;

	/**
	 * ����������
	 */
	private String squserShowName;

	/**
	 * �������(Fδ���T�����D�˻�)
	 */
	private String hrsh;

	/**
	 * ��Ϣ�����(Fδ���T�����D�˻�)
	 */
	private String infosh;

	/**
	 * ��ע
	 */
	private String remark;

	/**
	 *  ��ְλID
	 */
	private String stationId;

	/**
	 * ����ʱ��
	 */
	private Date createTime;

	/**
	 * �ܾ�ԭ��
	 */
	private String jjReason;

	/**
	 * �޸�ʱ��
	 */
	private Date lastModify;

	/**
	 * ���֤����
	 */
	private String empIdCard;

	/**
	 * ��ʼ��������
	 */
	private String empStartDate;

	private String beginDate;

	private String endDate;

	private String[] orgIds;

	private  String orgStr;
	
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
		this.orgIds = orgIds;
	}

	public String getOrgStr() {
		return orgStr;
	}

	public void setOrgStr(String orgStr) {
		this.orgStr = orgStr;
	}

}
