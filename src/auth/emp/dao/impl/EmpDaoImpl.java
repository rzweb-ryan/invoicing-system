package auth.emp.dao.impl;

import auth.emp.dao.dao.EmpDao;
import auth.emp.vo.EmpModel;
import auth.emp.vo.EmpQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;

import java.util.List;

/**
 * Created by RZ on 6/22/16.
 */
@Repository("empDao")
public class EmpDaoImpl extends BaseImpl<EmpModel> implements EmpDao{
    public EmpModel findByUserNameAndPwd(String username, String pwd) {
        String hql = "from EmpModel em where em.username=? and em.pwd=?";
        List<EmpModel> res = hibernateTemplate.find(hql,username,pwd);
        return res.size()>0?res.get(0):null;
    }

    public List<EmpModel> getAllInGroup(EmpModel login) {
        String hql = "from EmpModel em where em.dm=?";
        return hibernateTemplate.find(hql, login.getDm());
    }


    public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
        EmpQueryModel eqm = (EmpQueryModel)bqm;

        if (eqm.getUsername()!=null && eqm.getUsername().trim().length()>0) {
            dc.add(Restrictions.eq("username",eqm.getUsername().trim()));
        }
        if (eqm.getName()!=null && eqm.getName().trim().length()>0) {
            dc.add(Restrictions.like("name","%"+eqm.getName().trim()+"%"));
        }
        if (eqm.getTel()!=null && eqm.getTel().trim().length()>0) {
            dc.add(Restrictions.like("tel","%"+eqm.getTel().trim()+"%"));
        }
        if (eqm.getGender()!=null && eqm.getGender()!=-1) {
            dc.add(Restrictions.eq("gender",eqm.getGender()));
        }
        if (eqm.getEmail()!=null && eqm.getEmail().trim().length()>0) {
            dc.add(Restrictions.like("email","%"+eqm.getEmail().trim()+"%"));
        }
        if (eqm.getBirthday()!=null) {
            dc.add(Restrictions.ge("birthday",eqm.getBirthday()));
        }
        if (eqm.getBirthday2()!=null) {
            dc.add(Restrictions.le("birthday",eqm.getBirthday2()+24*3600*1000-1));
        }
        if (eqm.getDm()!=null &&eqm.getDm().getUuid()!=null && eqm.getDm().getUuid()!=-1) {
            dc.add(Restrictions.eq("dm",eqm.getDm()));
        }

    }
}
