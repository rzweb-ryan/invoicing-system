package invoice.order.business.ebo;

import java.io.Serializable;
import java.util.List;

import auth.emp.vo.EmpModel;
import invoice.order.business.ebi.SaleInfoEbi;
import invoice.order.dao.dao.SaleInfoDao;
import invoice.order.vo.OrderModel;
import invoice.order.vo.SaleInfoModel;
import invoice.order.vo.SaleInfoQueryModel;
import utils.base.BaseQueryModel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("saleInfoEbi")
public class SaleInfoEbo implements SaleInfoEbi{
	@Resource
	private SaleInfoDao saleInfoDao;

	public void save(SaleInfoModel sm) {
		saleInfoDao.save(sm);
	}

	public void update(SaleInfoModel sm) {
		saleInfoDao.update(sm);
	}

	public void delete(SaleInfoModel sm) {
		saleInfoDao.delete(sm);
	}

	public SaleInfoModel getByUuid(Serializable uuid) {
		return saleInfoDao.get(uuid);
	}

	public List<SaleInfoModel> getAll() {
		return saleInfoDao.getAll();
	}

	public List<SaleInfoModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return saleInfoDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return saleInfoDao.getCount(qm);
	}

	public SaleInfoModel getInfo(OrderModel om) {
		return saleInfoDao.getInfo(om);
	}

	public List<SaleInfoModel> getTasks(SaleInfoQueryModel sqm, EmpModel login, Integer pageNum, Integer pageCount) {
		return saleInfoDao.getTasks(sqm, login, pageNum, pageCount);
	}
}
