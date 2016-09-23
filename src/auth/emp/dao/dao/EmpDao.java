package auth.emp.dao.dao;

import auth.emp.vo.EmpModel;
import auth.res.vo.ResModel;
import utils.base.BaseDao;

import java.util.List;

/**
 * Created by RZ on 6/22/16.
 */
public interface EmpDao extends BaseDao<EmpModel>{
    public EmpModel findByUserNameAndPwd(String username, String pwd);

    List<EmpModel> getAllInGroup(EmpModel login);
}
