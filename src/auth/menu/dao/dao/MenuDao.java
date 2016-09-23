package auth.menu.dao.dao;

import auth.menu.vo.MenuModel;
import utils.base.BaseDao;

import java.util.List;

/**
 * Created by RZ on 7/2/16.
 */
public interface MenuDao extends BaseDao<MenuModel> {
    public List<MenuModel> getParentList();

    public List<MenuModel> getLever1Menu();

    public List<MenuModel> getMenus();

    public List<MenuModel> getLever2Menu(Long uuid);

    public List<MenuModel> getMenuByEmpAndPuuid(Long empUuid, Long menuUuid);
}
