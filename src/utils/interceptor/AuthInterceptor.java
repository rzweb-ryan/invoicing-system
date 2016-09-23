package utils.interceptor;

import auth.emp.vo.EmpModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;
import utils.exception.AppException;

/**
 * Created by RZ on 7/1/16.
 */
public class AuthInterceptor extends AbstractInterceptor {
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        //1.login
        ActionContext as = actionInvocation.getInvocationContext();
        //1.5  load resAll->ContextListener
        //2.load resList
        String className = actionInvocation.getProxy().getAction().getClass().getName();
        String methodName = actionInvocation.getProxy().getMethod();
        String fullName = className+"."+methodName;
        //3.invoke(contains) or not(!)
        String resAll = (String) ServletActionContext.getServletContext().getAttribute("allRes");
        //System.out.println("系统资源:"+resAll);
        if(!resAll.contains(fullName)) {
            return actionInvocation.invoke();
        }
        EmpModel emLogin = (EmpModel) as.getSession().get(EmpModel.LOGIN_SUCCESS_EMP);
        String resEmp = emLogin.getReses();
        //System.out.println("角色资源:"+resEmp);
        if(resEmp.contains(fullName)) {

            return actionInvocation.invoke();
        }
        throw new AppException("权限不足!");
    }
}
