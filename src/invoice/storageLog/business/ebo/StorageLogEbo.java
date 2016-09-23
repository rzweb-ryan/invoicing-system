package invoice.storageLog.business.ebo;

import java.io.Serializable;
import java.util.List;

import invoice.storageLog.business.ebi.StorageLogEbi;
import invoice.storageLog.dao.dao.StorageLogDao;
import invoice.storageLog.vo.StorageLogModel;
import utils.base.BaseQueryModel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("storageLogEbi")
public class StorageLogEbo implements StorageLogEbi{
	@Resource
	private StorageLogDao storageLogDao;

	public void save(StorageLogModel sm) {
		storageLogDao.save(sm);
	}

	public void update(StorageLogModel sm) {
		storageLogDao.update(sm);
	}

	public void delete(StorageLogModel sm) {
		storageLogDao.delete(sm);
	}

	public StorageLogModel getByUuid(Serializable uuid) {
		return storageLogDao.get(uuid);
	}

	public List<StorageLogModel> getAll() {
		return storageLogDao.getAll();
	}

	public List<StorageLogModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return storageLogDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return storageLogDao.getCount(qm);
	}

}
