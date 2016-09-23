package utils.base;

import auth.emp.vo.EmpModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by RZ on 6/28/16.
 */
public class BaseAction extends ActionSupport{
    //common redirect command
    protected static final String INPUT = "input";
    protected static final String TO_LIST  = "toList";
    protected static final String LIST = "list";

    //page redirect
    public Integer pageNum = 1;
    public Integer pageCount = 10;
    public Integer totalCount;
    public Integer totalPage;
    public Integer getPageNum() {
        return pageNum;
    }

    public String getActionName() {
        String className = this.getClass().getSimpleName();
        //EmpAction->Emp
        String tempName = className.substring(0,className.length()-6);
        //Emp->emp
        return tempName.substring(0,1).toLowerCase()+tempName.substring(1);
    }

    //data put method
    protected void put(String key, Object value) {
        ActionContext.getContext().put(key,value);
    }
    protected void putSession(String key, Object value) {
        ActionContext.getContext().getSession().put(key,value);
    }
    protected Object getSession(String key) {
        return ActionContext.getContext().getSession().get(key);
    }
    //get login emp
    protected EmpModel getLogin() {
        return (EmpModel) ActionContext.getContext().getSession().get(EmpModel.LOGIN_SUCCESS_EMP);
    }

    //get HttpServletResponse
    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }
    //get request
    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    protected void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        totalPage = ( totalCount + pageCount -1 )/pageCount;
        if(pageNum<1) pageNum = 1;
        if(pageNum > this.totalPage) pageNum = totalPage;

    }
}
