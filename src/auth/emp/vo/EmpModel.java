package auth.emp.vo;

import auth.dept.vo.DeptModel;
import auth.role.vo.RoleModel;
import utils.format.FormatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by RZ on 6/22/16.
 */
public class EmpModel {
    public static final String LOGIN_SUCCESS_EMP = "loginEmp";

    public static final String EMP_GENDER_OF_MAN_VIEW = "MAN";
    public static final String EMP_GENDER_OF_FEMALE_VIEW = "FEMALE";
    public static final Integer EMP_GENDER_OF_MAN = 1;
    public static final Integer EMP_GENDER_OF_FEMALE = 0;
    public static final Map<Integer,String> genderMap = new HashMap<Integer,String>();
    static {
        genderMap.put(EMP_GENDER_OF_FEMALE,EMP_GENDER_OF_FEMALE_VIEW);
        genderMap.put(EMP_GENDER_OF_MAN,EMP_GENDER_OF_MAN_VIEW);
    }

    //original data
    private Long uuid;
    private String username;
    private String pwd;
    private String name;
    private String email;
    private String tel;
    private String address;
    private Integer gender;
    private Long birthday;
    private DeptModel dm;
    private Set<RoleModel> roles;

    //helper data res accordingly
    private String reses;

    public String getReses() {
        return reses;
    }

    public void setReses(String reses) {
        this.reses = reses;
    }

    public Set<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleModel> roles) {
        this.roles = roles;
    }

    //extra data
    private Long lastLoginTime;
    private String lastLoginIp;
    private Long loginTimes;

    public Long getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Long loginTimes) {
        this.loginTimes = loginTimes;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        lastLoginTimeView = FormatUtil.formatDateTime(lastLoginTime);
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    //property view

    private String birthdayView;
    private String genderView;
    private String lastLoginTimeView;

    public String getLastLoginTimeView() {
        return lastLoginTimeView;
    }

    public String getBirthdayView() {
        return birthdayView;
    }

    public String getGenderView() {
        return genderView;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        genderView = genderMap.get(gender);
        this.gender = gender;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        birthdayView = FormatUtil.formatDate(birthday);
        this.birthday = birthday;
    }

    public DeptModel getDm() {
        return dm;
    }

    public void setDm(DeptModel dm) {
        this.dm = dm;
    }
}
