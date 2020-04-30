package com.taiji.dianthus.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @ClassName SecurityUtil
 * @Description Utility class for Spring Security.
 * @Author H.M
 * @Date 2019/10/21
 */
public class SecurityUtil {

    private SecurityUtil() {

    }

    /**
     * Get the login of the current user.
     *
     * @return
     */
    public static Object getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            if (authentication.getPrincipal() instanceof DianthusUser) {
                DianthusUser springSecurityUser = (DianthusUser) authentication.getPrincipal();
                return springSecurityUser.getUser();
            } else if (authentication.getPrincipal() instanceof String) {
                return (String) authentication.getPrincipal();
            } else if (authentication instanceof AbstractAuthenticationToken) {
                return authentication.getName();
            }
        }

        return null;
    }


    /**
     * Get the login of the current user.
     *
     * @return
     */
    public static String getCurrentLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails springSecurityUser = null;
        String loginName = null;

        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                springSecurityUser = (UserDetails) authentication.getPrincipal();
                loginName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                loginName = (String) authentication.getPrincipal();
            } else if (authentication instanceof AbstractAuthenticationToken) {
                loginName = authentication.getName();
            }
        }

        return loginName;
    }

    /**
     * Get the login of the current user.
     *
     * @return
     */
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        final Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();

        if (authorities != null) {
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
                    return false;
                }
            }
        }

        return true;
    }


}