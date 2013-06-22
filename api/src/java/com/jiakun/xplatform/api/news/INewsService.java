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
	public List<NewsDetail> getNewsList();

	/**
	 * ��ȡ�����ܱ���Ϣ
	 * 
	 * @return
	 */
	public List<NewsTotal> getNewsTotalList();

	/**
	 * ��ȡ������ϸ�б�
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailList(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ������ϸ
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public NewsDetail getNewsDetail(NewsDetail NewsDetail);

	/**
	 * �������ŵ������
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public int updateNewsDetail(NewsDetail NewsDetail);

	/**
	 * ��ȡ������ϸ����
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public List<NewsFile> getNewsFileList(NewsFile NewsFile);

	/**
	 * ��ȡ������Ŀ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsTotal> getNewsTreeTypeList(NewsTotal lanNewsTotal);

	/**
	 * ���������ܱ�lanNewsTotal
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public Long createNewsTotal(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ�����ܱ�����
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsTotalJsonCount(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ�����ܱ��ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsTotal> getNewsTotalJsonList(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ������ϸ������
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsDetailJsonCount(NewsDetail lanNewsDetail);

	/**
	 * ��ȡ������ϸ���ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailJsonList(NewsDetail lanNewsDetail);

	/**
	 * ɾ����ϸ
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public int deleteNewsDetail(NewsDetail NewsDetail);

	/**
	 * ��ȡ������ϸ������
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsDetailMoreListCount(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ������ϸ���ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailMoreList(NewsTotal lanNewsTotal);

	/**
	 * ����������ϸ��lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	public Long createNewsDetail(NewsDetail lanNewsDetail);

	/**
	 * ����������ϸ��lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	public Long createNewsFile(NewsFile newsFilebean);

	/**
	 * �޸�������ϸ������
	 * 
	 * @param newsFilebean
	 * @return
	 */
	public int updateNewsFile(NewsFile newsFilebean);

	/**
	 * ���������ܱ�
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int updateNewsTotal(NewsTotal lanNewsTotal);

	/**
	 * ��ȡ���������ܱ�
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public NewsTotal getNewsTotal(NewsTotal lanNewsTotal);

}
