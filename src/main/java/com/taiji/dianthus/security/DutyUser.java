package com.taiji.dianthus.security;

import com.taiji.dianthus.domain.sys.DSysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @ClassName DianthusUse
 * @Description 登录成功后缓存起来的用户对象，扩展增加了UserDto，方便后续获取当前登录用户
 * @Author H.M
 * @Date 2020/3/16
 */
public class  DianthusUser extends User {

    private DSysUser dSysUser;

    public DianthusUser(DSysUser user, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.dSysUser = user;
    }

    public DianthusUser(DSysUser user, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.dSysUser = user;
    }

    public  DSysUser getUser() {
        return dSysUser;
    }
}
