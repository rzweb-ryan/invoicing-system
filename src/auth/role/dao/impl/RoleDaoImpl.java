package auth.role.dao.impl;

import auth.role.dao.dao.RoleDao;
import auth.role.vo.RoleModel;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;

/**
 * Created by RZ on 7/1/16.
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseImpl<RoleModel> implements RoleDao{

    //TODO
    public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {

    }
}
