package utils.interceptor;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import auth.emp.vo.EmpModel;

/**
 * Created by RZ on 6/22/16.
 */
public class LoginInterceptor extends AbstractInterceptor {
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        String className = actionInvocation.getProxy().getAction().getClass().getName();
        String methodName = actionInvocation.getProxy().getMethod();
        String fullName =className+"."+methodName;
        String actionName = actionInvocation.getProxy().getActionName();
            if ("auth.emp.action.EmpAction.login".equals(fullName)||"page_login".equals(actionName)) {
                return actionInvocation.invoke();
            }
        if (actionInvocation.getInvocationContext().getSession().get(EmpModel.LOGIN_SUCCESS_EMP)==null){
            return "login";
        }else {
            return actionInvocation.invoke();
        }
    }
}
