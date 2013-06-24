package com.jiakun.xplatform.api.news;

import java.util.List;

import com.jiakun.xplatform.api.news.bo.NewsDetail;
import com.jiakun.xplatform.api.news.bo.NewsTotal;
import com.jiakun.xplatform.api.news.bo.NewsFile;

/**
 * oa�ȾW�ӿ�
 * 
 * @author anwang
 * 
 */
public interface INewsService {

	/**
	 * ��ȡoa��������
	 * 
	 * @return
	 */
	List<NewsDetail> getNewsList();

	/**
	 * ��ȡ�����ܱ���Ϣ
	 * 
	 * @return
	 */
	List<NewsTotal> getNewsTotalList();

	/**
	 * ��ȡ������ϸ�б�
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	List<NewsDetail> getNewsDetailList(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ������ϸ
	 * 
	 * @param NewsDetail
	 * @return
	 */
	NewsDetail getNewsDetail(NewsDetail NewsDetail);

	/**
	 * �������ŵ������
	 * 
	 * @param NewsDetail
	 * @return
	 */
	int updateNewsDetail(NewsDetail NewsDetail);

	/**
	 * ��ȡ������ϸ����
	 * 
	 * @param NewsDetail
	 * @return
	 */
	List<NewsFile> getNewsFileList(NewsFile NewsFile);

	/**
	 * ��ȡ������Ŀ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	List<NewsTotal> getNewsTreeTypeList(NewsTotal lanNewsTotal);

	/**
	 * ���������ܱ�lanNewsTotal
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	Long createNewsTotal(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ�����ܱ�����
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	int getNewsTotalJsonCount(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ�����ܱ��ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	List<NewsTotal> getNewsTotalJsonList(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ������ϸ������
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	int getNewsDetailJsonCount(NewsDetail lanNewsDetail);

	/**
	 * ��ȡ������ϸ���ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	List<NewsDetail> getNewsDetailJsonList(NewsDetail lanNewsDetail);

	/**
	 * ɾ����ϸ
	 * 
	 * @param NewsDetail
	 * @return
	 */
	int deleteNewsDetail(NewsDetail NewsDetail);

	/**
	 * ��ȡ������ϸ������
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	int getNewsDetailMoreListCount(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ������ϸ���ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	List<NewsDetail> getNewsDetailMoreList(NewsTotal lanNewsTotal);

	/**
	 * ����������ϸ��lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	Long createNewsDetail(NewsDetail lanNewsDetail);

	/**
	 * ����������ϸ��lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	Long createNewsFile(NewsFile newsFilebean);

	/**
	 * �޸�������ϸ������
	 * 
	 * @param newsFilebean
	 * @return
	 */
	int updateNewsFile(NewsFile newsFilebean);

	/**
	 * ���������ܱ�
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	int updateNewsTotal(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ���������ܱ�
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	NewsTotal getNewsTotal(NewsTotal lanNewsTotal);

}
