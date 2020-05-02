package com.taiji.dianthus.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName DSysUserRole
 * @Description
 * @Author H.M
 * @Date 2020/3/16
 */
@Entity
@Table(name = "d_sys_user_role")
@Proxy(lazy = false)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class DSysUserRole {

    @Id
    @Column(name = "role_id")
    private String roleId;

    /**
     *用户唯一标识
     */
    @Column(name ="user_id")
    private String userCode;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
