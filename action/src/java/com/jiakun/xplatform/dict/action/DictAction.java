package com.jiakun.xplatform.dict.action;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.jiakun.xplatform.api.dict.IDictService;
import com.jiakun.xplatform.api.dict.bo.CmsTbDict;
import com.jiakun.xplatform.api.dict.bo.CmsTbDictType;
import com.jiakun.xplatform.framework.action.BaseAction;
import com.jiakun.xplatform.framework.annotation.Decode;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.framework.bo.StringResult;
import com.jiakun.xplatform.framework.webwork.annotations.JsonResult;

/**
 * dict action
 * 
 * @author xujiakun
 * 
 */
public class DictAction extends BaseAction {

	private static final long serialVersionUID = 5042752280539471298L;

	private List<CmsTbDict> cmsTbDictList = new ArrayList<CmsTbDict>();

	private List<CmsTbDictType> cmsTbDictTypeList = new ArrayList<CmsTbDictType>();

	private IDictService dictService;

	private StringResult stringResult = new StringResult();

	private int total;

	@Decode
	private String dictTypeName;
	@Decode
	private String remark;
	@Decode
	private String dictTypeValue;

	private long dictTypeId;

	private long itemId;

	private CmsTbDict cmsTbDict;

	private CmsTbDictType cmsTbDictType;

	/**
	 * goto searchCmsTbDict view
	 * 
	 * @return
	 */
	public String searchCmsTbDict() {
		return "searchCmsTbDict";
	}

	/**
	 * get cmsTbDict jsonList
	 * 
	 * @return
	 */
	@JsonResult(field = "cmsTbDictList", include = { "itemId", "itemName",
			"itemValue", "parentItemId", "itemDescription", "remark",
			"lastModify", "dictTypeId" }, total = "total")
	public String getCmsTbDictJsonList() {
		CmsTbDict m = new CmsTbDict();
		m = getSearchInfo(m);
		m.setDictTypeId(dictTypeId);
		total = dictService.getCmsTbDictCount(m);
		if (total != 0) {
			cmsTbDictList = dictService.getCmsTbDictList(m);
		}

		return JSON;
	}

	/**
	 * get dict jsonList
	 * 
	 * @return
	 */
	@JsonResult(field = "cmsTbDictList", include = { "itemId", "itemValue",
			"itemState", "remark" }, total = "total")
	public String getDictJsonList() {
		CmsTbDict m = new CmsTbDict();
		m = getSearchInfo(m);
		if (StringUtil.isNotEmpty(dictTypeValue)) {
			m.setDictTypeValue(dictTypeValue);
		}
		total = dictService.getDictCount(m);
		if (total != 0) {
			cmsTbDictList = dictService.getDictList(m);
		}

		return JSON;
	}

	/**
	 * goto cmsTbDictType view
	 * 
	 * @return
	 */
	public String searchCmsTbDictType() {
		return "searchCmsTbDictType";
	}

	/**
	 * get cmsTbDictType jsonList
	 * 
	 * @return
	 */
	@JsonResult(field = "cmsTbDictTypeList", include = { "dictTypeId",
			"dictTypeName", "dictTypeValue", "remark", "lastModify" }, total = "total")
	public String getCmsTbDictTypeJsonList() {
		CmsTbDictType m = new CmsTbDictType();
		m = getSearchInfo(m);
		m.setDictTypeName(dictTypeName);
		m.setDictTypeValue(dictTypeValue);
		m.setRemark(remark);
		total = dictService.getCmsTbDictTypeCount(m);
		if (total != 0) {
			cmsTbDictTypeList = dictService.getCmsTbDictTypeList(m);
		}

		return JSON;
	}

	public String toCreateDictType() {
		return "toCreateDictType";
	}

	public String toCreateDict() {
		return "toCreateDict";
	}

	public String createDictType() {
		this.setSuccessMessage("�����ɹ�.");

		BooleanResult booleanResult = dictService.createDictType(cmsTbDictType);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String createDict() {
		this.setSuccessMessage("�����ɹ�.");
		BooleanResult booleanResult = dictService.createDict(cmsTbDict);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}

	public String toUpdateDictType() {
		CmsTbDictType searhCmsTbDictType = new CmsTbDictType();
		searhCmsTbDictType.setDictTypeId(dictTypeId);
		cmsTbDictType = dictService.getCmsTbDictType(searhCmsTbDictType);
		return "toUpdateDictType";
	}

	public String toUpdateDict() {
		CmsTbDict searchCmsTbDict = new CmsTbDict();
		searchCmsTbDict.setItemId(itemId);
		cmsTbDict = dictService.getCmsTbDict(searchCmsTbDict);
		return "toUpdateDict";
	}

	public String UpdateDict() {
		this.setSuccessMessage("���³ɹ�.");
		BooleanResult booleanResult = dictService.updateDict(cmsTbDict);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}

	public String UpdateDictType() {
		this.setSuccessMessage("���³ɹ�.");
		BooleanResult booleanResult = dictService.updateDictType(cmsTbDictType);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}

	@JsonResult(field = "stringResult", include = { "result", "code" })
	public String deleteDict() {
		stringResult.setResult("T");
		CmsTbDict deleteCmsTbDict = new CmsTbDict();
		deleteCmsTbDict.setItemId(itemId);
		deleteCmsTbDict.setItemState("D");
		BooleanResult booleanResult = dictService.updateDict(deleteCmsTbDict);
		if (!booleanResult.getResult()) {
			stringResult.setResult("F");
			stringResult.setCode(booleanResult.getCode());

		}
		return JSON;
	}

	@JsonResult(field = "stringResult", include = { "result", "code" })
	public String deleteDictType() {
		stringResult.setResult("T");
		CmsTbDictType deleteCmsTbDictType = new CmsTbDictType();
		deleteCmsTbDictType.setDictTypeId(dictTypeId);
		deleteCmsTbDictType.setDictTypeState("D");
		BooleanResult booleanResult = dictService
				.updateDictType(deleteCmsTbDictType);
		if (!booleanResult.getResult()) {
			stringResult.setResult("F");
			stringResult.setCode(booleanResult.getCode());

		}
		return JSON;
	}

	public List<CmsTbDict> getCmsTbDictList() {
		return cmsTbDictList;
	}

	public void setCmsTbDictList(List<CmsTbDict> cmsTbDictList) {
		this.cmsTbDictList = cmsTbDictList;
	}

	public List<CmsTbDictType> getCmsTbDictTypeList() {
		return cmsTbDictTypeList;
	}

	public void setCmsTbDictTypeList(List<CmsTbDictType> cmsTbDictTypeList) {
		this.cmsTbDictTypeList = cmsTbDictTypeList;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDictTypeValue() {
		return dictTypeValue;
	}

	public void setDictTypeValue(String dictTypeValue) {
		this.dictTypeValue = dictTypeValue;
	}

	public long getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(long dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public CmsTbDict getCmsTbDict() {
		return cmsTbDict;
	}

	public void setCmsTbDict(CmsTbDict cmsTbDict) {
		this.cmsTbDict = cmsTbDict;
	}

	public CmsTbDictType getCmsTbDictType() {
		return cmsTbDictType;
	}

	public void setCmsTbDictType(CmsTbDictType cmsTbDictType) {
		this.cmsTbDictType = cmsTbDictType;
	}

	public StringResult getStringResult() {
		return stringResult;
	}

	public void setStringResult(StringResult stringResult) {
		this.stringResult = stringResult;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

}
