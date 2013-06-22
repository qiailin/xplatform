package com.jiakun.xplatform.news.service.impl;

import java.util.List;

import com.jiakun.xplatform.api.news.INewsService;
import com.jiakun.xplatform.api.news.bo.NewsDetail;
import com.jiakun.xplatform.api.news.bo.NewsTotal;
import com.jiakun.xplatform.api.news.bo.NewsFile;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;
import com.jiakun.xplatform.news.dao.INewsDao;

public class NewsServiceImpl implements INewsService {

	private Logger4jExtend logger = Logger4jCollection
			.getLogger(NewsServiceImpl.class);

	private INewsDao newsDao;

	public List<NewsDetail> getNewsList() {
		try {
			return newsDao.getNewsList();
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * ��ȡ�����ܱ���Ϣ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsTotal> getNewsTotalList() {
		try {
			return newsDao.getNewsTotalList();
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * ��ȡ������ϸ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailList(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsDetailList(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return null;
	}

	/**
	 * ��ȡ������ϸ
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public NewsDetail getNewsDetail(NewsDetail NewsDetail) {
		try {
			return newsDao.getNewsDetail(NewsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(NewsDetail), e);
		}

		return null;
	}

	/**
	 * �������ŵ������
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public int updateNewsDetail(NewsDetail NewsDetail) {
		try {
			return newsDao.updateNewsDetail(NewsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(NewsDetail), e);
		}

		return 0;
	}

	/**
	 * ��ȡ������ϸ����
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public List<NewsFile> getNewsFileList(NewsFile NewsFile) {
		try {
			return newsDao.getNewsFileList(NewsFile);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(NewsFile), e);
		}

		return null;
	}

	/**
	 * ��ȡ������Ŀ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsTotal> getNewsTreeTypeList(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsTreeTypeList(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return null;
	}

	/**
	 * ���������ܱ�lanNewsTotal
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public Long createNewsTotal(NewsTotal lanNewsTotal) {
		try {
			return newsDao.createNewsTotal(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return null;
	}

	/**
	 * ��ȡ�����ܱ�����
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsTotalJsonCount(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsTotalJsonCount(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return 0;
	}

	/**
	 * ��ȡ�����ܱ��ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsTotal> getNewsTotalJsonList(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsTotalJsonList(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return null;
	}

	/**
	 * ��ȡ������ϸ������
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsDetailJsonCount(NewsDetail lanNewsDetail) {
		try {
			return newsDao.getNewsDetailJsonCount(lanNewsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsDetail), e);
		}

		return 0;
	}

	/**
	 * ��ȡ������ϸ���ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailJsonList(NewsDetail lanNewsDetail) {
		try {
			return newsDao.getNewsDetailJsonList(lanNewsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsDetail), e);
		}

		return null;
	}

	/**
	 * ɾ����ϸ
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public int deleteNewsDetail(NewsDetail NewsDetail) {
		try {
			return newsDao.deleteNewsDetail(NewsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(NewsDetail), e);
		}

		return 0;
	}

	/**
	 * ��ȡ������ϸ������
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsDetailMoreListCount(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsDetailMoreListCount(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return 0;
	}

	/**
	 * ��ȡ������ϸ���ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailMoreList(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsDetailMoreList(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return null;
	}

	/**
	 * ����������ϸ��lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	public Long createNewsDetail(NewsDetail lanNewsDetail) {
		try {
			return newsDao.createNewsDetail(lanNewsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsDetail), e);
		}

		return null;
	}

	/**
	 * ����������ϸ��lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	public Long createNewsFile(NewsFile newsFilebean) {
		try {
			return newsDao.createNewsFile(newsFilebean);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsFilebean), e);
		}

		return null;
	}

	/**
	 * ���������ܱ�
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int updateNewsTotal(NewsTotal lanNewsTotal) {
		try {
			return newsDao.updateNewsTotal(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return 0;
	}

	/**
	 * ��ȡ���������ܱ�
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public NewsTotal getNewsTotal(NewsTotal lanNewsTotal) {
		try {
			return newsDao.getNewsTotal(lanNewsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(lanNewsTotal), e);
		}

		return null;
	}

	/**
	 * �޸�������ϸ������
	 * 
	 * @param newsFilebean
	 * @return
	 */
	public int updateNewsFile(NewsFile newsFilebean) {
		try {
			return newsDao.updateNewsFile(newsFilebean);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsFilebean), e);
		}

		return 0;
	}

	public INewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(INewsDao newsDao) {
		this.newsDao = newsDao;
	}
}
