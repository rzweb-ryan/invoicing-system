package invoice.storageDetail.dao.impl;

import invoice.goods.vo.GoodsModel;
import invoice.storage.vo.StorageModel;
import invoice.storageDetail.business.ebi.StorageDetailEbi;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.*;

import invoice.storageDetail.dao.dao.StorageDetailDao;
import invoice.storageDetail.vo.StorageDetailModel;
import invoice.storageDetail.vo.StorageDetailQueryModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository("storageDetailDao")
public class StorageDetailImpl extends BaseImpl<StorageDetailModel> implements StorageDetailDao{

	public void doCriteria(DetachedCriteria dc,BaseQueryModel qm){
		StorageDetailQueryModel sqm = (StorageDetailQueryModel)qm;
		if(sqm!=null) {
			dc.createAlias("sm","s");
			if(sqm.getSm()!=null&&sqm.getSm().getName()!=null) {
				dc.add(Restrictions.like("s.name","%"+sqm.getSm().getName().trim()+"%"));
			}
			if(sqm.getGm()!=null&&sqm.getGm().getName()!=null) {
				dc.createAlias("gm","g");
				dc.add(Restrictions.like("g.name","%"+sqm.getGm().getName().trim()+"%"));
			}
			if(sqm.getSm()!=null&&sqm.getSm().getEm()!=null&&sqm.getSm().getEm().getName()!=null) {

				dc.createAlias("s.em","e");
				dc.add(Restrictions.like("e.name","%"+sqm.getSm().getEm().getName().trim()+"%"));
			}
		}
	}

	public StorageDetailModel getBySmAndGm(Long storageUuid, Long uuid) {
		String hql = "from StorageDetailModel where sm.uuid = ? and gm.uuid=?";
		List<StorageDetailModel> sdms =  hibernateTemplate.find(hql,storageUuid,uuid);
		return sdms.size()==0?null:sdms.get(0);
	}




	public List<Object[]> getGoodsInStore(StorageModel storageModel) {
		String sql = "select sd.uuid,sd.num,sd.goodsUuid,g.name,g.outPrice from tbl_storageDetail sd, tbl_goods g where sd.goodsUuid = g.uuid and sd.storageUuid = "+storageModel.getUuid();
		SQLQuery sq = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return sq.list();

	}

	public List<Object[]> getGoodsInStoreByGm(Long gmUuid) {
		String sql = "select sd.num,g.uuid,g.name,g.outPrice from tbl_storageDetail sd, tbl_goods g where sd.goodsUuid = g.uuid and g.uuid = "+gmUuid;
		SQLQuery sq = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return sq.list();

	}

	public static void main(String[] args) {
		ApplicationContext acx =new ClassPathXmlApplicationContext("applicationContext.xml");
		StorageDetailDao sd = (StorageDetailDao) acx.getBean("storageDetailDao");
		StorageModel sm = new StorageModel();
		sm.setUuid(2l);
		List<Object []> res = sd.getGoodsInStore(sm);
		for (Object[] re : res) {
			System.out.print(re[0]);
			System.out.print(" " +re[1]);
			System.out.print(" " +re[2]);
			System.out.print(" " +re[3]);
			System.out.println(" " +re[4]);

		}
	}
}
