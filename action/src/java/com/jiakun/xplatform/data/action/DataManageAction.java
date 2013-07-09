package com.jiakun.xplatform.data.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.springframework.beans.BeanUtils;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.api.alluser.bo.AllUsers;
import com.jiakun.xplatform.api.data.IDataService;
import com.jiakun.xplatform.api.data.bo.DataInfo;
import com.jiakun.xplatform.api.data.bo.DataLogTotal;
import com.jiakun.xplatform.api.data.bo.TabColumn;
import com.jiakun.xplatform.framework.action.BaseAction;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.DateUtil;
import com.jiakun.xplatform.framework.util.ExcelUtil;
import com.jiakun.xplatform.framework.util.JsonUtil;
import com.jiakun.xplatform.framework.webwork.annotations.JsonResult;

/**
 * import data by excel.
 * 
 * @author xujiakun
 * 
 */
public class DataManageAction extends BaseAction {

	private static final long serialVersionUID = 7511837191810578359L;

	private static final String BR = "</br>";

	private Logger4jExtend logger = Logger4jCollection.getLogger(DataManageAction.class);

	private IDataService dataService;

	private String dataConfigId;

	private File upload;

	private String uploadFileName;

	private String dataLogTotalId;

	private int total;

	private List<DataInfo> dataInfoList;

	private String listJson;

	public String exportDataTemplate() {
		AllUsers users = this.getUser();

		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			List<TabColumn> tabColumnList =
				dataService.getTabColumnsByConfigId(Long.parseLong(dataConfigId), users.getUserId());

			List<String> props = new ArrayList<String>();
			List<DataInfo> dataInfos = new ArrayList<DataInfo>();

			if (tabColumnList != null && tabColumnList.size() != 0) {
				int i = 1;
				DataInfo dataInfo = new DataInfo();

				for (TabColumn tabColumn : tabColumnList) {
					props.add("parameter" + i);

					@SuppressWarnings("rawtypes")
					Class[] c = { String.class };
					Method method = BeanUtils.findMethod(dataInfo.getClass(), "setParameter" + i, c);

					method.invoke(dataInfo,
						new Object[] { tabColumn.getColumnName()
							+ (StringUtil.isNotEmpty(tabColumn.getComments()) ? ":" + tabColumn.getComments() : "") });
					i++;
				}

				dataInfos.add(dataInfo);

				ExcelUtil util = new ExcelUtil();
				inputStream =
					Thread.currentThread().getContextClassLoader().getResource("/resource/exportDataTemplate.xls")
						.openStream();
				HttpServletResponse response = this.getServletResponse();
				response.setContentType("application/x-download");
				response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(("���ģ��(" + tabColumnList.get(0).getTableName() + ")").getBytes("GBK"), "ISO8859-1")
					+ ".xls\"");
				outputStream = response.getOutputStream();
				util.createExcelWithTemplate(inputStream, outputStream, props, dataInfos);
				outputStream.flush();

				return RESULT_MESSAGE;
			}

