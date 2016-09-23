package invoice.goodsType.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import invoice.goodsType.dao.dao.GoodsTypeDao;
import invoice.goodsType.vo.GoodsTypeModel;
import invoice.goodsType.vo.GoodsTypeQueryModel;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("goodsTypeDao")
public class GoodsTypeImpl extends BaseImpl<GoodsTypeModel> implements GoodsTypeDao{

	public void doCriteria(DetachedCriteria dc,BaseQueryModel qm){
		GoodsTypeQueryModel gqm = (GoodsTypeQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	public List<GoodsTypeModel> getAllBySupplier(Long smUuid) {
		String hql = "from GoodsTypeModel where sm.uuid=?";
		return hibernateTemplate.find(hql,smUuid);
	}

	public List<GoodsTypeModel> getAllBySmUnion(Long smUuid) {
		String hql = "select distinct gtm from GoodsModel gm join gm.gtm gtm where gtm.sm.uuid=?";
		return hibernateTemplate.find(hql,smUuid);
	}
}
