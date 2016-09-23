package auth.emp.action;

import auth.dept.business.ebi.DeptEbi;
import auth.dept.vo.DeptModel;
import auth.emp.vo.EmpQueryModel;
import auth.res.business.ebi.ResEbi;
import auth.res.vo.ResModel;
import auth.role.business.ebi.RoleEbi;
import auth.role.vo.RoleModel;
import com.opensymphony.xwork2.ActionContext;
import auth.emp.business.ebi.EmpEbi;
import auth.emp.vo.EmpModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import utils.base.BaseAction;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

import static org.apache.struts2.ServletActionContext.getRequest;

/**
 * Created by RZ on 6/22/16.
 */
@Controller("empAction")
@Scope("prototype")
public class EmpAction extends BaseAction{
    @Resource
    private EmpEbi empEbi;
    @Resource
    private DeptEbi deptEbi;
    @Resource
    private RoleEbi roleEbi;
    @Resource
    private ResEbi resEbi;

    public EmpModel em = new EmpModel();
    public EmpQueryModel eqm = new EmpQueryModel();

    public Long[] roles;

    public String login() {
        HttpServletRequest request = getRequest();
        String loginIp = request.getHeader("x-forwarded-for");
        if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getHeader("Proxy-Client-IP");
        }
        if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getRemoteAddr();
        }
        em = empEbi.login(em.getUsername(),em.getPwd(),loginIp);

        if(em==null) {
            this.addActionError("username/password invalid!");
            return "login";
        }
        ActionContext.getContext().getSession().put(EmpModel.LOGIN_SUCCESS_EMP,em);
        List<ResModel> resList = resEbi.getResByEmpUuid(em.getUuid());
        StringBuilder sb= new StringBuilder();
        for (ResModel resModel : resList) {
            sb.append(resModel.getUrl()+",");
        }
        em.setReses(sb.toString());
        return "loginSuccess";
    }

    public String list() {
        List<DeptModel> deptList = deptEbi.getAll();
        put("deptList",deptList);
        setTotalCount(empEbi.getCount(eqm));
        List<EmpModel> empList = empEbi.getAll(eqm,pageNum,pageCount);
        put("empList",empList);
        return "list";
    }

    public String input() {
        if (em!=null && em.getUuid()!=null) {
            em = empEbi.getByUuid(em.getUuid());
            Set<RoleModel> temp = em.getRoles();
            roles = new Long[temp.size()];
            int i=0;
            for (RoleModel roleModel : temp) {
                roles[i++] = roleModel.getUuid();
            }
        }
        List<DeptModel> deptList = deptEbi.getAll();
        put("deptList",deptList);
        //import the role list
        List<RoleModel> roleList = roleEbi.getAll();
        put("roleList",roleList);
        return INPUT;
    }

    public String save() {
        if(em!=null && em.getUuid()!=null) {
            empEbi.update(em, roles);
        }else {
            empEbi.save(em, roles);
        }
        return "toList";
    }

    public String delete() {
        empEbi.delete(em);
        return "delete";
    }

    public String logout() {
        putSession(EmpModel.LOGIN_SUCCESS_EMP,null);
        return "login";
    }

    public String toChangePwd() {
        return "toChangePwd";
    }

    public String newPwd;
    public String changePwd() {
        if (getLogin()==null) {
            return "login";
        }
        boolean success = empEbi.changePwd(getLogin().getUsername(),em.getPwd(),newPwd);
        if(success) {
            return login();
        }else {
            addActionError("密码验证失败,请重试");
            return "toChangePwd";
        }
    }
}
