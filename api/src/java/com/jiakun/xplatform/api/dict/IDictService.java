package com.jiakun.xplatform.api.dict;

import java.util.List;

import com.jiakun.xplatform.api.dict.bo.CmsTbDict;
import com.jiakun.xplatform.api.dict.bo.CmsTbDictType;
import com.jiakun.xplatform.framework.bo.BooleanResult;

/**
 * �ֵ�ӿ�
 * 
 */
public interface IDictService {

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	int getCmsTbDictCount(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict);

	/**
	 * ���� dict type ��ѯ dict
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	int getDictCount(CmsTbDict cmsTbDict);

	/**
	 * ���� dict type ��ѯ dict
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	List<CmsTbDict> getDictList(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	int getCmsTbDictTypeCount(CmsTbDictType cmsTbDictType);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	BooleanResult createDictType(CmsTbDictType cmsTbDictType);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	BooleanResult createDict(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	BooleanResult updateDict(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	BooleanResult updateDictType(CmsTbDictType cmsTbDictType);

	CmsTbDictType getCmsTbDictType(CmsTbDictType cmsTbDictType);

	CmsTbDict getCmsTbDict(CmsTbDict cmsTbDict);

	List<CmsTbDict> getCmsTbDictByType(CmsTbDict cmsTbDict);

}
