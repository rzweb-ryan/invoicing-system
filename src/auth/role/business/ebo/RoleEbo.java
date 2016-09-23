package auth.role.business.ebo;

import auth.menu.vo.MenuModel;
import auth.res.vo.ResModel;
import auth.role.business.ebi.RoleEbi;
import auth.role.dao.dao.RoleDao;
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
 * Created by RZ on 7/1/16.
 */
@Service("roleEbi")
@Transactional
public class RoleEbo implements RoleEbi {

    @Resource
    private RoleDao roleDao;
    public List<RoleModel> getAll() {
        return roleDao.getAll();
    }


    public void save(RoleModel roleModel) {
        roleDao.save(roleModel);
    }


    public RoleModel getByUuid(Serializable uuid) {
        return roleDao.get(uuid);
    }

    //deprecated
    public void update(RoleModel roleModel) {
        roleDao.update(roleModel);

    }


    public void delete(RoleModel roleModel) {
        roleDao.delete(roleModel);

    }


    public List<RoleModel> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount) {
        return roleDao.getAll(bqm,pageNum,pageCount);
    }


    public Integer getCount(BaseQueryModel bqm) {
        return roleDao.getCount(bqm);
    }

    public void update(RoleModel rm, Long[] resources) {

    }

    public void save(RoleModel rm, Long[] resources) {

    }

    public void update(RoleModel rm, Long[] resources, Long[] mmUuids) {
        RoleModel role = roleDao.get(rm.getUuid());
        role.setName(rm.getName());
        role.setCode(rm.getCode());
        Set<ResModel> reses = new HashSet<ResModel>();
        for (Long resource : resources) {
            ResModel temp = new ResModel();
            temp.setUuid(resource);
            reses.add(temp);
        }
        role.setReses(reses);
        Set<MenuModel> menus = new HashSet<MenuModel>();
        for (Long mmUuid : mmUuids) {
            MenuModel temp = new MenuModel();
            temp.setUuid(mmUuid);
            menus.add(temp);
        }
        role.setMenus(menus);
    }

    public void save(RoleModel rm, Long[] resources, Long[] mmUuids) {
        Set<ResModel> reses = new HashSet<ResModel>();
        for (Long resource : resources) {
            ResModel temp = new ResModel();
            temp.setUuid(resource);
            reses.add(temp);
        }
        rm.setReses(reses);
        Set<MenuModel> menus = new HashSet<MenuModel>();
        for (Long mmUuid : mmUuids) {
            MenuModel temp = new MenuModel();
            temp.setUuid(mmUuid);
            menus.add(temp);
        }
        rm.setMenus(menus);
        roleDao.save(rm);
    }
}
