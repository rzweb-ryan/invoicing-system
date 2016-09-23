package auth.role.vo;

import auth.menu.vo.MenuModel;
import auth.res.vo.ResModel;

import java.util.Set;

/**
 * Created by RZ on 7/1/16.
 */
public class RoleModel {
    private Long uuid;
    private String name;
    private String code;

    private Set<ResModel> reses;

    private Set<MenuModel> menus;

    public Set<MenuModel> getMenus() {
        return menus;
    }

    public void setMenus(Set<MenuModel> menus) {
        this.menus = menus;
    }

    public Set<ResModel> getReses() {
        return reses;
    }


    public void setReses(Set<ResModel> reses) {
        this.reses = reses;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
