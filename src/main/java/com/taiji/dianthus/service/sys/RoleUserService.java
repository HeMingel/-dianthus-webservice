package com.taiji.dianthus.service.sys;

import com.taiji.dianthus.domain.sys.DSysRole;

import java.util.Set;

/**
 * @InterfaceName RoleUserService
 * @Description
 * @Author H.M
 * @Date 2019/10/21
 */
public interface RoleUserService {

    /**
     * 获取用户权限
     * @param  userId
     * @return
     */
    Set<DSysRole> listByUser(String userId);

    /**
     * 根据roleId　查询结果集
     * @param roleId
     * @return
     */
    Integer countByRole(String roleId);

    /**
     * 获取用户权限
     * @param userId
     * @return
     */
   String getByUser(String userId);

    /**
     * 更改用户权限
     *
     * @param userId
     * @param roleId
     */
   void update(String userId, String roleId);


    /**
     * 保存权限
     * @param roleId
     * @param userCode
     */
    void save(String roleId, String userCode);

    String getRoleIdByUserCode(String id);
}
