package auth.res.action;

import auth.res.business.ebi.ResEbi;
import auth.res.vo.ResModel;
import auth.res.vo.ResQueryModel;
import auth.role.business.ebi.RoleEbi;
import auth.role.vo.RoleModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import utils.base.BaseAction;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by RZ on 7/1/16.
 */
@Controller("resAction")
@Scope("prototype")
public class ResAction extends BaseAction {
    @Resource
    private ResEbi resEbi;
    public ResModel rm = new ResModel();
    public ResQueryModel rqm = new ResQueryModel();
    public String list() {

        setTotalCount(resEbi.getCount(rqm));
        List<ResModel> resList = resEbi.getAll(rqm,pageNum,pageCount);
        put("resList",resList);
        return "list";
    }
    public String input() {

        if(rm!=null&&rm.getUuid()!=null) {
            rm = resEbi.getByUuid(rm.getUuid());
        }
        return "input";
    }
    public String save() {
        if(rm!=null && rm.getUuid()!=null) {
            resEbi.update(rm);
        }else  {
            resEbi.save(rm);
        }
        return "toList";
    }

    public String delete() {
        resEbi.delete(rm);
        return "delete";
    }
}
