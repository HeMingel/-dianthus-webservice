package com.taiji.dianthus.controller.sys;


import com.taiji.dianthus.common.BasicController;
import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysOrg;
import com.taiji.dianthus.domain.sys.DSysRole;
import com.taiji.dianthus.domain.sys.DSysUser;
import com.taiji.dianthus.dto.UserDto;
import com.taiji.dianthus.service.sys.OrgService;
import com.taiji.dianthus.service.sys.RoleService;
import com.taiji.dianthus.service.sys.RoleUserService;
import com.taiji.dianthus.service.sys.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName sysUserViewController
 * @Description
 * @Author H.M
 * @Date 2020/1/8
 */
@RequestMapping(value = "/sys/user")
@Controller
public class  SysUserViewController extends BasicController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private OrgService orgService;

    /**
     * 列表查询
     *
     * @param model
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Model model, Integer currentPage, Integer pageSize,String name) {
        currentPage = getCurrentPage(currentPage);
        pageSize = getPageSize(pageSize);
        BusinessMessage message = userService.list(currentPage, pageSize,name);
        model.addAttribute("data", message.getData());
        StringBuffer url = new StringBuffer("/sys/user/list?pageSize=10");
        if (!StringUtils.isEmpty(name)) {
            url.append("&name="+name);
        }
        model.addAttribute("url",url);
        return "pages/sys/userList";
    }

    /**
     * 跳转新增、编辑页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "add")
    public String add(Model model,String id) {
        UserDto userDto = new UserDto();
        if (null != id) {
           DSysUser user = userService.getById(id);
            String roleId = roleUserService.getByUser(user.getId());
            BeanUtils.copyProperties(user,userDto);
            userDto.setRoleId(roleId);
        }
        //查询所有角色
        List<DSysRole> roleList = roleService.listAll();
        //查询所有组织
         List<DSysOrg>  orgList = orgService.listAll();
        model.addAttribute("roleList", roleList);
        model.addAttribute("orgList",  orgList);
        model.addAttribute("user", userDto);
        return "pages/sys/userAdd";
    }
}