			this.setFailMessage("û�е�ǰ���ģ������Ȩ��");
			return RESULT_MESSAGE;
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage(IDataService.ERROR_MESSAGE);
			return RESULT_MESSAGE;
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}

	public void importData() {
		PrintWriter out = null;
		AllUsers users = this.getUser();

		try {
			HttpServletResponse response = this.getServletResponse();
			response.setContentType("text/html; charset=GBK");
			out = response.getWriter();

			String resultMsg = processData(uploadFileName, upload, Long.parseLong(dataConfigId), users.getUserId());

			out.write(StringUtil.isEmpty(resultMsg) ? "{success:true,msg:'�����ɹ�'}" : "{success:false,msg:'"
				+ resultMsg + "'}");
			out.flush();
		} catch (Exception e) {
			logger.error("dataConfigId:" + dataConfigId, e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
	}

	private String processData(String uploadFileName, File upload, Long dataConfigId, String userId) {
		InputStream inputStream = null;
		Workbook tBook = null;
		StringBuilder sb = new StringBuilder();

		try {
			// �ϴ��ļ�������
			if (StringUtil.isEmpty(uploadFileName)) {
				return "��ѡ����Ҫ�ϴ��ļ�";
			}

			String end = StringUtil.substring(uploadFileName, StringUtil.lastIndexOf(uploadFileName, '.'));

			// �ļ���ʽ����ȷ
			if (StringUtil.isEmpty(end) || (!".xls".equals(end) && !".xlsx".equals(end))) {
				return "�ϴ��ļ������޷�ʶ��";
			}

			inputStream = new FileInputStream(upload);
			tBook = Workbook.getWorkbook(inputStream);
			Sheet rs = tBook.getSheet(0);
			int rsColumns = rs.getColumns();
			int rsRows = rs.getRows();

			List<TabColumn> tabColumnList = dataService.getTabColumnsByConfigId(dataConfigId, userId);

			if (tabColumnList == null || tabColumnList.size() == 0) {
				return "û�и���ݿ��ĵ���Ȩ��";
			}

			// ��ݿ���ֶθ���һ��
			if (tabColumnList.size() != rsColumns) {
				return "�ϴ��ļ���������ݿ���ֶβ�һ��";
			}

			List<DataInfo> dataInfos = new ArrayList<DataInfo>();

			for (int i = 2; i < rsRows; i++) {
				DataInfo dataInfo = new DataInfo();

				// init tableName and tableSequence
				dataInfo.setTableName(tabColumnList.get(0).getTableName());
				dataInfo.setSequenceValue(tabColumnList.get(0).getSequenceValue());

				for (int j = 0; j < rsColumns; j++) {
					@SuppressWarnings("rawtypes")
					Class[] c = { String.class };
					TabColumn tabColumn = tabColumnList.get(j);

					Method method1 = BeanUtils.findMethod(dataInfo.getClass(), "setParameter" + (j + 1), c);
					method1.invoke(dataInfo, new Object[] { tabColumn.getColumnName() });

					// ��i�� ��j�� rs.getCell(j, i).getContents()
					String value = rs.getCell(j, i).getContents();

					Method method2 = BeanUtils.findMethod(dataInfo.getClass(), "setValue" + (j + 1), c);
					method2.invoke(dataInfo, new Object[] { StringUtil.isNotEmpty(value) ? value : "" });

					// ��֤value��Ч��
					// �ж��Ƿ��Ϊ��
					if (StringUtil.isEmpty(value)) {
						if ("N".equals(tabColumn.getNullable())) {
							if (sb.length() > 0) {
								sb.append(BR);
							}
							sb.append("��").append(i + 1).append("��").append("��").append(j + 1).append("��")
								.append("����Ϊ��");
						}
					} else {
						// �ж��Ƿ�Ϊdate
						if ("DATE".equals(tabColumn.getDataType())) {
							if (DateUtil.getDateTime(value) == null) {
								if (sb.length() > 0) {
									sb.append(BR);
								}
								sb.append("��").append(i + 1).append("��").append("��").append(j + 1).append("��")
									.append("������yyyy-MM-dd��ʽ");
							}
						} else if ("NUMBER".equals(tabColumn.getDataType())) {
							try {
								// �ж��Ƿ�Ϊ��������
								new BigDecimal(value);

								// �жϳ���
								int length = tabColumn.getDataLength();
								if (value.length() > length) {
									if (sb.length() > 0) {
										sb.append(BR);
									}
									sb.append("��").append(i + 1).append("��").append("��").append(j + 1).append("��")
										.append("�ֶγ��ȳ���").append(length);
								}
							} catch (Exception e) {
								logger.error("value:" + value, e);
								if (sb.length() > 0) {
									sb.append(BR);
								}
								sb.append("��").append(i + 1).append("��").append("��").append(j + 1).append("��")
									.append("���������ָ�ʽ");
							}
						} else if ("VARCHAR2".equals(tabColumn.getDataType())) {
							// �жϳ���
							int length = tabColumn.getDataLength();
							if (value.length() * 2 > length) {
								if (sb.length() > 0) {
									sb.append(BR);
								}
								sb.append("��").append(i + 1).append("��").append("��").append(j + 1).append("��")
									.append("�ֶγ��ȳ���").append(length);
							}
						}
					}
				}

				dataInfos.add(dataInfo);
			}

			if (sb.length() > 0) {
				return sb.toString();
			}

			// insert
			BooleanResult result = dataService.createDataInfo(dataConfigId, dataInfos);

			if (result.getResult()) {
				return "";
			} else {
				return IDataService.ERROR_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
			return IDataService.ERROR_MESSAGE;
		} finally {
			if (tBook != null) {
				try {
					tBook.close();
				} catch (Exception e) {
					logger.error(e);
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
	}

	public String searchDataPreview() {
		AllUsers users = this.getUser();
		if (StringUtil.isNotEmpty(dataLogTotalId)) {
			try {
				List<TabColumn> tabColumns =
					dataService.getTabColumnsByLogId(Long.parseLong(dataLogTotalId), users.getUserId());

				listJson = JsonUtil.bean2Json(tabColumns.getClass(), tabColumns);
				StringBuilder temp = new StringBuilder();
				temp.append(listJson);
				temp.insert(0, "{values:");
				temp.append(",total:");
				temp.append(tabColumns.size());
				temp.append("}");
				listJson = temp.toString();

			} catch (Exception e) {
				logger.error("dataLogTotalId:" + dataLogTotalId, e);
			}
		}

		return "searchDataPreview";
	}

	@JsonResult(field = "dataInfoList", exclude = { "tableName", "sequenceValue" }, total = "total")
	public String getDataPreviewJsonList() {
		AllUsers users = this.getUser();
		DataLogTotal s = new DataLogTotal();
		s = getSearchInfo(s);

		if (StringUtil.isNotEmpty(dataLogTotalId)) {
			try {
				s.setDataLogTotalId(Long.parseLong(dataLogTotalId));
			} catch (Exception e) {
				logger.error("dataLogTotalId:" + dataLogTotalId, e);
				return JSON;
			}
		}

		s.setUserId(users.getUserId());

		total = dataService.getDataPreviewCount(s);
		if (total != 0) {
			dataInfoList = dataService.getDataPreviewList(s);
		}

		return JSON;
	}

	public String deleteDataInfo() {
		AllUsers users = this.getUser();

		if (StringUtil.isNotEmpty(dataLogTotalId)) {
			try {
				BooleanResult reult = dataService.deleteDataInfo(Long.parseLong(dataLogTotalId), users.getUserId());
				if (reult.getResult()) {
					return RESULT_MESSAGE;
				}
			} catch (Exception e) {
				logger.error("dataLogTotalId:" + dataLogTotalId, e);
			}
		}

		this.setFailMessage(IDataService.ERROR_MESSAGE);
		return RESULT_MESSAGE;
	}

	public IDataService getDataService() {
		return dataService;
	}

	public void setDataService(IDataService dataService) {
		this.dataService = dataService;
	}

	public String getDataConfigId() {
		return dataConfigId;
	}

	public void setDataConfigId(String dataConfigId) {
		this.dataConfigId = dataConfigId;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getDataLogTotalId() {
		return dataLogTotalId;
	}

	public void setDataLogTotalId(String dataLogTotalId) {
		this.dataLogTotalId = dataLogTotalId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<DataInfo> getDataInfoList() {
		return dataInfoList;
	}

	public void setDataInfoList(List<DataInfo> dataInfoList) {
		this.dataInfoList = dataInfoList;
	}

	public String getListJson() {
		return listJson;
	}

	public void setListJson(String listJson) {
		this.listJson = listJson;
	}

}
