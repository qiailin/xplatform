package com.jiakun.xplatform.framework.bo;

import java.io.Serializable;

/**
 * @author rongjie.chenrj
 * @description:������ѯ���������
 */
public class BaseQuery implements Serializable {

	private static final long serialVersionUID = -3083820639654271036L;
	private String gmtStart;// ��ʼʱ��
	private String gmtEnd; // ����ʱ��
	private String name; // �ؼ��
	private String code;// ���
	private int startRow = 0; // ��ʼ��¼��
	private int endRow = 0; // �����¼��
	public String ORDER_ASC = "asc";
	public String ORDER_DESC = "desc";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGmtStart() {
		return gmtStart;
	}

	public void setGmtStart(String gmtStart) {
		this.gmtStart = gmtStart;
	}

	public String getGmtEnd() {
		return gmtEnd;
	}

	public void setGmtEnd(String gmtEnd) {
		this.gmtEnd = gmtEnd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

}
