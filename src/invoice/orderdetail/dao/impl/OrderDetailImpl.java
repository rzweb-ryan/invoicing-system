package invoice.orderdetail.dao.impl;

import invoice.report.vo.ReportQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import invoice.orderdetail.dao.dao.OrderDetailDao;
import invoice.orderdetail.vo.OrderDetailModel;
import invoice.orderdetail.vo.OrderDetailQueryModel;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDetailDao")
public class OrderDetailImpl extends BaseImpl<OrderDetailModel> implements OrderDetailDao{

	public void doCriteria(DetachedCriteria dc,BaseQueryModel qm){
		OrderDetailQueryModel oqm = (OrderDetailQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	public List<OrderDetailModel> getByOrderUuid(Long uuid) {
		String hql = "from OrderDetailModel where om.uuid = ?";
		return hibernateTemplate.find(hql,uuid);
	}

	public List<OrderDetailModel> getGmList(ReportQueryModel rqm, Long gmUuid) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetailModel.class);
		if(rqm!=null) {
			if(rqm.getStatus()!=null&&rqm.getStatus()!=-1) {
				dc.add(Restrictions.eq("status",rqm.getStatus()));
			}
			if(rqm.getBeginDate()!=null) {
				dc.add(Restrictions.ge("createTime",rqm.getBeginDate()));
			}
			if(rqm.getEndDate()!=null) {
				dc.add(Restrictions.le("createTime",rqm.getEndDate()+24*3600*1000-1));
			}
		}
		dc.createAlias("gm","g");
		dc.add(Restrictions.eq("g.uuid",gmUuid));
		return hibernateTemplate.findByCriteria(dc);
	}
}
