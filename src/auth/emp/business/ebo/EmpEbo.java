package auth.emp.business.ebo;

import auth.emp.business.ebi.EmpEbi;
import auth.emp.dao.dao.EmpDao;
import auth.emp.vo.EmpModel;
import auth.role.vo.RoleModel;

import utils.base.BaseQueryModel;
import utils.format.MD5Utils;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by RZ on 6/22/16.
 */
@Service("empEbi")
public class EmpEbo implements EmpEbi{
    @Resource
    private EmpDao empDao;
    public EmpModel login(String username, String pwd, String loginIp) {
        pwd= MD5Utils.md5(pwd);
        EmpModel em =  empDao.findByUserNameAndPwd(username,pwd);
        //snapshot
        if(em!=null) {
            em.setLastLoginIp(loginIp);
            em.setLoginTimes(em.getLoginTimes()+1);
            em.setLastLoginTime(System.currentTimeMillis());
        }
        return em;
    }



    public List<EmpModel> getAll() {
        return empDao.getAll();
    }

   //deprecated
    public void save(EmpModel empModel) {


    }
    public EmpModel getByUuid(Serializable uuid) {
        return empDao.get(uuid);
    }

   //snapshot update
    //Deprecated
    public void update(EmpModel empModel) {
//        empDao.update(empModel);
//        EmpModel temp = empDao.get(empModel.getUuid());
//        temp.setDm(empModel.getDm());
//        temp.setTel(empModel.getTel());
//        temp.setAddress(empModel.getAddress());
//        temp.setEmail(empModel.getEmail());
//        temp.setGender(empModel.getGender());
//        temp.setName(empModel.getName());
//        temp.setBirthday(empModel.getBirthday());
    }

   
    public void delete(EmpModel empModel) {
        if(empModel.getUsername().equals("admin")) return;
        empDao.delete(empModel);
    }

   
    public List<EmpModel> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount) {
        return empDao.getAll(bqm,pageNum,pageCount);
    }

   
    public Integer getCount(BaseQueryModel bqm) {
        return empDao.getCount(bqm);
    }


    public boolean changePwd(String username, String pwd, String newPwd) {
        EmpModel em = empDao.findByUserNameAndPwd(username,MD5Utils.md5(pwd));
        if(em!=null) {
            em.setPwd(MD5Utils.md5(newPwd));
            return true;
        }
        return false;
    }

    public void update(EmpModel em, Long[] resCheckbox) {
        EmpModel temp = empDao.get(em.getUuid());
        temp.setDm(em.getDm());
        temp.setTel(em.getTel());
        temp.setAddress(em.getAddress());
        temp.setEmail(em.getEmail());
        temp.setGender(em.getGender());
        temp.setName(em.getName());
        temp.setBirthday(em.getBirthday());
        Set<RoleModel> roles = new HashSet<RoleModel>();
        for (Long aLong : resCheckbox) {
            RoleModel temp1 = new RoleModel();
            temp1.setUuid(aLong);
            roles.add(temp1);
        }
        temp.setRoles(roles);
    }

    public void save(EmpModel em, Long[] resCheckbox) {
        em.setPwd(MD5Utils.md5(em.getPwd()));
        Set<RoleModel> roles = new HashSet<RoleModel>();
        for (Long aLong : resCheckbox) {
            RoleModel temp1 = new RoleModel();
            temp1.setUuid(aLong);
            roles.add(temp1);
        }
        em.setRoles(roles);
        empDao.save(em);
    }

    public List<EmpModel> getAllInGroup(EmpModel login) {
        return empDao.getAllInGroup(login);
    }


}
