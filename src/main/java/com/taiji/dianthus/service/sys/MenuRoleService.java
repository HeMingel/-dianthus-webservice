package com.taiji.dianthus.service.sys;

import com.taiji.dianthus.domain.sys.DSysMenu;
import com.taiji.dianthus.domain.sys.DSysRole;
import com.taiji.dianthus.domain.sys.DSysRoleMenu;
import com.taiji.dianthus.dto.MenuDto;

import java.util.List;
import java.util.Set;

/**
 * @InterfaceName MenuRoleService
 * @Description
 * @Author H.M
 * @Date 2019/10/21
 */
public interface MenuRoleService {

    Set<DSysRole> getRoleByMenuId(String menuId);


    /**
     * 获取菜单
     * @param roles
     * @return
     */
    List<MenuDto> getMenuByRoles(Set<DSysRole> roles);

    /**
     * 根据ID 获取菜单信息
     * @param roleId
     * @return
     */
    List<DSysMenu>  getMenuByRoleId(String roleId);

    /**
     * 保存方法
     * @param menuRole
     */
    void save(DSysRoleMenu menuRole);


    /**
     * 获取菜单ID
     * @param roleId
     * @return
     */
    List<String> getMenuIdsByRoleId(String roleId);


    /**
     * 删除方法
     * @param menuRole
     */
    void delete(DSysRoleMenu menuRole);

    /**
     * 根据角色删除
     * @param roleId
     */
    void deleteByRole(String roleId);
}
