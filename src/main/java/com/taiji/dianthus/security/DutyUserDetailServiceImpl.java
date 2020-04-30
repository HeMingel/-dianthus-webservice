package com.taiji.dianthus.security;

import com.taiji.dianthus.domain.sys.DSysRole;
import com.taiji.dianthus.domain.sys.DSysUser;
import com.taiji.dianthus.service.sys.RoleUserService;
import com.taiji.dianthus.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName PmUserDetailServiceImpl
 * @Description
 * @Author H.M
 * @Date 2019/10/21
 */
public class DianthusUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleUserService roleUserService;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    /**
     *   // 登录验证
     * @param loginName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String loginName)
            throws UsernameNotFoundException {
        //连接数据库根据登陆？？用户名称获得用户信息
        DSysUser user = userService.findByLoginName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException(loginName);
        }else {
            //TODO - 这里应该添加 用户是否锁定 判断
        }

        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);

        // 是否可用
        boolean enables = true;
        // 是否过期
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        // 封装成spring security的user
        DianthusUser  userdetail = new DianthusUser(user,user.getUserLoginName(), user.getUserPassword(),
                enables, accountNonExpired, credentialsNonExpired,
                accountNonLocked, grantedAuths);
        return userdetail;
    }

    /**
     *  取得用户的权限
     * @param users
     * @return
     */
    private Set<GrantedAuthority> obtionGrantedAuthorities(DSysUser users) {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        // 获取用户角色
        Set<DSysRole> roles = roleUserService.listByUser(users.getId());
        if (null != roles && !roles.isEmpty()) {
            for (DSysRole role : roles) {
                authSet.add(new SimpleGrantedAuthority(role.getId()));
            }
        }

        return authSet;
    }
}
