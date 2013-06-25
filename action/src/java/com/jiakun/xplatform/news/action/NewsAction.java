package com.jiakun.xplatform.news.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.cache.IMemcachedCacheService;
import com.jiakun.xplatform.api.news.INewsService;
import com.jiakun.xplatform.api.news.bo.NewsDetail;
import com.jiakun.xplatform.api.news.bo.NewsTotal;
import com.jiakun.xplatform.api.news.bo.NewsFile;
import com.jiakun.xplatform.api.tree.bo.Tree4Ajax;
import com.jiakun.xplatform.framework.action.BaseAction;
import com.jiakun.xplatform.framework.annotation.Decode;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.DateUtil;
import com.jiakun.xplatform.framework.util.FileUtil;
import com.jiakun.xplatform.framework.webwork.annotations.JsonResult;

/**
 * 
 * @author
 * 
 */
public class NewsAction extends BaseAction {

	private Logger4jExtend logger = Logger4jCollection.getLogger(NewsAction.class);

	private static final long serialVersionUID = 1L;
	private int total;
	private List<NewsTotal> lanNewsTotalList;
	private List<NewsDetail> lanNewsDetailList;
	private List<NewsTotal> newsTypelist;
	private List<NewsFile> lanNewsFileList;
	private String detailId;
	private String upload_sign;
	private NewsDetail lanNewsDelailbean;

	private INewsService newsService;
	private String oaReadFilePath;
	private String oaTxtFilePath;
	private List<Tree4Ajax> treeList;
	private String node;
	private String actionName;
	private String newsId;
	private String totalParentId;
	private @Decode
	String totalName;
	private String totalUploadSign;
	private String totalShow;
	private String totalSign;
	private String totalCode;
	private @Decode
	String total_Name;
	private Date create_date;
	private @Decode
	String delail_title;
	private String delail_ids;
	private String total_id;
	private String delailTitle;
	private File[] upload;
	private String[] uploadFileName;
	private String delail_content;
	private String css_flag;
	private String oaFileFilePath;
	private String totalId;
	private String filename;
	private String[] fileId;

	private IMemcachedCacheService memcachedCacheService;

	/**
	 * OA������ҳ��ʼ��
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String newsIndex() {

		lanNewsTotalList = new ArrayList<NewsTotal>();

		List<NewsDetail> o = null;
		try {
			o = (List<NewsDetail>) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_NEWS);
		} catch (Exception e) {
		}

		List<NewsDetail> detailList = (o == null || o.size() == 0) ? newsService.getNewsList() : o;

		if (o == null || o.size() == 0) {
			try {
				memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_NEWS, detailList,
					IMemcachedCacheService.CACHE_KEY_NEWS_DEFAULT_EXP);
			} catch (Exception e) {
			}
		}

		Map<Long, NewsTotal> map = new HashMap<Long, NewsTotal>();

		for (NewsDetail d : detailList) {
			if (map.containsKey(d.getTotal_id())) {
				map.get(d.getTotal_id()).getNewsdet_list().add(d);
			} else {
				NewsTotal total = new NewsTotal();
				total.setTotal_id(d.getTotal_id());
				total.setTotal_name(d.getTotal_name());
				total.setTotal_sign(d.getTotal_sign());
				total.setNewsdet_list(new ArrayList<NewsDetail>());
				total.getNewsdet_list().add(d);

				map.put(d.getTotal_id(), total);

				lanNewsTotalList.add(total);
			}
		}

		map = null;

		return "searchTotal";
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public String getOneNews() {
		lanNewsDelailbean = new NewsDetail();
		NewsFile lanNewsFile = new NewsFile();
		String conent = null;

		if (StringUtil.isNotEmpty(detailId)) {
			lanNewsDelailbean.setDelail_id(Long.valueOf(detailId));
			lanNewsFile.setDetail_id(Long.valueOf(detailId));

			// ��ȡ������ϸ
			lanNewsDelailbean = newsService.getNewsDetail(lanNewsDelailbean);
			lanNewsDelailbean.setClicks_ratio(lanNewsDelailbean.getClicks_ratio() + 1);

			// �޸ĵ������
			newsService.updateNewsDetail(lanNewsDelailbean);

			// ��ȡ����
			if (StringUtil.isNotEmpty(lanNewsDelailbean.getDelail_content())) {
				try {
					conent = FileUtil.readFile(oaTxtFilePath + "/" + lanNewsDelailbean.getDelail_content());
				} catch (Exception e) {
					logger.error(e);
				}
				if (StringUtil.isNotEmpty(conent)) {
					lanNewsDelailbean.setDelail_content(conent);
				} else {
					lanNewsDelailbean.setDelail_content("  ");
				}
			}

			// ��ȡ�����б�
			lanNewsFileList = newsService.getNewsFileList(lanNewsFile);
			if (lanNewsFileList != null && lanNewsFileList.size() > 0) {
				for (NewsFile NewsFile : lanNewsFileList) {
					NewsFile.setNews_file_url(oaReadFilePath + "/" + NewsFile.getNews_file_url());
				}
				upload_sign = "Y";
			} else {
				upload_sign = "N";
			}
		}

		return "oneNews";
	}

	/**
	 * ��ת�����Ű�鴴��
	 * 
	 * @return
	 */
	public String newsTot_add() {
		return "newsTotAdd";
	}

