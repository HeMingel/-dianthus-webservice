package com.taiji.dianthus.security;



import com.taiji.dianthus.domain.sys.DSysMenu;
import com.taiji.dianthus.domain.sys.DSysRole;
import com.taiji.dianthus.domain.sys.DSysUser;
import com.taiji.dianthus.service.sys.MenuRoleService;
import com.taiji.dianthus.service.sys.MenuService;
import com.taiji.dianthus.service.sys.UserService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.*;

/**
 * @ClassName PmSecurityMetadataSource
 * @Description 资源源数据定义，将所有的资源和权限对应关系建立起来，即定义某一资源可以被哪些角色访问
 * * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * @Author H.M
 * @Date 2019/10/21
 */

public class DianthusSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private MenuService menuService;

    private UserService userService;

    private Map<String, Collection<ConfigAttribute>> resourceMap = null;

    private MenuRoleService menuRoleService;




    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }


    /**
     * 加载所有资源与权限的关系
     * @param refresh
     */
    public void loadResourceDefine(boolean refresh) {
        if (resourceMap == null || resourceMap.isEmpty() || refresh) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<DSysMenu> menus = this.menuService.findAll();
            if (!menus.isEmpty()) {
                // 以权限名封装为Spring的security Object
                for (DSysMenu menu : menus) {
                    Set<DSysRole> list = menuRoleService.getRoleByMenuId(menu.getId());
                    if (list != null && list.size() > 0) {
                        for (DSysRole role : list) {
                            Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                            ConfigAttribute configAttribute = new SecurityConfig(role.getId());
                            configAttributes.add(configAttribute);
                            // 获得已有资源，添加
                            if (!resourceMap.isEmpty()) {
                                if (menu != null && !menu.getMenuUrl().isEmpty()) {
                                    Collection<ConfigAttribute> configAttribute_old = resourceMap.get("/" + menu.getMenuUrl());
                                    if ("/".equals(menu.getMenuUrl())) {
                                        configAttribute_old = resourceMap.get(menu.getMenuUrl());
                                    }
                                    if (configAttribute_old != null) {
                                        configAttributes.addAll(configAttribute_old);
                                    }
                                }
                            }
                            if (menu.getMenuUrl() != null && !menu.getMenuUrl().isEmpty()) {
                                if (menu.getMenuUrl().startsWith("/")) {
                                    resourceMap.put(menu.getMenuUrl(), configAttributes);
                                } else {
                                    resourceMap.put("/" + menu.getMenuUrl(), configAttributes);
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(resourceMap);


    }

    /**
     *  获得当前登录用户对象
     * @return
     */
    public DSysUser currentUser() {
        Object currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            return null;
        }
        if (currentUser instanceof DSysUser) {
            return (DSysUser) currentUser;
        } else if (currentUser instanceof String) {
            return userService.findByLoginName((String) currentUser);
        } else {
            return null;
        }
    }
}
