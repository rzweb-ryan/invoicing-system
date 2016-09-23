package invoice.report.dao.impl;

import invoice.orderdetail.vo.OrderDetailModel;
import invoice.report.dao.dao.ReportDao;
import invoice.report.vo.ReportQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("reportDao")
public class ReportImpl implements ReportDao{
	@Resource
	private HibernateTemplate hibernateTemplate;
	public void doCriteria(DetachedCriteria dc,ReportQueryModel rqm){
		// TODO 添加自定义查询条件
		if(rqm!=null) {
			dc.createAlias("om","o");
			if(rqm.getStatus()!=null&&rqm.getStatus()!=-1) {
				dc.add(Restrictions.eq("o.status",rqm.getStatus()));
			}
			if(rqm.getSupplierUuid()!=null&&rqm.getSupplierUuid()!=-1) {
				dc.createAlias("o.sm","s");
				dc.add(Restrictions.eq("s.uuid",rqm.getSupplierUuid()));
			}
			if(rqm.getBeginDate()!=null) {
				dc.add(Restrictions.ge("o.createTime",rqm.getBeginDate()));
			}
			if(rqm.getEndDate()!=null) {
				dc.add(Restrictions.le("o.createTime",rqm.getEndDate()+24*2600*1000-1));
			}
		}
	}

	public List<Object[]> getReport(ReportQueryModel rqm) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetailModel.class);
		doCriteria(dc,rqm);
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.groupProperty("gm"));
		pList.add(Projections.sum("num"));
		dc.setProjection(pList);
		return hibernateTemplate.findByCriteria(dc);
	}
}
