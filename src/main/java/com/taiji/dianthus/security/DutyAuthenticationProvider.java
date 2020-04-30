package com.taiji.dianthus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @ClassName PmAuthenticationProvider
 * @Description 实现自定义拦截
 * @Author H.M
 * @Date 2019/10/21
 */
public class DianthusAuthenticationProvider implements AuthenticationProvider {



    @Autowired
    private DianthusUserDetailServiceImpl pmUserDetailService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = pmUserDetailService.loadUserByUsername(authentication.getName());
        //密码验证
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                userDetails, authentication.getCredentials(),userDetails.getAuthorities());
        return result;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public DianthusUserDetailServiceImpl getPmUserDetailService() {
        return pmUserDetailService;
    }

    public void setPmUserDetailService(DianthusUserDetailServiceImpl pmUserDetailService) {
        this.pmUserDetailService = pmUserDetailService;
    }
}
