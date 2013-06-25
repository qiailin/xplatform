package com.jiakun.xplatform.dict.dao.impl;

import java.util.List;

import com.jiakun.xplatform.api.dict.bo.CmsTbDict;
import com.jiakun.xplatform.api.dict.bo.CmsTbDictType;
import com.jiakun.xplatform.dict.dao.IDictDao;
import com.jiakun.xplatform.framework.dao.impl.BaseDaoImpl;

public class DictDaoImpl extends BaseDaoImpl implements IDictDao {

	public int getCmsTbDictCount(CmsTbDict cmsTbDict) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"dict.getCmsTbDictCount", cmsTbDict);
	}

	@SuppressWarnings("unchecked")
	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict) {
		return (List<CmsTbDict>) getSqlMapClientTemplate().queryForList(
				"dict.getCmsTbDictList", cmsTbDict);
	}

	public int getDictCount(CmsTbDict cmsTbDict) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"dict.getDictCount", cmsTbDict);
	}

	@SuppressWarnings("unchecked")
	public List<CmsTbDict> getDictList(CmsTbDict cmsTbDict) {
		return (List<CmsTbDict>) getSqlMapClientTemplate().queryForList(
				"dict.getDictList", cmsTbDict);
	}

	public int getCmsTbDictTypeCount(CmsTbDictType cmsTbDictType) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"dict.getCmsTbDictTypeCount", cmsTbDictType);
	}

	@SuppressWarnings("unchecked")
	public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType) {
		return (List<CmsTbDictType>) getSqlMapClientTemplate().queryForList(
				"dict.getCmsTbDictTypeList", cmsTbDictType);
	}

	public Long createDict(CmsTbDict cmsTbDict) {
		return (Long) getSqlMapClientTemplate().insert("dict.createDict",
				cmsTbDict);
	}

	public Long createDictType(CmsTbDictType cmsTbDictType) {
		return (Long) getSqlMapClientTemplate().insert("dict.createDictType",
				cmsTbDictType);
	}

	public int updateDict(CmsTbDict cmsTbDict) {
		return getSqlMapClientTemplate().update("dict.updateDict", cmsTbDict);
	}

	public int updateDictType(CmsTbDictType cmsTbDictType) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("dict.updateDictType",
				cmsTbDictType);
	}

	public CmsTbDict getCmsTbDict(CmsTbDict cmsTbDict) {
		// TODO Auto-generated method stub
		return (CmsTbDict) getSqlMapClientTemplate().queryForObject(
				"dict.getCmsTbDict", cmsTbDict);
	}

	public CmsTbDictType getCmsTbDictType(CmsTbDictType cmsTbDictType) {
		// TODO Auto-generated method stub
		return (CmsTbDictType) getSqlMapClientTemplate().queryForObject(
				"dict.getCmsTbDictType", cmsTbDictType);
	}

	@SuppressWarnings("unchecked")
	public List<CmsTbDict> getCmsTbDictByType(CmsTbDict cmsTbDict) {
		return (List<CmsTbDict>) getSqlMapClientTemplate().queryForList(
				"dict.getCmsTbDictListByType", cmsTbDict);
	}

}
