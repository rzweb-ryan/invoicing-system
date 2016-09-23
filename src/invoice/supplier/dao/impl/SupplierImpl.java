package invoice.supplier.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import invoice.supplier.dao.dao.SupplierDao;
import invoice.supplier.vo.SupplierModel;
import invoice.supplier.vo.SupplierQueryModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("supplierDao")
public class SupplierImpl extends BaseImpl<SupplierModel> implements SupplierDao{

	public void doCriteria(DetachedCriteria dc,BaseQueryModel qm){
		SupplierQueryModel sqm = (SupplierQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	public List<SupplierModel> getAllUnion() {
		String hql = "select distinct sm from GoodsTypeModel gtm join gtm.sm sm";
		return hibernateTemplate.find(hql);
	}

	public List<SupplierModel> getAllUnion2() {
		String hql="select distinct sm from GoodsModel gm join gm.gtm gtm join gtm.sm sm";
		return hibernateTemplate.find(hql);
	}

	public static void main(String[] args) {
		ApplicationContext apx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SupplierDao dao = (SupplierDao) apx.getBean("supplierDao");
		System.out.println(dao.getAllUnion().size());
	}
}
