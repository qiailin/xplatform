package com.jiakun.xplatform.dict.dao;

import java.util.List;

import com.jiakun.xplatform.api.dict.bo.CmsTbDict;
import com.jiakun.xplatform.api.dict.bo.CmsTbDictType;

public interface IDictDao {

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
	Long createDictType(CmsTbDictType cmsTbDictType);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	Long createDict(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	int updateDict(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	int updateDictType(CmsTbDictType cmsTbDictType);

	CmsTbDictType getCmsTbDictType(CmsTbDictType cmsTbDictType);

	CmsTbDict getCmsTbDict(CmsTbDict cmsTbDict);

	List<CmsTbDict> getCmsTbDictByType(CmsTbDict cmsTbDict);

}
