package invoice.orderdetail.business.ebo;

import java.io.Serializable;
import java.util.List;

import invoice.orderdetail.business.ebi.OrderDetailEbi;
import invoice.orderdetail.dao.dao.OrderDetailDao;
import invoice.orderdetail.vo.OrderDetailModel;
import invoice.report.vo.ReportQueryModel;
import utils.base.BaseQueryModel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("orderDetailEbi")
public class OrderDetailEbo implements OrderDetailEbi{
	@Resource
	private OrderDetailDao orderDetailDao;

	public void save(OrderDetailModel om) {
		orderDetailDao.save(om);
	}

	public void update(OrderDetailModel om) {
		orderDetailDao.update(om);
	}

	public void delete(OrderDetailModel om) {
		orderDetailDao.delete(om);
	}

	public OrderDetailModel getByUuid(Serializable uuid) {
		return orderDetailDao.get(uuid);
	}

	public List<OrderDetailModel> getAll() {
		return orderDetailDao.getAll();
	}

	public List<OrderDetailModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return orderDetailDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return orderDetailDao.getCount(qm);
	}

	public List<OrderDetailModel> getByOrderUuid(Long uuid) {
		return orderDetailDao.getByOrderUuid(uuid);
	}

	public List<OrderDetailModel> getGmList(ReportQueryModel rqm, Long gmUuid) {
		return orderDetailDao.getGmList(rqm,gmUuid);
	}
}
