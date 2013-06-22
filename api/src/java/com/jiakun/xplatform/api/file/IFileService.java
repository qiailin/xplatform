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

	public static final String FILE_FLAG_USED = "U";

	public static final String FILE_FLAG_DELETED = "D";

	public static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	public static final String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

	/**
	 * ��ȡ�ļ���Ϣ
	 * 
	 * @param fileInfo
	 * @return
	 */
	public FileInfo getFileInfo(FileInfo fileInfo);

	/**
	 * �����ļ���Ϣ
	 * 
	 * @param fileInfo
	 * @return
	 */
	public BooleanResult createFileInfo(FileInfo fileInfo);

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
	 * ɾ���ļ�
	 * 
	 * @param fileInfo
	 * @return
	 */
	public BooleanResult deleteFile(FileInfo fileInfo);

}
