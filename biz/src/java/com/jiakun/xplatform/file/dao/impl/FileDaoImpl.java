package com.jiakun.xplatform.file.dao.impl;

import java.util.List;

import com.jiakun.xplatform.api.file.bo.FileInfo;
import com.jiakun.xplatform.file.dao.IFileDao;
import com.jiakun.xplatform.framework.dao.impl.BaseDaoImpl;

public class FileDaoImpl extends BaseDaoImpl implements IFileDao {

	public FileInfo getFileInfo(FileInfo fileInfo) {
		return (FileInfo) getSqlMapClientTemplate().queryForObject(
				"file.getFileInfo", fileInfo);
	}

	public String createFileInfo(FileInfo fileInfo) {
		return (String) getSqlMapClientTemplate().insert("file.createFileInfo",
				fileInfo);
	}

	public int getFileCount(FileInfo fileInfo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"file.getFileCount", fileInfo);
	}

	@SuppressWarnings("unchecked")
	public List<FileInfo> getFileList(FileInfo fileInfo) {
		return (List<FileInfo>) getSqlMapClientTemplate().queryForList(
				"file.getFileList", fileInfo);
	}

	public int deleteFile(FileInfo fileInfo) {
		return getSqlMapClientTemplate().update("file.deleteFile", fileInfo);
	}

}
