package auth.dept.action;

import auth.dept.business.ebi.DeptEbi;
import auth.dept.vo.DeptModel;
import auth.dept.vo.DeptQueryModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import utils.base.BaseAction;

import java.util.List;

/**
 * Created by RZ on 6/22/16.
 */
@Controller("deptAction")
@Scope("prototype")
public class DeptAction extends BaseAction {
    public DeptModel dm = new DeptModel();
    public DeptQueryModel dqm= new DeptQueryModel();
    @Resource
    private DeptEbi deptEbi;

    //redirect to page "list"
    public String list() {
        totalCount = deptEbi.getCount(dqm);
        setTotalCount(totalCount);
        List<DeptModel> deptList = deptEbi.getAll(dqm,pageNum,pageCount);
//        ActionContext.getContext().put("deptList",deptList);
        put("deptList",deptList);
        return LIST;
    }

   //redirect to input page
    public String input() {
        if (dm!=null && dm.getUuid()!=null) {
            dm = deptEbi.getByUuid(dm.getUuid());
        }
        return INPUT;
    }
    //input new dept
    public String save() {
        if ((dm!=null && dm.getUuid()!=null)) {
            deptEbi.update(dm);
        }else {
            deptEbi.save(dm);
        }
        return "toList";
    }

    public String delete() {
        deptEbi.delete(dm);
        return "delete";
    }

}
