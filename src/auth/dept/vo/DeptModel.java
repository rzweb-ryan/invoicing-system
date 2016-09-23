package auth.dept.vo;

/**
 * Created by RZ on 6/22/16.
 */
public class DeptModel {
    private Long uuid;
    private String name;
    private String tel;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
