package invoice.goods.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import invoice.goods.vo.GoodsModel;
import utils.base.BaseEbi;

import java.util.List;

@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel>{
    /**
     * get all goods by goodsType uuid
     * @return
     * @param uuid
     */
    public List<GoodsModel> getAllByGtm(Long uuid);

}
