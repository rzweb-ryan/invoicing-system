package invoice.storageDetail.dao.dao;

import invoice.goods.vo.GoodsModel;
import invoice.storage.vo.StorageModel;
import invoice.storageDetail.business.ebi.StorageDetailEbi;
import invoice.storageDetail.vo.StorageDetailModel;
import utils.base.BaseDao;

import java.util.List;

public interface StorageDetailDao extends BaseDao<StorageDetailModel> {

    public StorageDetailModel getBySmAndGm(Long storageUuid, Long uuid);


    List<Object[]> getGoodsInStore(StorageModel storageModel);

    List<Object[]> getGoodsInStoreByGm(Long gmUuid);
}
