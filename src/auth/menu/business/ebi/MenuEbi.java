package auth.menu.business.ebi;

import auth.menu.vo.MenuModel;
import org.springframework.transaction.annotation.Transactional;
import utils.base.BaseEbi;

import java.util.List;

/**
 * Created by RZ on 7/2/16.
 */
@Transactional
public interface MenuEbi extends BaseEbi<MenuModel> {
    public List<MenuModel> getParentList();

    public void update(MenuModel mm, Long[] rmUuids);

    public void save(MenuModel mm, Long[] rmUuids);

    /**
     * get level 1 menu which parent = system_menu
      * @return List<MenuModel>
     */
    public List<MenuModel> getLevel1Menu();


    /**
     * get menus except system_menu
     * @return List<MenuModel>
     */
    public List<MenuModel> getMenus();

    /**
     * get menuList by parent.uuid
     * @param uuid
     * @return
     */
    public List<MenuModel> getLevel2Menu(Long uuid);

    /**
     * get menus by emp and menu.parent.uuid
     * @param empUuid emp.uuid
     * @param menuUuid menu.parent.uuid
     * @return List<MenuModel>
     */
    public List<MenuModel> getMenuByEmpAndPuuid(Long empUuid, Long menuUuid);
}
