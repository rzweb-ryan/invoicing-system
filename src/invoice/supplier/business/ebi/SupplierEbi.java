package invoice.supplier.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import invoice.supplier.vo.SupplierModel;
import utils.base.BaseEbi;

import java.util.List;

@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel>{
    /**
     * get all which contains GoodsType
     * @return
     */
    public List<SupplierModel> getAllUnion();

    /**
     * get all Supplier which contains goodsType which contains goods
     * @return
     */
    public List<SupplierModel> getAllUnion2();
}
