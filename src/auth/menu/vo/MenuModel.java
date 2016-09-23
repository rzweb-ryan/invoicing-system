package auth.menu.vo;

import auth.role.vo.RoleModel;

import java.util.List;
import java.util.Set;

/**
 * Created by RZ on 7/2/16.
 */
public class MenuModel{
    public static final Long MENU_SYSTEM_MENU_UUID = 1L;

    private Long uuid;
    private String name;
    private String url;
    //one-to-many-self
    private MenuModel parent;
    //many-to-one-self
    private Set<MenuModel> children;

    //many-to-many roleModel
    private Set<RoleModel> roles;

    public Set<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleModel> roles) {
        this.roles = roles;
    }

    public Set<MenuModel> getChildren() {
        return children;
    }

    public void setChildren(Set<MenuModel> children) {
        this.children = children;
    }

    public MenuModel getParent() {
        return parent;
    }

    public void setParent(MenuModel parent) {
        this.parent = parent;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
