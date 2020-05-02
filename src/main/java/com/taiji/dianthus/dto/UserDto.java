package com.taiji.dianthus.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

/**
 * @ClassName UserDto
 * @Description
 * @Author H.M
 * @Date 2020/1/8
 */

@ApiModel(description = "用户dto")
public class UserDto {

    @ApiModelProperty(value = "主键", name = "id")
    private String  id;

    @ApiModelProperty(value = "用户名", name = "name")
    private String name;

    @ApiModelProperty(value = "登陆名称", name = "userLoginName")
    private String userLoginName;

    @ApiModelProperty(value = "登陆密码", name = "userPassword")
    private String userPassword;

    @ApiModelProperty(value = "邮件地址", name = "userMail")
    private String userMail;

    @ApiModelProperty(value = "用户手机号", name = "userPhone")
    private String userPhone;

    @ApiModelProperty(value = "组织id", name = "orgId")
    private String orgId;

    @ApiModelProperty(value = "上次登陆时间", name = "lastLoginTime")
    private String lastLoginTime;

    @ApiModelProperty(value = "用户排序", name = "userOrder")
    private Integer userOrder;

    @ApiModelProperty(value = "用户座机", name = "officePhone")
    private String officePhone;

    @ApiModelProperty(value = "用户职位", name = "userDuty")
    private String userDuty;

    @ApiModelProperty(value = "用户职责", name = "userPosition")
    private String userPosition;

    @ApiModelProperty(value = "用户性别", name = "userSex")
    private String userSex;

    @ApiModelProperty(value = "角色ID", name = "roleId")
    private String roleId;


    public Integer getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(Integer userOrder) {
        this.userOrder = userOrder;
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

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getUserDuty() {
        return userDuty;
    }

    public void setUserDuty(String userDuty) {
        this.userDuty = userDuty;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
