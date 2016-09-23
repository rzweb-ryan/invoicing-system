package invoice.storageDetail.business.ebi;

import invoice.goods.vo.GoodsModel;
import invoice.storage.vo.StorageModel;
import org.springframework.transaction.annotation.Transactional;

import invoice.storageDetail.vo.StorageDetailModel;
import utils.base.BaseEbi;

import java.util.List;

@Transactional
public interface StorageDetailEbi extends BaseEbi<StorageDetailModel>{

    /**
     * get all goods int the storage
     * @param storageModel
     * @return
     */

    public List<Object[]> getGoodsInStore(StorageModel storageModel);

    List<Object[]> getGoodsInStoreByGm(Long gmUuid);
}
