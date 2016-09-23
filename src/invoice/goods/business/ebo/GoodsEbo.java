package invoice.goods.business.ebo;

import java.io.Serializable;
import java.util.List;

import invoice.goods.business.ebi.GoodsEbi;
import invoice.goods.dao.dao.GoodsDao;
import invoice.goods.vo.GoodsModel;
import utils.base.BaseQueryModel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("goodsEbi")
public class GoodsEbo implements GoodsEbi{
	@Resource
	private GoodsDao goodsDao;

	public void save(GoodsModel gm) {
		goodsDao.save(gm);
	}

	public void update(GoodsModel gm) {
		goodsDao.update(gm);
	}

	public void delete(GoodsModel gm) {
		goodsDao.delete(gm);
	}

	public GoodsModel getByUuid(Serializable uuid) {
		return goodsDao.get(uuid);
	}

	public List<GoodsModel> getAll() {
		return goodsDao.getAll();
	}

	public List<GoodsModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return goodsDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return goodsDao.getCount(qm);
	}

	public List<GoodsModel> getAllByGtm(Long uuid) {
		return goodsDao.getAllByGtm(uuid);
	}
}
