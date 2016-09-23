package auth.menu.business.ebo;

import auth.menu.business.ebi.MenuEbi;
import auth.menu.dao.dao.MenuDao;
import auth.menu.vo.MenuModel;
import auth.role.vo.RoleModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.base.BaseQueryModel;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by RZ on 7/2/16.
 */
@Transactional
@Service("menuEbi")
public class MenuEbo implements MenuEbi{
    @Resource
    private MenuDao menuDao;
    public List<MenuModel> getAll() {
        return menuDao.getAll();
    }


    public void save(MenuModel menuModel) {

    }


    public MenuModel getByUuid(Serializable uuid) {
        return menuDao.get(uuid);
    }


    public void update(MenuModel menuModel) {

    }


    public void delete(MenuModel menuModel) {
        menuDao.delete(menuModel);
    }


    public List<MenuModel> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount) {
        return menuDao.getAll(bqm, pageNum, pageCount);
    }


    public Integer getCount(BaseQueryModel bqm) {
        return menuDao.getCount(bqm);
    }

    /**
     * parent list which uuid=1 or .parent.uuid=1
     * @return List<MenuModel>
     */
    public List<MenuModel> getParentList() {
        return menuDao.getParentList();
    }

    public void update(MenuModel mm, Long[] rmUuids) {
        MenuModel temp = menuDao.get(mm.getUuid());
        temp.setName(mm.getName());
        temp.setParent(mm.getParent());
        temp.setUrl(mm.getUrl());
        //todo
        Set<RoleModel> roles = new HashSet<RoleModel>();
        for (Long rmUuid : rmUuids) {
            RoleModel temp1 = new RoleModel();
            temp1.setUuid(rmUuid);
            roles.add(temp1);
        }
        temp.setRoles(roles);
    }

    public void save(MenuModel mm, Long[] rmUuids) {
        Set<RoleModel> roles = new HashSet<RoleModel>();
        for (Long rmUuid : rmUuids) {
            RoleModel temp = new RoleModel();
            temp.setUuid(rmUuid);
            roles.add(temp);
        }
        mm.setRoles(roles);
        menuDao.save(mm);
    }

    public List<MenuModel> getLevel1Menu() {
        return menuDao.getLever1Menu();
    }

    public List<MenuModel> getMenus() {
        return menuDao.getMenus();
    }

    public List<MenuModel> getLevel2Menu(Long uuid) {
        return menuDao.getLever2Menu(uuid);
    }

    public List<MenuModel> getMenuByEmpAndPuuid(Long empUuid, Long menuUuid) {
        return menuDao.getMenuByEmpAndPuuid(empUuid,menuUuid);
    }
}
