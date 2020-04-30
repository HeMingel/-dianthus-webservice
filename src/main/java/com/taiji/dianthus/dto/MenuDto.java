package com.taiji.dianthus.dto;

import com.taiji.dianthus.domain.sys.DSysMenu;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MenuDto
 * @Description
 * @Author H.M
 * @Date 2019/10/25
 */
public class MenuDto {


    private String id;

    /**
     * 菜单名称
     */

    private String menuName;

    /**
     * 菜单说明
     */

    private String menuDesc;

    /**
     * 备注
     */

    private String menuRemark;

    /**
     * 排序
     */

    private Integer menuOrder;

    /**
     * 菜单链接
     */

    private String menuUrl;

    /**
     * 父级ID
     */

    private String parentId;


    /**
     * 图标
     */
    private String menuIco;


    private List<DSysMenu> child;

    /**
     * 是否有子菜单
     */

    private Integer hasSubmenu;

    /**
     * 创建时间戳
     */

    private Date createTime;


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

    public List<DSysMenu> getChild() {
        return child;
    }

    public void setChild(List<DSysMenu> child) {
        this.child = child;
    }

    public String getMenuIco() {
        return menuIco;
    }

    public void setMenuIco(String menuIco) {
        this.menuIco = menuIco;
    }
}


