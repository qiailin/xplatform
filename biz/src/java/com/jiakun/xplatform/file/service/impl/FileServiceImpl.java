package com.jiakun.xplatform.file.service.impl;

import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.api.cache.IMemcachedCacheService;
import com.jiakun.xplatform.api.file.IFileService;
import com.jiakun.xplatform.api.file.bo.FileInfo;
import com.jiakun.xplatform.file.dao.IFileDao;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.framework.exception.ServiceException;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;

/**
 * 
 * @author jiakunxu
 * 
 */
public class FileServiceImpl implements IFileService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(FileServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private IFileDao fileDao;

	public FileInfo getFileInfo(FileInfo fileInfo) {
		FileInfo info = null;

		try {
			info =
				(FileInfo) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_FILE_ID + fileInfo.getFileId());

			if (info != null) {
				if (StringUtil.isNotEmpty(fileInfo.getFlag())) {
					if (fileInfo.getFlag().equals(info.getFlag())) {
						return info;
					}
				} else {
					return info;
				}
			}
		} catch (Exception e0) {
			logger.error(LogUtil.parserBean(fileInfo), e0);
		}

		try {
			info = fileDao.getFileInfo(fileInfo);

			memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_FILE_ID + fileInfo.getFileId(), info);
		} catch (Exception e1) {
			logger.error(LogUtil.parserBean(fileInfo), e1);
		}

		return info;
	}

	public BooleanResult createFileInfo(FileInfo fileInfo) {
		BooleanResult res = new BooleanResult();
		res.setResult(false);

		try {
			fileDao.createFileInfo(fileInfo);
			res.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(fileInfo), e);
		}

		return res;
	}

	public int getFileCount(FileInfo fileInfo) {
		try {
			return fileDao.getFileCount(fileInfo);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(fileInfo), e);
		}

		return 0;
	}

	public List<FileInfo> getFileList(FileInfo fileInfo) {
		try {
			return fileDao.getFileList(fileInfo);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(fileInfo), e);
		}

		return null;
	}

	public BooleanResult deleteFile(FileInfo fileInfo) {
		BooleanResult res = new BooleanResult();

		try {
			int result = fileDao.deleteFile(fileInfo);
			res.setResult(true);
			res.setCode(String.valueOf(result));
			// clear cache
			removeCache(fileInfo.getCodes());
			return res;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(fileInfo), e);
		}

		res.setResult(false);
		res.setCode(IFileService.ERROR_MESSAGE);
		return res;
	}

	private void removeCache(String[] ids) {
		try {
			for (String id : ids) {
				memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_FILE_ID + id);
			}
		} catch (ServiceException e) {
			logger.error(e);
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IFileDao getFileDao() {
		return fileDao;
	}

	public void setFileDao(IFileDao fileDao) {
		this.fileDao = fileDao;
	}

}
