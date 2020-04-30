package com.taiji.dianthus.controller;


import com.taiji.dianthus.domain.sys.DSysUser;
import com.taiji.dianthus.security.DianthusSecurityMetadataSource;
import com.taiji.dianthus.service.sys.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName LoginViewController
 * @Description 登录页面
 * @Author H.M
 * @Date 2019/10/22
 */
@Controller
@RequestMapping
public class LoginViewController {

    @Autowired
    private DianthusSecurityMetadataSource dianthusSecurityMetadataSource;
    @Autowired
    private RoleUserService roleUserService;

    /**
     * 页面拦截
     * @param model
     * @return
     */
    @RequestMapping(value = "/")
    public String showMain(Model model) {
        DSysUser user = dianthusSecurityMetadataSource.currentUser();
        String roleId =  roleUserService.getRoleIdByUserCode(user.getId());
        if (null == roleId) {
            return "pages/login_error_noRole";
        }
        model.addAttribute("userName",user.getName());
        return "main";
    }


}
