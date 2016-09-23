package auth.menu.dao.impl;

import auth.menu.dao.dao.MenuDao;
import auth.menu.vo.MenuModel;
import auth.menu.vo.MenuQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import utils.base.BaseImpl;
import utils.base.BaseQueryModel;

import java.util.List;

/**
 * Created by RZ on 7/2/16.
 */
@Repository("menuDao")
public class MenuDaoImpl extends BaseImpl<MenuModel> implements MenuDao {
    public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
        //remove system-menu from list
        dc.add(Restrictions.not(Restrictions.eq("uuid",MenuModel.MENU_SYSTEM_MENU_UUID)));
        MenuQueryModel mqm = (MenuQueryModel)bqm;
        if(mqm!=null && mqm.getParent()!=null && mqm.getParent().getUuid()!=-1) {
            dc.add(Restrictions.eq("parent",mqm.getParent()));
        }
        if(mqm!=null && mqm.getName()!=null) {
            dc.add(Restrictions.like("name","%"+mqm.getName().trim()+"%"));
        }
    }

    public List<MenuModel> getParentList() {
        String hql = "from MenuModel where uuid = ? or parent.uuid = ?";
        return hibernateTemplate.find(hql,MenuModel.MENU_SYSTEM_MENU_UUID,MenuModel.MENU_SYSTEM_MENU_UUID);
    }

    public List<MenuModel> getLever1Menu() {
        return getLever2Menu(MenuModel.MENU_SYSTEM_MENU_UUID);
    }

    public List<MenuModel> getMenus() {
        String hql = "from MenuModel where uuid != ? ";
        return  hibernateTemplate.find(hql,MenuModel.MENU_SYSTEM_MENU_UUID);
    }

    public List<MenuModel> getLever2Menu(Long uuid) {
        String hql = "from MenuModel where parent.uuid = ?";
        return hibernateTemplate.find(hql,uuid);
    }

    public List<MenuModel> getMenuByEmpAndPuuid(Long empUuid, Long menuUuid) {
        String hql = "select distinct menus from EmpModel emp join emp.roles roles join roles.menus menus where emp.uuid=? and menus.parent.uuid=? order by menus.uuid";
        return hibernateTemplate.find(hql,empUuid,menuUuid);
    }
}
