package auth.emp.business.ebi;

import auth.emp.vo.EmpModel;
import auth.res.vo.ResModel;
import auth.role.vo.RoleModel;
import org.springframework.transaction.annotation.Transactional;
import utils.base.BaseEbi;

import java.util.List;
import java.util.Set;

/**
 * Created by RZ on 6/22/16.
 */
@Transactional
public interface EmpEbi extends BaseEbi<EmpModel>{
    /**
     * login method by username and password
     * @param username password
     * @param pwd username
     * @param loginIp
     * @return EmpModel
     */
    public EmpModel login(String username, String pwd, String loginIp);
    boolean changePwd(String username, String pwd, String newPwd);

    public void update(EmpModel em, Long[] resCheckbox);

    public void save(EmpModel em, Long[] resCheckbox);

    /**
     * get all emp in the same group
     * @param login
     * @return
     */
    public List<EmpModel> getAllInGroup(EmpModel login);
}
