package com.taiji.dianthus.dto;

/**
 * @ClassName UserDto
 * @Description
 * @Author H.M
 * @Date 2020/1/8
 */
public class UserDto {


    private String  id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 登陆名称
     */
    private String userLoginName;

    /**
     * 登陆密码
     */
    private String userPassword;


    /**
     * 邮件地址
     */
    private String userMail;

    /**
     * 联系方式
     */
    private String userPhone;

    /**
     * 组织id
     */
    private String  orgId;

    /**
     * 上次登陆时间
     */
    private String lastLoginTime;

    /**
     * 角色ID
     */
    private String roleId;


    /**
     * 用户排序
     */
    private Integer userOrder;

    /**
     * 用户座机
     */
    private String userLandline;

    /**
     * 用户职位
     */
    private String userDianthus;

    /**
     * 用户职位
     */
    private String userPosition;

    /**
     * 用户性别
     */
    private String userSex;


    public Integer getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(Integer userOrder) {
        this.userOrder = userOrder;
    }

    public String getUserLandline() {
        return userLandline;
    }

    public void setUserLandline(String userLandline) {
        this.userLandline = userLandline;
    }

    public String getUserDianthus() {
        return userDianthus;
    }

    public void setUserDianthus(String userDianthus) {
        this.userDianthus = userDianthus;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
