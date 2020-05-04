package com.taiji.dianthus.controller;


import com.taiji.dianthus.domain.sys.DSysUser;
import com.taiji.dianthus.security.DianthusAuthenticationProvider;
import com.taiji.dianthus.security.DianthusSecurityMetadataSource;
import com.taiji.dianthus.security.DianthusUserDetailServiceImpl;
import com.taiji.dianthus.service.sys.RoleUserService;
import com.taiji.dianthus.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DianthusUserDetailServiceImpl dUserDetailService;

    private DianthusAuthenticationProvider dAuthenticationProvider = new  DianthusAuthenticationProvider();
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


    /**
     * http://localhost:7777/dianthus/ssoLogin?userCode=f07bf386da044ae48fe7bbd08478ceb6
     * @param model
     * @param userCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/ssoLogin")
    public String ssoLogin(Model model, String userCode, HttpServletRequest request) {
        System.out.println("用户唯一标识:" + userCode);
        DSysUser user = userService.getByPushId(userCode);

        if (null != user) {
//        String roleId =  roleUserService.getRoleIdByUserCode(user.getId());
//        if (null == roleId) {
//            return "pages/login_error_noRole";
//        }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUserLoginName(),user.getUserPassword());
            authentication.setDetails(new WebAuthenticationDetails((HttpServletRequest) request));
            //UserDetails userDetails =  userDetailsService.loadUserByUsername(user.getUserLoginName());
            this.dAuthenticationProvider.setPmUserDetailService( dUserDetailService);
            Authentication result = dAuthenticationProvider.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(result);
            model.addAttribute("userName",user.getName());
            System.out.println("认证："+SecurityContextHolder.getContext().getAuthentication());
        }

        return "redirect:/";
    }
}
