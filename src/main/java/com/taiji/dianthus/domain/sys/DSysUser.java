package com.taiji.dianthus.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class DSysUser {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;

    /**
     * 平台推送的ID
     */
    @Column(name = "push_id")
    private String pushId;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String name;

    /**
     * 登陆名称
     */
    @Column(name = "user_login_name")
    private String userLoginName;

    /**
     * 登陆密码
     */
    @Column(name = "user_password")
    private String userPassword;


    /**
     * 邮件地址
     */
    @Column(name = "user_mail")
    private String userMail;

    /**
     * 联系方式
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * 组织id
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 上次登陆时间
     */
    @Column(name = "last_login_time")
    private String lastLoginTime;

    /**
     * 用户排序
     */
    @Column(name = "user_order")
    private Integer userOrder;

    /**
     * 用户座机
     */
    @Column(name = "userLandline")
    private String userLandline;

    /**
     * 用户职位
     */
    @Column(name = "user_dianthus")
    private String userDianthus;

    /**
     * 用户职位
     */
    @Column(name = "user_position")
    private String userPosition;

    /**
     * 用户性别
     */
    @Column(name = "user_sex")
    private String userSex;

    /**
     * 是否兼任 1 是 0 否
     */
    @Column(name = "parttime")
    private Integer parttime;

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

    public Integer getParttime() {
        return parttime;
    }

    public void setParttime(Integer parttime) {
        this.parttime = parttime;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + name + '\'' +
                ", userCode='" + pushId + '\'' +
                ", userDianthusb='" + userDianthus + '\'' +
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
