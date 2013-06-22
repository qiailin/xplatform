package com.jiakun.xplatform.file.dao;

import java.util.List;

import com.jiakun.xplatform.api.file.bo.FileInfo;

public interface IFileDao {

	/**
	 * 
	 * @param fileInfo
	 * @return
	 */
	public FileInfo getFileInfo(FileInfo fileInfo);

	/**
	 * 
	 * @param fileInfo
	 * @return
	 */
	public String createFileInfo(FileInfo fileInfo);

	/**
	 * 
	 * @param fileInfo
	 * @return
	 */
	public int getFileCount(FileInfo fileInfo);

	/**
	 * 
	 * @param fileInfo
	 * @return
	 */
	public List<FileInfo> getFileList(FileInfo fileInfo);

	/**
	 * 
	 * @param fileInfo
	 * @return
	 */
	public int deleteFile(FileInfo fileInfo);

}
