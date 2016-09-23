package invoice.goodsType.business.ebo;

import java.io.Serializable;
import java.util.List;

import invoice.goodsType.business.ebi.GoodsTypeEbi;
import invoice.goodsType.dao.dao.GoodsTypeDao;
import invoice.goodsType.vo.GoodsTypeModel;
import utils.base.BaseQueryModel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("goodsTypeEbi")
public class GoodsTypeEbo implements GoodsTypeEbi{
	@Resource
	private GoodsTypeDao goodsTypeDao;

	public void save(GoodsTypeModel gm) {
		goodsTypeDao.save(gm);
	}

	public void update(GoodsTypeModel gm) {
		goodsTypeDao.update(gm);
	}

	public void delete(GoodsTypeModel gm) {
		goodsTypeDao.delete(gm);
	}

	public GoodsTypeModel getByUuid(Serializable uuid) {
		return goodsTypeDao.get(uuid);
	}

	public List<GoodsTypeModel> getAll() {
		return goodsTypeDao.getAll();
	}

	public List<GoodsTypeModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return goodsTypeDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return goodsTypeDao.getCount(qm);
	}

	public List<GoodsTypeModel> getAllBySupplier(Long smUuid) {
		return goodsTypeDao.getAllBySupplier(smUuid);
	}

	public List<GoodsTypeModel> getAllBySmUnion(Long smUuid) {
		return goodsTypeDao.getAllBySmUnion(smUuid);
	}
}
