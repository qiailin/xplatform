package com.jiakun.xplatform.api.file.bo;

import java.util.Date;

import com.jiakun.xplatform.framework.bo.SearchInfo;

/**
 * 
 * @author xujiakun
 * 
 */
public class FileInfo extends SearchInfo {

	private static final long serialVersionUID = 6240425668306639391L;

	/**
	 * file Ψһ���
	 */
	private String fileId;

	private String fileName;

	/**
	 * txt jpg ...
	 */
	private String suffix;

	/**
	 * hdfs ����·��
	 */
	private String filePath;

	/**
	 * file ״̬ - U:��Ч; D:ɾ��;
	 */
	private String flag;

	private Date createDate;

	private Date modifyDate;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

}
