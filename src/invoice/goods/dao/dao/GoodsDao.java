package invoice.goods.dao.dao;

import invoice.goods.vo.GoodsModel;
import utils.base.BaseDao;

import java.util.List;

public interface GoodsDao extends BaseDao<GoodsModel> {

    public List<GoodsModel> getAllByGtm(Long uuid);
}
