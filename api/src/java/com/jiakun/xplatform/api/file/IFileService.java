package com.jiakun.xplatform.api.file;

import java.util.List;

import com.jiakun.xplatform.api.file.bo.FileInfo;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IFileService {

	String FILE_FLAG_USED = "U";

	String FILE_FLAG_DELETED = "D";

	String ERROR_MESSAGE = "����ʧ�ܣ�";

	String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

	/**
	 * getFileInfo.
	 * 
	 * @param fileInfo
	 * @return
	 */
	FileInfo getFileInfo(FileInfo fileInfo);

	/**
	 * createFileInfo.
	 * 
	 * @param fileInfo
	 * @return
	 */
	BooleanResult createFileInfo(FileInfo fileInfo);

	/**
	 * 
	 * @param fileInfo
	 * @return
	 */
	int getFileCount(FileInfo fileInfo);

	/**
	 * 
	 * @param fileInfo
	 * @return
	 */
	List<FileInfo> getFileList(FileInfo fileInfo);

	/**
	 * deleteFile.
	 * 
	 * @param fileInfo
	 * @return
	 */
	BooleanResult deleteFile(FileInfo fileInfo);

}
