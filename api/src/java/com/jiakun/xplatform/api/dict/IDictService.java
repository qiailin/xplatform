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
	public int getCmsTbDictCount(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict);

	/**
	 * ���� dict type ��ѯ dict
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public int getDictCount(CmsTbDict cmsTbDict);

	/**
	 * ���� dict type ��ѯ dict
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public List<CmsTbDict> getDictList(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	public int getCmsTbDictTypeCount(CmsTbDictType cmsTbDictType);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	public BooleanResult createDictType(CmsTbDictType cmsTbDictType);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public BooleanResult createDict(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public BooleanResult updateDict(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public BooleanResult updateDictType(CmsTbDictType cmsTbDictType);

	public CmsTbDictType getCmsTbDictType(CmsTbDictType cmsTbDictType);

	public CmsTbDict getCmsTbDict(CmsTbDict cmsTbDict);

	public List<CmsTbDict> getCmsTbDictByType(CmsTbDict cmsTbDict);

}
