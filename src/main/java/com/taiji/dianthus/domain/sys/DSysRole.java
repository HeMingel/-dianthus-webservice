package com.taiji.dianthus.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName DsysRole
 * @Description
 * @Author H.M
 * @Date 2020/3/16
 */
@Entity
@Table(name = "d_sys_role")
@Proxy(lazy = false)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class DSysRole implements Serializable {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id")
    private String id;

    /**
     *角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     *角色说明
     */
    @Column(name ="role_desc")
    private String roleDesc;

    /**
     *备注
     */
    @Column(name ="role_remark")
    private String roleRemark;


    /**
     *创建时间戳
     */
    @Column(name ="create_time" ,insertable =false,updatable = false)
    private Date createTime;

    @Column(name="enable_flag" ,insertable =false,updatable = false)
    private Integer enableFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark;
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
}
