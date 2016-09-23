package invoice.storage.business.ebo;

import java.io.Serializable;
import java.util.List;

import invoice.storage.business.ebi.StorageEbi;
import invoice.storage.dao.dao.StorageDao;
import invoice.storage.vo.StorageModel;
import utils.base.BaseQueryModel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("storageEbi")
public class StorageEbo implements StorageEbi{
	@Resource
	private StorageDao storageDao;

	public void save(StorageModel sm) {
		storageDao.save(sm);
	}

	public void update(StorageModel sm) {
		storageDao.update(sm);
	}

	public void delete(StorageModel sm) {
		storageDao.delete(sm);
	}

	public StorageModel getByUuid(Serializable uuid) {
		return storageDao.get(uuid);
	}

	public List<StorageModel> getAll() {
		return storageDao.getAll();
	}

	public List<StorageModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return storageDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return storageDao.getCount(qm);
	}

	public List<StorageModel> getAllNotFull() {
		return storageDao.getAllNotFull();
	}
}
