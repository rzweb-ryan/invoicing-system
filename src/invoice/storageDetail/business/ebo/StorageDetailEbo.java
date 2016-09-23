package invoice.storageDetail.business.ebo;

import java.io.Serializable;
import java.util.List;

import invoice.goods.vo.GoodsModel;
import invoice.storage.vo.StorageModel;
import invoice.storageDetail.business.ebi.StorageDetailEbi;
import invoice.storageDetail.dao.dao.StorageDetailDao;
import invoice.storageDetail.vo.StorageDetailModel;
import org.springframework.orm.hibernate3.HibernateTemplate;
import utils.base.BaseQueryModel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("storageDetailEbi")
public class StorageDetailEbo implements StorageDetailEbi{
	@Resource
	private StorageDetailDao storageDetailDao;

	public void save(StorageDetailModel sm) {
		storageDetailDao.save(sm);
	}

	public void update(StorageDetailModel sm) {
		storageDetailDao.update(sm);
	}

	public void delete(StorageDetailModel sm) {
		storageDetailDao.delete(sm);
	}

	public StorageDetailModel getByUuid(Serializable uuid) {
		return storageDetailDao.get(uuid);
	}

	public List<StorageDetailModel> getAll() {
		return storageDetailDao.getAll();
	}

	public List<StorageDetailModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return storageDetailDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return storageDetailDao.getCount(qm);
	}


	public List<Object[]> getGoodsInStore(StorageModel storageModel) {
		return storageDetailDao.getGoodsInStore(storageModel);
	}

	public List<Object[]> getGoodsInStoreByGm(Long gmUuid) {
		return storageDetailDao.getGoodsInStoreByGm(gmUuid);
	}
}
