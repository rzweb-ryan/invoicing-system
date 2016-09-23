package invoice.storageLog.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import invoice.storageLog.dao.dao.StorageLogDao;
import invoice.storageLog.vo.StorageLogModel;
import invoice.storageLog.vo.StorageLogQueryModel;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;
import org.springframework.stereotype.Repository;
@Repository("storageLogDao")
public class StorageLogImpl extends BaseImpl<StorageLogModel> implements StorageLogDao{

	public void doCriteria(DetachedCriteria dc,BaseQueryModel qm){
		StorageLogQueryModel sqm = (StorageLogQueryModel)qm;
		// TODO 添加自定义查询条件
	}

}
