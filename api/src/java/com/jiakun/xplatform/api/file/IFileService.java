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

	static final String FILE_FLAG_USED = "U";

	static final String FILE_FLAG_DELETED = "D";

	static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	static final String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

	/**
	 * ��ȡ�ļ���Ϣ
	 * 
	 * @param fileInfo
	 * @return
	 */
	FileInfo getFileInfo(FileInfo fileInfo);

	/**
	 * �����ļ���Ϣ
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
	 * ɾ���ļ�
	 * 
	 * @param fileInfo
	 * @return
	 */
	BooleanResult deleteFile(FileInfo fileInfo);

}
