package com.taiji.dianthus.controller;

import com.taiji.dianthus.domain.sys.DSysRole;
import com.taiji.dianthus.domain.sys.DSysUser;
import com.taiji.dianthus.dto.MenuDto;
import com.taiji.dianthus.security.DianthusSecurityMetadataSource;
import com.taiji.dianthus.service.sys.MenuRoleService;
import com.taiji.dianthus.service.sys.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

/**
 * @ClassName HomeController
 * @Description 登陆、文档入口
 * @Author H.M
 * @Date 2019/9/6
 */
@RequestMapping(value = "/home")
@Controller
public class HomeViewController {


    @Autowired
    private DianthusSecurityMetadataSource dianthusSecurityMetadataSource;

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private MenuRoleService menuRoleService;

    @RequestMapping(value = "/table")
    public String showSql() {
        return "pages/table";
    }

    @RequestMapping(value = "/main")
    public String showMain() {
        return "main";
    }

    @RequestMapping(value = "/index")
    public String showIndex() {
        return "index";
    }

    @RequestMapping(value = "/menu")
    public String showMenu(Model model) {
        DSysUser user = dianthusSecurityMetadataSource.currentUser();
        if (null != user) {
            // 获取用户角色
            Set<DSysRole> roles = roleUserService.listByUser(user.getId());
            List<MenuDto> menuList =  menuRoleService.getMenuByRoles(roles);
           model.addAttribute("menu",menuList);
        }
        return "pages/frame/menu";
    }


    @RequestMapping(value = "/controlframe")
    public String controlFrame() {
        return "pages/frame/controlframe";
    }




    @RequestMapping(value = "/top")
    public String showTop(Model model) {
        DSysUser user = dianthusSecurityMetadataSource.currentUser();
        model.addAttribute("userName", user.getName());
        return "pages/frame/top";
    }


}
