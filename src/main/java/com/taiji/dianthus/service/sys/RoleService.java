package com.taiji.dianthus.service.sys;


import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysRole;

import java.util.List;

/**
 * @ClassName RoleService
 * @Description 用户角色
 * @Author H.M
 * @Date 2019/12/24
 */
public interface RoleService {

    /**
     *  获取所有角色
     * @param pageSize
     * @param currentPage
     * @return
     */
    BusinessMessage listRoles(Integer pageSize, Integer currentPage);

    /**
     * 根据ID详情
     * @param id
     * @return
     */
    DSysRole getRoleById(String id);

    /**
     * 保存角色
     * @param role
     * @return
     */
    BusinessMessage saveRole(DSysRole  role);


    /**
     * 保存角色菜单的分配
     * @param menuIds
     * @param preMenuIds
     * @param roleId
     * @return
     */
    BusinessMessage saveOrUpdateRoleMenu(String[] menuIds, String preMenuIds, String roleId);

    /**
     * 删除方法
     * @param id
     */
    void deleteRole(String id);


    /**
     * 查询所有角色
     * @return
     */
    List<DSysRole > listAll();


}
