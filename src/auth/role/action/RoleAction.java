package auth.role.action;

import auth.menu.business.ebi.MenuEbi;
import auth.menu.vo.MenuModel;
import auth.res.business.ebi.ResEbi;
import auth.res.vo.ResModel;
import auth.role.business.ebi.RoleEbi;
import auth.role.vo.RoleModel;
import auth.role.vo.RoleQueryModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import utils.base.BaseAction;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by RZ on 7/1/16.
 */
@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction {
    @Resource
    private RoleEbi roleEbi;
    @Resource
    private ResEbi resEbi;
    @Resource
    private MenuEbi menuEbi;
    public RoleModel rm = new RoleModel();
    public RoleQueryModel rqm = new RoleQueryModel();

    public Long[] resources;
    public String list() {
        setTotalCount(roleEbi.getCount(rqm));
        List<RoleModel> roleList =  roleEbi.getAll(rqm,pageNum,pageCount);
        put("roleList", roleList);
        return "list";
    }
    public Long[] mmUuids;

    public String input() {
        List<ResModel> resList = resEbi.getAll();
        put("resList",resList);
        List<MenuModel> menuList = menuEbi.getMenus();
        put("menuList",menuList);
        if(rm!=null && rm.getUuid()!=null) {
            rm = roleEbi.getByUuid(rm.getUuid());
            resources = new Long[rm.getReses().size()];
            int i=0;
            for (ResModel resModel : rm.getReses()) {
                resources[i++] = resModel.getUuid();
            }
            mmUuids = new Long[rm.getMenus().size()];
            i=0;
            for (MenuModel menuModel : rm.getMenus()) {
                mmUuids[i++] = menuModel.getUuid();
            }
        }
        return "input";
    }

    public String save() {
        if(rm!=null && rm.getUuid()!=null) {
            roleEbi.update(rm,resources,mmUuids);
        }else {
            roleEbi.save(rm,resources,mmUuids);
        }
        return "toList";
    }

    public String delete() {
        roleEbi.delete(rm);
        return "delete";
    }
}