	/**
	 * ��ȡ������Ŀ
	 * 
	 * @return
	 */
	public String newsTree() {
		this.node = "1";
		return "newsTree";
	}

	@JsonResult(field = "treeList", include = { "id", "text" })
	public String getNewsTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
		NewsTotal NewsTotal = new NewsTotal();
		List<NewsTotal> newsTreeList = null;
		if (StringUtil.isNotEmpty(node)) {
			try {
				NewsTotal.setTotal_parent_id(Long.valueOf(node));

				// ��ȡ�����б�
				newsTreeList = newsService.getNewsTreeTypeList(NewsTotal);
			} catch (Exception e) {
				logger.error(e);
			}

			if (newsTreeList == null || newsTreeList.size() == 0) {
				return JSON;
			}

			for (NewsTotal total : newsTreeList) {
				Tree4Ajax tree = new Tree4Ajax();
				tree.setId(String.valueOf(total.getTotal_id()));
				tree.setText(total.getTotal_name() + "|" + total.getTotal_upload_sign());
				// tree.setCls("folder");
				treeList.add(tree);
			}
		}

		return JSON;
	}

	/**
	 * �����ܵ�
	 * 
	 * @return
	 */
	public String createNewsTotal() {
		NewsTotal lanNewsTotalbean = new NewsTotal();
		lanNewsTotalbean.setTotal_parent_id(Long.valueOf(totalParentId));
		lanNewsTotalbean.setTotal_name(totalName);
		lanNewsTotalbean.setTotal_show(totalShow);
		lanNewsTotalbean.setTotal_flag("Y");
		lanNewsTotalbean.setTotal_upload_sign(totalUploadSign);

		if ("Y".equals(totalShow)) {
			lanNewsTotalbean.setTotal_sign(totalSign);
			lanNewsTotalbean.setTotal_code(totalCode);
		}

		Long totalId = newsService.createNewsTotal(lanNewsTotalbean);
		if (totalId == 0l) {
			this.setFailMessage("����ʧ��!");
		} else {
			this.setSuccessMessage("�����ɹ�!");

			// ���cache
			removeCache();
		}

		return SUCCESS;
	}

	/**
	 * ��ת��������Ŀ��ѯ��ά��
	 * 
	 * @return
	 */
	public String searchNewsTotal() {
		return "searchNewsTotal";
	}

	/**
	 * ��ת��������Ŀ��ѯ��ά��
	 * 
	 * @return
	 */

	@JsonResult(field = "lanNewsTotalList", include = { "total_id", "total_parent_id", "total_name", "total_title",
		"total_date", "total_code", "total_show", "total_sign", "total_upload_sign" }, total = "total")
	public String searchNewsTotalList() {
		NewsTotal newsTotal = new NewsTotal();
		newsTotal = this.getSearchInfo(newsTotal);

		if (StringUtil.isNotEmpty(total_Name)) {
			newsTotal.setTotal_name(total_Name);
		}

		total = newsService.getNewsTotalJsonCount(newsTotal);
		if (total > 0) {
			lanNewsTotalList = newsService.getNewsTotalJsonList(newsTotal);
		}

		return JSON;
	}

	/**
	 * ��ת����Ŀ��ϸ����
	 * 
	 * @return
	 */
	public String newsDetail_add() {
		return "newsDetail_add";
	}

	/**
	 * ��ת����Ŀ��ϸ������ά��
	 * 
	 * @return
	 */
	public String searchNewsDetail() {
		return "searchNewsDetail";
	}

	/**
	 * ��ת��������Ŀ��ѯ��ά��
	 * 
	 * @return
	 */

	@JsonResult(field = "lanNewsDetailList", include = { "delail_id", "total_id", "delail_title", "delail_operator",
		"detail_date", "clicks_ratio", "total_name" }, total = "total")
	public String searchNewsDetailList() {
		NewsDetail newsDetail = new NewsDetail();
		newsDetail = this.getSearchInfo(newsDetail);

		// ������Ŀ���
		if (StringUtil.isNotEmpty(total_Name)) {
			newsDetail.setTotal_name(total_Name);
		}

		// ����
		if (StringUtil.isNotEmpty(delail_title)) {
			newsDetail.setDelail_title(delail_title);
		}

		total = newsService.getNewsDetailJsonCount(newsDetail);
		if (total > 0) {
			lanNewsDetailList = newsService.getNewsDetailJsonList(newsDetail);
		}

		return JSON;
	}

	/**
	 * ��Ŀ��ϸɾ��
	 * 
	 * @return
	 */
	public String deleteNewsDetail() {
		NewsDetail newsDetail = new NewsDetail();
		if (StringUtil.isNotEmpty(delail_ids)) {
			String[] codes = delail_ids.split(",");
			newsDetail.setCodes(codes);
			if (newsService.deleteNewsDetail(newsDetail) > 0) {
				this.setSuccessMessage("�����ɹ�");

				// ���cache
				removeCache();
			} else {
				this.setFailMessage("����ʧ��");
			}
		} else {
			this.setFailMessage("����ʧ��");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * ��ת����ϸ�б�
	 * 
	 * @return
	 */
	public String getSearchNews() {
		lanNewsDetailList = new ArrayList<NewsDetail>();

		// ��ȡ�����б�
		newsTypelist = newsService.getNewsTotalList();
		return "getSearchNews";
	}

	/**
	 * ��ת��ϸ�б�
	 * 
	 * @return
	 */

	@JsonResult(field = "lanNewsDetailList", include = { "delail_id", "delail_title", "detail_date", "clicks_ratio" }, total = "total")
	public String searchNewsD() {
		NewsTotal NewsTotal = new NewsTotal();
		NewsTotal = this.getSearchInfo(NewsTotal);

		if (StringUtil.isNotEmpty(total_id)) {
			NewsTotal.setTotal_id(Long.valueOf(total_id.replace(" ", "")));
		}
		if (StringUtil.isNotEmpty(total_Name)) {
			NewsTotal.setTotal_name(total_Name);
		}

		total = newsService.getNewsDetailMoreListCount(NewsTotal);
		if (total > 0) {
			lanNewsDetailList = newsService.getNewsDetailMoreList(NewsTotal);
		}

		return JSON;
	}

	/**
	 * ɾ�������ܵ�
	 * 
	 * @return
	 */
	public String deleteNewsTotal() {
		if (StringUtil.isNotEmpty(totalId)) {
			NewsTotal NewsTotal = new NewsTotal();
			NewsTotal.setTotal_id(Long.valueOf(totalId));
			NewsTotal.setTotal_flag("N");
			if (newsService.updateNewsTotal(NewsTotal) > 0) {
				this.setSuccessMessage("ɾ��ɹ���");

				// ���cache
				removeCache();
			} else {
				this.setFailMessage("ɾ��ʧ��");
			}

		}

		return SUCCESS;
	}

	/**
	 * ��ת���޸������ܵ�ҳ��
	 * 
	 * @return
	 */
	public String updateNewsTotalPre() {
		if (StringUtil.isNotEmpty(totalId)) {
			NewsTotal NewsTotal = new NewsTotal();
			NewsTotal.setTotal_id(Long.valueOf(totalId));
			NewsTotal = newsService.getNewsTotal(NewsTotal);

			if (NewsTotal != null) {
				// ��ȡ��Ŀ���
				totalName = NewsTotal.getTotal_name();
				totalUploadSign = NewsTotal.getTotal_upload_sign();
				totalShow = NewsTotal.getTotal_show();
				totalSign = NewsTotal.getTotal_sign();
				totalCode = NewsTotal.getTotal_code();
			}
		}

		return "updateNewsTotalPre";
	}

	/**
	 * �޸������ܵ�
	 * 
	 * @return
	 */
	public String updateNewsTotal() {
		NewsTotal NewsTotal = new NewsTotal();
		NewsTotal.setTotal_id(Long.valueOf(totalId));
		NewsTotal.setTotal_code(totalCode);
		NewsTotal.setTotal_name(totalName);
		NewsTotal.setTotal_show(totalShow);
		NewsTotal.setTotal_sign(totalSign);
		NewsTotal.setTotal_upload_sign(totalUploadSign);

		if (newsService.updateNewsTotal(NewsTotal) > 0) {
			this.setSuccessMessage("�޸ĳɹ���");

			// ���cache
			removeCache();
		} else {
			this.setFailMessage("�޸�ʧ��");
		}

		return SUCCESS;
	}

	/**
	 * Ԥ��������Ŀ��ϸ
	 * 
	 * @return
	 */
	public String preview() {
		AllUsers user = this.getUser();
		lanNewsDelailbean = new NewsDetail();
		lanNewsDelailbean.setDelail_title(delailTitle);
		lanNewsDelailbean.setDelail_content(delail_content);
		lanNewsDelailbean.setDelail_operator(user.getUserName());
		lanNewsDelailbean.setClicks_ratio(0l);
		lanNewsDelailbean.setDetail_date(new Date());

		return "preview";
	}

	/**
	 * ����������Ŀ��ϸ
	 * 
	 * @return
	 */
	public String createNewsdet() {
		lanNewsDelailbean = new NewsDetail();
		AllUsers user = this.getUser();
		NewsFile newsFilebean = new NewsFile();
		String imageFileName = "";

		if ("N".equals(totalUploadSign) && StringUtil.isNotEmpty(delail_content)) {
			File savedir = new File(oaTxtFilePath);
			// ���Ŀ¼�����ڣ����½�
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
			String name = DateUtil.getNowDateminStr() + ".txt";
			FileUtil.saveFile(oaTxtFilePath + "/" + name, delail_content);
			lanNewsDelailbean.setDelail_content(name);
		}

		lanNewsDelailbean.setDelail_title(delailTitle);
		lanNewsDelailbean.setClicks_ratio(0l);
		lanNewsDelailbean.setCss_flag(css_flag);
		lanNewsDelailbean.setDelail_operator(user.getUserName());
		lanNewsDelailbean.setTotal_id(Long.valueOf(totalParentId));
		lanNewsDelailbean.setOrg_name("1");

		Long detailId = newsService.createNewsDetail(lanNewsDelailbean);

		if (0l == detailId) {
			this.setFailMessage("����������ϸʧ�ܣ�");
			return SUCCESS;
		}

		if (upload != null && upload.length > 0) {
			for (int i = 0; i < upload.length; i++) {
				if (uploadFileName[i] != null && uploadFileName[i].length() > 0) {
					imageFileName = UUID.randomUUID() + FileUtil.getFileExtention(uploadFileName[i]);
					File savedir = new File(oaFileFilePath);
					// ���Ŀ¼�����ڣ����½�
					if (!savedir.exists()) {
						savedir.mkdirs();
					}
					File imageFile = new File(oaFileFilePath + "/" + imageFileName);
					FileUtil.saveAsFile(upload[i], imageFile);

					newsFilebean.setDetail_id(detailId);
					newsFilebean.setNews_file_name(uploadFileName[i]);
					newsFilebean.setNews_file_url(imageFileName);

					Long news_file_id = newsService.createNewsFile(newsFilebean);
					if (0l == news_file_id) {
						this.setFailMessage("�������Ÿ���ʧ�ܣ�");
						return SUCCESS;
					}
				}
			}
		}
		this.setSuccessMessage("�����ɹ���");

		// ���cache
		removeCache();

		return SUCCESS;
	}

	/**
	 * ��ת���޸�������Ŀ��ϸҳ��
	 * 
	 * @return
	 */
	public String updateNewsDetailPre() {
		lanNewsDelailbean = new NewsDetail();
		NewsFile lanNewsFile = new NewsFile();
		lanNewsFileList = new ArrayList<NewsFile>();
		String conent = "";

		if (StringUtil.isNotEmpty(detailId)) {
			lanNewsDelailbean.setDelail_id(Long.valueOf(detailId));
			lanNewsFile.setDetail_id(Long.valueOf(detailId));

			// ��ȡ������ϸ
			lanNewsDelailbean = newsService.getNewsDetail(lanNewsDelailbean);
			filename = lanNewsDelailbean.getDelail_content();

			// ��ȡ����
			if (StringUtil.isNotEmpty(lanNewsDelailbean.getDelail_content())) {
				try {
					conent = FileUtil.readFile(oaTxtFilePath + "/" + lanNewsDelailbean.getDelail_content());
				} catch (Exception e) {
					logger.error(e);
				}

				if (StringUtil.isNotEmpty(conent)) {
					lanNewsDelailbean.setDelail_content(conent);
				} else {
					lanNewsDelailbean.setDelail_content("  ");
				}
			}

			// ��ȡ�����б�
			lanNewsFileList = newsService.getNewsFileList(lanNewsFile);
			if (lanNewsFileList != null && lanNewsFileList.size() > 0) {
				for (NewsFile NewsFile : lanNewsFileList) {
					NewsFile.setNews_file_url(oaReadFilePath + "/" + NewsFile.getNews_file_url());
				}
			}
		}

		return "updateNewsDetailPre";
	}

	/**
	 * �޸�������Ŀ��ϸ
	 * 
	 * @return
	 */
	public String updateNewsDetail() {
		lanNewsDelailbean = new NewsDetail();
		NewsFile newsFilebean = new NewsFile();
		lanNewsFileList = new ArrayList<NewsFile>();
		String imageFileName = "";

		if (StringUtil.isNotEmpty(detailId)) {
			lanNewsDelailbean.setDelail_id(Long.valueOf(detailId));
			// ����
			lanNewsDelailbean.setDelail_title(delailTitle);
			// �Ƿ���
			lanNewsDelailbean.setCss_flag(css_flag);
			// ��ȡ����
			if (StringUtil.isNotEmpty(filename) && StringUtil.isNotEmpty(delail_content)) {
				File savedir = new File(oaTxtFilePath);
				// ���Ŀ¼�����ڣ����½�
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				FileUtil.saveFile(oaTxtFilePath + "/" + filename, delail_content);
			}

			if (newsService.updateNewsDetail(lanNewsDelailbean) <= 0) {// �޸�������ϸ
				this.setFailMessage("�޸���ϸʧ��");
				return SUCCESS;
			}
			newsFilebean.setNews_del_flag("N");
			newsFilebean.setDetail_id(Long.valueOf(detailId));
			newsFilebean.setCodes(fileId);
			if (newsService.updateNewsFile(newsFilebean) >= 0) { // ɾ���
				if (upload != null && upload.length > 0) { // ��������
					for (int i = 0; i < upload.length; i++) {
						if (uploadFileName[i] != null && uploadFileName[i].length() > 0) {
							imageFileName = UUID.randomUUID() + FileUtil.getFileExtention(uploadFileName[i]);
							File savedir = new File(oaFileFilePath);
							// ���Ŀ¼�����ڣ����½�
							if (!savedir.exists()) {
								savedir.mkdirs();
							}
							File imageFile = new File(oaFileFilePath + "/" + imageFileName);
							FileUtil.saveAsFile(upload[i], imageFile);

							newsFilebean.setNews_file_name(uploadFileName[i]);
							newsFilebean.setNews_file_url(imageFileName);
							Long news_file_id = newsService.createNewsFile(newsFilebean);
							if (0l == news_file_id) {
								this.setFailMessage("�������Ÿ���ʧ�ܣ�");
								return SUCCESS;
							}
						}
					}
				}
			}
		}
		this.setSuccessMessage("�޸ĳɹ���");

		// ���cache
		removeCache();

		return SUCCESS;
	}

	private void removeCache() {
		try {
			memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_NEWS);
		} catch (Exception e) {

		}
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<NewsTotal> getLanNewsTotalList() {
		return lanNewsTotalList;
	}

	public void setLanNewsTotalList(List<NewsTotal> lanNewsTotalList) {
		this.lanNewsTotalList = lanNewsTotalList;
	}

	public List<NewsDetail> getLanNewsDetailList() {
		return lanNewsDetailList;
	}

	public void setLanNewsDetailList(List<NewsDetail> lanNewsDetailList) {
		this.lanNewsDetailList = lanNewsDetailList;
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	public List<NewsTotal> getNewsTypelist() {
		return newsTypelist;
	}

	public void setNewsTypelist(List<NewsTotal> newsTypelist) {
		this.newsTypelist = newsTypelist;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public List<NewsFile> getLanNewsFileList() {
		return lanNewsFileList;
	}

	public void setLanNewsFileList(List<NewsFile> lanNewsFileList) {
		this.lanNewsFileList = lanNewsFileList;
	}

	public String getUpload_sign() {
		return upload_sign;
	}

	public void setUpload_sign(String upload_sign) {
		this.upload_sign = upload_sign;
	}

	public NewsDetail getLanNewsDelailbean() {
		return lanNewsDelailbean;
	}

	public void setLanNewsDelailbean(NewsDetail lanNewsDelailbean) {
		this.lanNewsDelailbean = lanNewsDelailbean;
	}

	public String getOaReadFilePath() {
		return oaReadFilePath;
	}

	public void setOaReadFilePath(String oaReadFilePath) {
		this.oaReadFilePath = oaReadFilePath;
	}

	public String getOaTxtFilePath() {
		return oaTxtFilePath;
	}

	public void setOaTxtFilePath(String oaTxtFilePath) {
		this.oaTxtFilePath = oaTxtFilePath;
	}

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	@Override
	public String getActionName() {
		return actionName;
	}

	@Override
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getTotalParentId() {
		return totalParentId;
	}

	public void setTotalParentId(String totalParentId) {
		this.totalParentId = totalParentId;
	}

	public String getTotalName() {
		return totalName;
	}

	public void setTotalName(String totalName) {
		this.totalName = totalName;
	}

	public String getTotalUploadSign() {
		return totalUploadSign;
	}

	public void setTotalUploadSign(String totalUploadSign) {
		this.totalUploadSign = totalUploadSign;
	}

	public String getTotalShow() {
		return totalShow;
	}

	public void setTotalShow(String totalShow) {
		this.totalShow = totalShow;
	}

	public String getTotalSign() {
		return totalSign;
	}

	public void setTotalSign(String totalSign) {
		this.totalSign = totalSign;
	}

	public String getTotalCode() {
		return totalCode;
	}

	public void setTotalCode(String totalCode) {
		this.totalCode = totalCode;
	}

	public String getTotal_Name() {
		return total_Name;
	}

	public void setTotal_Name(String total_Name) {
		this.total_Name = total_Name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getDelail_title() {
		return delail_title;
	}

	public void setDelail_title(String delail_title) {
		this.delail_title = delail_title;
	}

	public String getDelail_ids() {
		return delail_ids;
	}

	public void setDelail_ids(String delail_ids) {
		this.delail_ids = delail_ids;
	}

	public String getTotal_id() {
		return total_id;
	}

	public void setTotal_id(String total_id) {
		this.total_id = total_id;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public String getDelailTitle() {
		return delailTitle;
	}

	public void setDelailTitle(String delailTitle) {
		this.delailTitle = delailTitle;
	}

	public String getDelail_content() {
		return delail_content;
	}

	public void setDelail_content(String delail_content) {
		this.delail_content = delail_content;
	}

	public String getCss_flag() {
		return css_flag;
	}

	public void setCss_flag(String css_flag) {
		this.css_flag = css_flag;
	}

	public String getOaFileFilePath() {
		return oaFileFilePath;
	}

	public void setOaFileFilePath(String oaFileFilePath) {
		this.oaFileFilePath = oaFileFilePath;
	}

	public String getTotalId() {
		return totalId;
	}

	public void setTotalId(String totalId) {
		this.totalId = totalId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String[] getFileId() {
		return fileId;
	}

	public void setFileId(String[] fileId) {
		this.fileId = fileId;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

}
