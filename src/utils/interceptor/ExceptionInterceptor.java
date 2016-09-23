package utils.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import utils.exception.AppException;

/**
 * Created by RZ on 7/1/16.
 */
public class ExceptionInterceptor extends AbstractInterceptor {
    public String intercept(ActionInvocation actionInvocation) throws Exception {
       try {
           return actionInvocation.invoke();
       }catch (AppException e) {
           ActionSupport as = (ActionSupport) actionInvocation.getAction();
           as.addActionError(as.getText(e.getMessage()));
           return "error";
       }catch (Exception e) {
           //			ActionSupport as = (ActionSupport) invocation.getAction();
//			as.addActionError("对不起，服务器已关闭，请联系管理员！");
//			return "error";
           //记录日志
           //发送日志到程序员邮箱
           //报警
           e.printStackTrace();
           return actionInvocation.invoke();
       }
    }
}
