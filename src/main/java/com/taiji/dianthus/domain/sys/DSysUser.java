package com.taiji.dianthus.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName User
 * @Description 推送用户表 实体类
 * @Author H.M
 * @Date 2020/3/16
 */
@Entity
@Table(name = "d_sys_user")
@Proxy(lazy = false)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@ApiModel(description = "用户表")
public class DSysUser {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    @ApiModelProperty(value = "主键", name = "id")
    private String id;

    @Column(name = "push_id")
    @ApiModelProperty(value = "平台推送的ID", name = "push_id")
    private String pushId;

    @Column(name = "user_name")
    @ApiModelProperty(value = "用户名", name = "name")
    private String name;

    @Column(name = "user_login_name")
    @ApiModelProperty(value = "登陆名称", name = "userLoginName")
    private String userLoginName;

    @Column(name = "user_password")
    @ApiModelProperty(value = "登陆密码", name = "userPassword")
    private String userPassword;

    @Column(name = "user_mail")
    @ApiModelProperty(value = "邮件地址", name = "userMail")
    private String userMail;

    @Column(name = "user_phone")
    @ApiModelProperty(value = "用户手机号", name = "userPhone")
    private String userPhone;

    @Column(name = "org_id")
    @ApiModelProperty(value = "组织id", name = "orgId")
    private String orgId;

    @Column(name = "last_login_time")
    @ApiModelProperty(value = "上次登陆时间", name = "lastLoginTime")
    private String lastLoginTime;

    @Column(name = "user_order")
    @ApiModelProperty(value = "用户排序", name = "userOrder")
    private Integer userOrder;

    @Column(name = "user_office_phone")
    @ApiModelProperty(value = "用户座机", name = "officePhone")
    private String officePhone;

    @Column(name = "user_duty")
    @ApiModelProperty(value = "用户职位", name = "userDuty")
    private String userDuty;

    @Column(name = "user_position")
    @ApiModelProperty(value = "用户职责", name = "userPosition")
    private String userPosition;

    @Column(name = "user_sex")
    @ApiModelProperty(value = "用户性别", name = "userSex")
    private String userSex;


    /**
     * 创建时间戳
     */
    @Column(name = "create_time", insertable = false, updatable = false)
    private Date createTime;


    @Column(name = "enable_flag", insertable = false, updatable = false)
    private Integer enableFlag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
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
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Integer enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getUserDuty() {
        return userDuty;
    }

    public void setUserDuty(String userDuty) {
        this.userDuty = userDuty;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + name + '\'' +
                ", userCode='" + pushId + '\'' +
                ", userDianthusb='" + userDuty + '\'' +
                ", userLoginName='" + userLoginName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", createTime=" + createTime +
                ", enableFlag=" + enableFlag +
                ", userPhone='" + userPhone + '\'' +
                ", userOrder=" + userOrder +
                ", userPosition='" + userPosition + '\'' +
                '}';
    }
}
