package invoice.storage.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import invoice.storage.dao.dao.StorageDao;
import invoice.storage.vo.StorageModel;
import invoice.storage.vo.StorageQueryModel;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("storageDao")
public class StorageImpl extends BaseImpl<StorageModel> implements StorageDao{

	public void doCriteria(DetachedCriteria dc,BaseQueryModel qm){
		StorageQueryModel sqm = (StorageQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	@Override
	public List<StorageModel> getAllNotFull() {
		String hql = "from StorageModel where isFull = ?";
		return hibernateTemplate.find(hql,StorageModel.STORAGE_IS_FULL_NO);
	}
}
