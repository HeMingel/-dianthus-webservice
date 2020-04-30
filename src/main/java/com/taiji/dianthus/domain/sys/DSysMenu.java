package com.taiji.dianthus.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
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
public class DSysMenu {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id")
    private String id;

    /**
     *菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     *菜单说明
     */
    @Column(name ="menu_desc")
    private String menuDesc;

    /**
     *备注
     */
    @Column(name ="menu_remark")
    private String menuRemark;

    /**
     *排序
     */
    @Column(name ="menu_order")
    private Integer menuOrder;

    /**
     *菜单链接
     */
    @Column(name ="menu_url")
    private String menuUrl;

    /**
     *父级ID
     */
    @Column(name ="parent_id")
    private String parentId;

    /**
     * 图标
     */
    @Column(name="menu_ico")
    private String menuIco;

    /**
     * 是否有子菜单
     */
    @Column(name="has_submenu")
    private Integer hasSubmenu;

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
