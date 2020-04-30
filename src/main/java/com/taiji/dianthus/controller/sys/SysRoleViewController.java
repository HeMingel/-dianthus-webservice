package com.taiji.dianthus.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taiji.dianthus.common.BasicController;
import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysMenu;
import com.taiji.dianthus.domain.sys.DSysRole;
import com.taiji.dianthus.service.sys.MenuRoleService;
import com.taiji.dianthus.service.sys.MenuService;
import com.taiji.dianthus.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName SysRoleController
 * @Description 系统角色相关
 * @Author H.M
 * @Date 2019/12/24
 */
@RequestMapping(value = "/sys/role")
@Controller
public class SysRoleViewController extends BasicController {


    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuRoleService menuRoleService;

    @Autowired
    private MenuService menuService;

    /**
     * 获取系统角色列表
     *
     * @param model
     * @param pageSize
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/list")
    public String listRole(Model model, Integer pageSize, Integer currentPage) {
        pageSize = getPageSize(pageSize);
        currentPage = getCurrentPage(currentPage);
        BusinessMessage message = roleService.listRoles(pageSize, currentPage);
        model.addAttribute("data", message.getData());
        model.addAttribute("user", currentUser());
        return "pages/sys/roleList";
    }


    /**
     * 跳转新增/编辑页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/add")
    public String addRole(Model model, String id) {
        DSysRole role = new DSysRole();
        if (!StringUtils.isEmpty(id)) {
            role = roleService.getRoleById(id);
        }
        model.addAttribute("role", role);
        return "pages/sys/roleAdd";
    }


    /**
     * 跳转权限分配页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit")
    public String editRoleMenu(Model model, String roleId) {
        DSysRole role = roleService.getRoleById(roleId);
        //获取当前角色菜单
        List<DSysMenu> currentMenus = menuRoleService.getMenuByRoleId(roleId);
        //获取总菜单
        List<DSysMenu> allMenus = menuService.findAll();
        model.addAttribute("allMenus", allMenus);
        model.addAttribute("currentMenus", currentMenus);
        model.addAttribute("role", role);
        JSONArray jsonArray = new JSONArray();
        for (DSysMenu menu : allMenus) {
            JSONObject json = new JSONObject();
            json.put("id", menu.getId());
            json.put("pId", null == menu.getParentId() ? 0 : menu.getParentId());
            json.put("name", menu.getMenuName());
            if (null != currentMenus && currentMenus.size() > 0 && currentMenus.contains(menu)) {
                json.put("checked", true);
            }
            json.put("open", true);
            jsonArray.add(json);
        }
        model.addAttribute("menuList", jsonArray.toJSONString());
        return "/pages/sys/roleDetail";
    }

}
