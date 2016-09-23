package auth.dept.dao.impl;

import auth.dept.dao.dao.DeptDao;
import auth.dept.vo.DeptModel;
import auth.dept.vo.DeptQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;

import java.util.List;

/**
 * Created by RZ on 6/22/16.
 */
@Repository("deptDao")
public class DeptDaoImpl extends BaseImpl<DeptModel> implements DeptDao{

    /**
     * trim and '%' like
     * @param dc
     * @param bqm
     */
    public void doCriteria(DetachedCriteria dc,BaseQueryModel bqm) {
        //...
        DeptQueryModel dqm = (DeptQueryModel)bqm;
        if(dqm!=null) {
            if(dqm.getName()!=null&&dqm.getName().trim()!="") {
                dc.add(Restrictions.like("name","%"+dqm.getName().trim()+"%"));
            }
            if (dqm.getTel()!=null&&dqm.getTel().trim()!="") {
                dc.add(Restrictions.like("tel","%"+dqm.getTel().trim()+"%"));
            }
        }
    }
}
