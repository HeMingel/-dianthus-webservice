package com.taiji.dianthus.controller.sys;


import com.taiji.dianthus.common.BasicController;
import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysMenu;
import com.taiji.dianthus.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName SysMenuViewController
 * @Description 系统菜单相关
 * @Author H.M
 * @Date 2020/3/17
 */
@RequestMapping(value = "/sys/menu")
@Controller
public class SysMenuViewController extends BasicController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取主菜单列表
     * @param model
     * @param pageSize
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/list")
    public String showMenuList(Model model,Integer pageSize,Integer currentPage) {
        pageSize = getPageSize(pageSize);
        currentPage = getCurrentPage(currentPage);
        BusinessMessage message = menuService.listRootMenus(pageSize,currentPage);
        model.addAttribute("menu",message.getData());
        return "pages/sys/menuList";
    }

    /**
     * 跳转新增/编辑页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/add")
    public String addMenu(Model model, String id) {
        DSysMenu menu = new   DSysMenu ();
        if (!StringUtils.isEmpty(id)) {
            menu = menuService.getByMenuId(id);
        }
        model.addAttribute("menu", menu);
        return "pages/sys/menuAdd";
    }

    /**
     * 跳转子菜单
     * @param model
     * @param menuId
     * @return
     */
    @RequestMapping(value ="/subMenu")
    public String showSubMenu(Model model ,String menuId) {
        List< DSysMenu > menus = menuService.listChildMenu(menuId);
        DSysMenu  parentMenu = menuService.getByMenuId(menuId);
        model.addAttribute("menus",menus);
        model.addAttribute("parentMenu",parentMenu);
        return "pages/sys/subMenuList";
    }

    /**
     * 跳转新增/编辑页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/addSubMenu")
    public String addSubMenu(Model model, String id,String parentId) {
        DSysMenu  menu = new   DSysMenu ();

        if (!StringUtils.isEmpty(id)) {
            //编辑
            menu = menuService.getByMenuId(id);
            parentId = menu.getParentId();
        }
        DSysMenu  parentMenu = menuService.getByMenuId(parentId);
        model.addAttribute("submenu", menu);
        model.addAttribute("menu", parentMenu);
        return "pages/sys/menuSubmenuAdd";
    }

}
