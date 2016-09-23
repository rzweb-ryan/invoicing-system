package invoice.goods.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import invoice.goods.dao.dao.GoodsDao;
import invoice.goods.vo.GoodsModel;
import invoice.goods.vo.GoodsQueryModel;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("goodsDao")
public class GoodsImpl extends BaseImpl<GoodsModel> implements GoodsDao{

	public void doCriteria(DetachedCriteria dc,BaseQueryModel qm){
		GoodsQueryModel gqm = (GoodsQueryModel)qm;
		if(gqm!=null) {
			if(gqm.getUnit()!=null) {
				dc.add(Restrictions.like("unit","%"+gqm.getUnit()+"%"));
			}
			if(gqm.getProducer()!=null&&gqm.getGtm()!=null&&gqm.getGtm().getName()!=null) {
				dc.add(Restrictions.like("gtm.name","%"+gqm.getGtm().getName()+"%"));
			}
			if(gqm.getGtm()!=null&&gqm.getGtm().getSm()!=null&&gqm.getGtm().getSm().getUuid()!=null&&gqm.getGtm().getSm().getUuid()!=-1) {
				dc.createAlias("gtm","g");
				dc.add(Restrictions.eq("g.sm",gqm.getGtm().getSm()));
			}
		}
	}

	public List<GoodsModel> getAllByGtm(Long uuid) {
		String hql = "from GoodsModel where gtm.uuid = ?";
		return hibernateTemplate.find(hql,uuid);
	}
}
