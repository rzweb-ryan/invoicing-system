package invoice.goodsType.dao.dao;

import invoice.goodsType.vo.GoodsTypeModel;
import utils.base.BaseDao;

import java.util.List;

public interface GoodsTypeDao extends BaseDao<GoodsTypeModel> {

    public List<GoodsTypeModel> getAllBySupplier(Long smUuid);

    public List<GoodsTypeModel> getAllBySmUnion(Long smUuid);
}
