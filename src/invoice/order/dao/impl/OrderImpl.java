package invoice.order.dao.impl;

import auth.emp.vo.EmpModel;
import org.hibernate.criterion.*;

import invoice.order.dao.dao.OrderDao;
import invoice.order.vo.OrderModel;
import invoice.order.vo.OrderQueryModel;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDao")
public class OrderImpl extends BaseImpl<OrderModel> implements OrderDao{

	public void doCriteria(DetachedCriteria dc,BaseQueryModel qm){
		OrderQueryModel oqm = (OrderQueryModel)qm;
		dc.addOrder(Order.desc("createTime"));
		if(oqm.getOrderType()!=null&&oqm.getOrderType()!=-1) {
			dc.add(Restrictions.eq("orderType",oqm.getOrderType()));
		}
		if(oqm.getStatus()!=null&&oqm.getStatus()!=-1) {
			dc.add(Restrictions.eq("status",oqm.getStatus()));
		}
		if(oqm.getTotalNum()!=null) {
			dc.add(Restrictions.ge("totalNum",oqm.getTotalNum()));
		}
		if(oqm.getTotalNum2()!=null) {
			dc.add(Restrictions.le("totalNum",oqm.getTotalNum2()));
		}
		if(oqm.getCreateTime()!=null) {
			dc.add(Restrictions.ge("createTime",oqm.getCreateTime()));
		}
		if(oqm.getCreateTime2()!=null) {
			dc.add(Restrictions.le("createTime",oqm.getCreateTime2()+24*2600*1000-1));
		}
		if(oqm.getTotalPrice()!=null) {
			dc.add(Restrictions.ge("totalPrice",oqm.getTotalPrice()));
		}
		if(oqm.getTotalPrice2()!=null) {
			dc.add(Restrictions.le("totalPrice",oqm.getTotalPrice2()));
		}
		if(oqm.getCreator()!=null&& oqm.getCreator().getName()!=null) {
			dc.createAlias("creator","c");
			dc.add((Restrictions.like("c.name","%"+oqm.getCreator().getName()+"%")));
		}
	}

	private void doQbcTrans(DetachedCriteria dc,Integer[] notTasks) {

		dc.add(Restrictions.not(Restrictions.in("status",notTasks)));
	}


	public Integer getTransportCount(OrderQueryModel oqm, Integer[] notTasks) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		doCriteria( dc, oqm);
		doQbcTrans(dc,notTasks);
		dc.setProjection(Projections.rowCount());
		List<Long> l  =  hibernateTemplate.findByCriteria(dc);
		return l.get(0).intValue();
	}

	public List<OrderModel> getTransportList(OrderQueryModel oqm, Integer pageNum, Integer pageCount, Integer[] notTasks) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		doCriteria( dc, oqm);
		doQbcTrans(dc,notTasks);
		dc.addOrder(Order.desc("checkTime"));
		return hibernateTemplate.findByCriteria(dc,(pageNum-1)*pageCount,pageCount);
	}

	public Integer getCountTasks(OrderQueryModel oqm, EmpModel login) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		doCriteria(dc,oqm);
		dc.add(Restrictions.eq("completer",login));
		dc.setProjection(Projections.rowCount());
		List<Long> res =  hibernateTemplate.findByCriteria(dc);
		return res.get(0).intValue();
	}

	public List<OrderModel> getTasks(OrderQueryModel oqm, EmpModel login, Integer pageNum, Integer pageCount) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		doCriteria(dc,oqm);
		dc.add(Restrictions.eq("completer",login));
		dc.addOrder(Order.desc("checkTime"));
		return hibernateTemplate.findByCriteria(dc,(pageNum-1)*pageCount,pageCount);
	}
}
