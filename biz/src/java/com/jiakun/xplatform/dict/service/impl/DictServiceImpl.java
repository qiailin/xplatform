package com.jiakun.xplatform.dict.service.impl;

import java.util.List;

import com.jiakun.xplatform.api.dict.IDictService;
import com.jiakun.xplatform.api.dict.bo.CmsTbDict;
import com.jiakun.xplatform.api.dict.bo.CmsTbDictType;
import com.jiakun.xplatform.dict.dao.IDictDao;
import com.jiakun.xplatform.framework.bo.BooleanResult;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;

public class DictServiceImpl implements IDictService {

	private Logger4jExtend logger = Logger4jCollection
			.getLogger(DictServiceImpl.class);

	private IDictDao dictDao;

	public int getCmsTbDictCount(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getCmsTbDictCount(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return 0;
	}

	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getCmsTbDictList(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return null;
	}

	public int getDictCount(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getDictCount(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return 0;
	}

	public List<CmsTbDict> getDictList(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getDictList(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return null;
	}

	public int getCmsTbDictTypeCount(CmsTbDictType cmsTbDictType) {
		try {
			return dictDao.getCmsTbDictTypeCount(cmsTbDictType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDictType), e);
		}

		return 0;
	}

	public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType) {
		try {
			return dictDao.getCmsTbDictTypeList(cmsTbDictType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDictType), e);
		}

		return null;
	}

	public BooleanResult createDict(CmsTbDict cmsTbDict) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			long itemId = dictDao.createDict(cmsTbDict);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(itemId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("����ʧ��");
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return booleanResult;
	}

	public BooleanResult createDictType(CmsTbDictType cmsTbDictType) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			long dictTypeId = dictDao.createDictType(cmsTbDictType);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(dictTypeId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("����ʧ��");
			logger.error(LogUtil.parserBean(cmsTbDictType), e);
		}

		return booleanResult;
	}

	public BooleanResult updateDict(CmsTbDict cmsTbDict) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			int n = dictDao.updateDict(cmsTbDict);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(n));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("����ʧ��");
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return booleanResult;
	}

	public BooleanResult updateDictType(CmsTbDictType cmsTbDictType) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			int n = dictDao.updateDictType(cmsTbDictType);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(n));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("����ʧ��");
			logger.error(LogUtil.parserBean(cmsTbDictType), e);
		}

		return booleanResult;
	}

	public CmsTbDict getCmsTbDict(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getCmsTbDict(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return null;
	}

	public CmsTbDictType getCmsTbDictType(CmsTbDictType cmsTbDictType) {
		try {
			return dictDao.getCmsTbDictType(cmsTbDictType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDictType), e);
		}

		return null;
	}

	@Override
	public List<CmsTbDict> getCmsTbDictByType(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getCmsTbDictByType(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return null;
	}

	public IDictDao getDictDao() {
		return dictDao;
	}

	public void setDictDao(IDictDao dictDao) {
		this.dictDao = dictDao;
	}

}
