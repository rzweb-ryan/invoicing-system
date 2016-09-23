package auth.menu.action;

import auth.menu.business.ebi.MenuEbi;
import auth.menu.vo.MenuModel;
import auth.menu.vo.MenuQueryModel;
import auth.role.business.ebi.RoleEbi;
import auth.role.vo.RoleModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import utils.base.BaseAction;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

/**
 * Created by RZ on 7/2/16.
 */
@Controller("menuAction")
@Scope("prototype")
public class MenuAction extends BaseAction {
    @Resource
    private MenuEbi menuEbi;
    @Resource
    private RoleEbi roleEbi;
    public MenuModel mm = new MenuModel();
    public MenuQueryModel mqm = new MenuQueryModel();

    public String list() {
        //load menu-level-one
        List<MenuModel> menu1 = menuEbi.getLevel1Menu();
        put("menu1",menu1);

        setTotalCount(menuEbi.getCount(mqm));
        List<MenuModel> menuList = menuEbi.getAll(mqm,pageNum,pageCount);
        put("menuList",menuList);
        return "list";
    }
    public String input() {
        if(mm!=null && mm.getUuid()!=null) {
            mm = menuEbi.getByUuid(mm.getUuid());
            Set<RoleModel> roles = mm.getRoles();
            rmUuids = new Long[roles.size()];
            int i = 0;
            for (RoleModel role : roles) {
                rmUuids[i++] = role.getUuid();
            }
        }
        List<MenuModel> parentList = menuEbi.getParentList();
        put("parentList",parentList);
        List<RoleModel> roleList = roleEbi.getAll();
        put("roleList",roleList);
        return "input";
    }

    public Long[] rmUuids;
    public String save() {
        if(mm!=null && mm.getUuid()!=null) {
            menuEbi.update(mm,rmUuids);
        } else {
            menuEbi.save(mm,rmUuids);
        }
        return "toList";
    }

    public String delete() {
        if(mm!=null && mm.getUuid()!=null) {
            menuEbi.delete(mm);
        }
        return "delete";
    }

    public void showMenu() throws IOException {
        HttpServletResponse response  = getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        StringBuilder json = new StringBuilder();
        String root = getRequest().getParameter("root");
        Long empUuid = getLogin().getUuid();
        json.append("[");
        if (root==null||root.equals("source")) {
            List<MenuModel> menu1 = menuEbi.getMenuByEmpAndPuuid(empUuid,MenuModel.MENU_SYSTEM_MENU_UUID);
            for (MenuModel menuModel : menu1) {
                json.append("{\"text\":\"");
                json.append(menuModel.getName());
                json.append("\",\"id\":\"");
                json.append(menuModel.getUuid());
                json.append("\",\"classes\":\"folder\",\"hasChildren\":true},");
            }
        }else {
            Long menuUuid = new Long(root);
            List<MenuModel> menu2 = menuEbi.getMenuByEmpAndPuuid(empUuid,menuUuid);
            for (MenuModel menuModel : menu2) {
                json.append("{\"text\":\"<a class='hei' target='main' href='");
                json.append(menuModel.getUrl());
                json.append("'>");
                json.append(menuModel.getName());
                json.append("</a>\",\"id\":\"");
                json.append(menuModel.getUuid());
                json.append("\",\"classes\":\"file\"},");
            }
        }
        json.deleteCharAt(json.length()-1);
        json.append("]");
        pw.print(json.toString());
    }

}
