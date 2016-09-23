package invoice.order.dao.impl;

import auth.emp.vo.EmpModel;
import invoice.order.vo.OrderModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import invoice.order.dao.dao.SaleInfoDao;
import invoice.order.vo.SaleInfoModel;
import invoice.order.vo.SaleInfoQueryModel;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("saleInfoDao")
public class SaleInfoImpl extends BaseImpl<SaleInfoModel> implements SaleInfoDao{

	public void doCriteria(DetachedCriteria dc,BaseQueryModel qm){
		SaleInfoQueryModel sqm = (SaleInfoQueryModel)qm;
		if(sqm.getOm()!=null&&sqm.getOm().getOrderType()==OrderModel.ORDER_STATUS_OF_SALE_WAITING) {
			dc.add(Restrictions.eq("o.status",OrderModel.ORDER_STATUS_OF_SALE_WAITING));
		}
		// TODO 添加自定义查询条件
	}

	public SaleInfoModel getInfo(OrderModel om) {
		String hql = "from SaleInfoModel where om = ?";
		List<SaleInfoModel> list =  hibernateTemplate.find(hql,om);
		return list.get(0);
	}

	@Override
	public List<SaleInfoModel> getTasks(SaleInfoQueryModel sqm, EmpModel login, Integer pageNum, Integer pageCount) {
		DetachedCriteria dc = DetachedCriteria.forClass(SaleInfoModel.class);
		doCriteria(dc,sqm);
		dc.createAlias("om","m");
		dc.add(Restrictions.eq("m.completer",login));
		dc.addOrder(Order.desc("m.createTime"));
		return hibernateTemplate.findByCriteria(dc,(pageNum-1)*pageCount,pageCount);
	}
}
