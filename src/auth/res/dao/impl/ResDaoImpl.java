package auth.res.dao.impl;

import auth.res.dao.dao.ResDao;
import auth.res.vo.ResModel;
import auth.role.dao.dao.RoleDao;
import auth.role.vo.RoleModel;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RZ on 7/1/16.
 */
@Repository("resDao")
public class ResDaoImpl extends BaseImpl<ResModel> implements ResDao {
    //TODO
    public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {

    }

    public List<ResModel> getResByEmpUuid(Long uuid) {
        String hql="select res from EmpModel em join em.roles rs join rs.reses res where em.uuid=?";
        return hibernateTemplate.find(hql,uuid);
    }
}
