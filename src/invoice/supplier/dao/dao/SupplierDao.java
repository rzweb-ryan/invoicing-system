package invoice.supplier.dao.dao;

import invoice.supplier.vo.SupplierModel;
import utils.base.BaseDao;

import java.util.List;

public interface SupplierDao extends BaseDao<SupplierModel> {

    public List<SupplierModel> getAllUnion();

    public List<SupplierModel> getAllUnion2();
}
