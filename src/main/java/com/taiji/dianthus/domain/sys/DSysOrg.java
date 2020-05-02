package com.taiji.dianthus.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName DSysOrg
 * @Description
 * @Author H.M
 * @Date 2020/3/16
 */
@Entity
@Table(name = "d_sys_org")
@Proxy(lazy = false)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@ApiModel(description = "系统组织表")
public class DSysOrg implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "jpa-uuid")
    @ApiModelProperty(value = "主键", name = "id")
    private String id;

    @Column(name = "push_id")
    @ApiModelProperty(value = "平台推送的ID", name = "pushId")
    private String pushId;

    @Column(name = "组织名称")
    @NotNull(message = "组织名不能为空")
    @ApiModelProperty(value = "名称", name = "orgName")
    private String orgName;

    @Column(name = "parent_id")
    @ApiModelProperty(value = "上级ID", name = "parentId")
    private String parentId;

    @Column(name = "org_level")
    @ApiModelProperty(value = "组织层级", name = "orgLevel")
    private Integer orgLevel;

    @Column(name = "org_order")
    @ApiModelProperty(value = "组织排序", name = "orgOrder")
    private Integer orgOrder;

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Integer getOrgOrder() {
        return orgOrder;
    }

    public void setOrgOrder(Integer orgOrder) {
        this.orgOrder = orgOrder;
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
