package com.taiji.dianthus.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ClassName DSysMenu
 * @Description
 * @Author H.M
 * @Date 2020/3/16
 */
@Entity
@Table(name = "d_sys_menu")
@Proxy(lazy = false)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@ApiModel(description = "系统菜 单表")
public class DSysMenu {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    @ApiModelProperty(value = "主键", name = "id")
    private String id;

    @Column(name = "menu_name")
    @ApiModelProperty(value = "菜单名称", name = "menuName")
    private String menuName;

    @Column(name = "menu_desc")
    @ApiModelProperty(value = "菜单说明", name = "menuDesc")
    private String menuDesc;

    @Column(name = "menu_remark")
    @ApiModelProperty(value = "备注", name = "menuRemark")
    private String menuRemark;

    @Column(name = "menu_order")
    @ApiModelProperty(value = "排序", name = "menuOrder")
    private Integer menuOrder;

    @Column(name = "menu_url")
    @NotBlank(message = "菜单链接不能为空")
    @ApiModelProperty(value = "菜单链接", name = "menuUrl")
    private String menuUrl;

    @Column(name = "parent_id")
    @ApiModelProperty(value = "父级ID", name = "parentId")
    private String parentId;

    @Column(name = "menu_ico")
    @ApiModelProperty(value = "图标", name = "menuIco")
    private String menuIco;

    @Column(name = "has_submenu")
    @ApiModelProperty(value = "是否有子菜单", name = "hasSubmenu")
    private Integer hasSubmenu;

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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public String getMenuRemark() {
        return menuRemark;
    }

    public void setMenuRemark(String menuRemark) {
        this.menuRemark = menuRemark;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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


    public Integer getHasSubmenu() {
        return hasSubmenu;
    }

    public void setHasSubmenu(Integer hasSubmenu) {
        this.hasSubmenu = hasSubmenu;
    }

    public String getMenuIco() {
        return menuIco;
    }

    public void setMenuIco(String menuIco) {
        this.menuIco = menuIco;
    }
}
