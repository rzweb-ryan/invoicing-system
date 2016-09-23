package invoice.storage.dao.dao;

import invoice.storage.vo.StorageModel;
import utils.base.BaseDao;

import java.util.List;

public interface StorageDao extends BaseDao<StorageModel> {

    List<StorageModel> getAllNotFull();
}
