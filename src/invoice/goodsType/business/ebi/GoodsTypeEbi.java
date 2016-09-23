package invoice.goodsType.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import invoice.goodsType.vo.GoodsTypeModel;
import utils.base.BaseEbi;

import java.util.List;

@Transactional
public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel>{

    /**
     * get goodsType by Supplier Uuid
     * @param smUuid
     * @return
     */
    public List<GoodsTypeModel> getAllBySupplier(Long smUuid);

    /**
     * get goodsType by Supplier Uuid which contains goods
     * @param smUuid
     * @return
     */
    public List<GoodsTypeModel> getAllBySmUnion(Long smUuid);

}
