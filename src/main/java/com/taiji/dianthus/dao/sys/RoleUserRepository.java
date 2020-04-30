package com.taiji.dianthus.dao.sys;


import com.taiji.dianthus.domain.sys.DSysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @InterfaceName RoleRepository
 * @Description Role 持久层
 * @Author yc
 * @Date 2019/10/18
 */
@Repository
public interface RoleUserRepository extends JpaRepository<DSysUserRole, String>, JpaSpecificationExecutor<DSysUserRole> {

    /**
     * 根据用户查询权限
     * @param userCode
     * @return
     */
    @Query("select ru  from DSysUserRole  ru where ru.userCode =:userCode")
    List<DSysUserRole> listByUser(@Param("userCode") String userCode);

    /**
     * 根据ROLEid 查询结果数
     * @param roleId
     * @return
     */
    @Query("select count(*) from DSysUserRole  ru where ru.roleId = :roleId")
    Integer countByRole(@Param("roleId") String roleId);

    /**
     * 查询用户角色（用户-角色 1对1 的情况）
     * @param userCode
     * @return
     */
    @Query("select ru.roleId  from DSysUserRole  ru where ru.userCode =:userCode")
    String getRoleIdByUser(@Param("userCode") String userCode);

    /**
     * 更新用户权限
     * @param userCode
     * @param roleId
     */
    @Modifying
    @Query("update DSysUserRole  ru set  ru.roleId = :roleId where ru.userCode =:userCode")
    void updateRole(@Param("userCode") String userCode, @Param("roleId") String roleId);


    /**
     * 保存方法
     * @param userCode
     * @param roleId
     */
    @Modifying
    @Query(value = "insert into d_sys_user_role(user_id,role_id) values(:userCode,:roleId) ",
            nativeQuery=true)
    void  save(@Param("userCode") String userCode, @Param("roleId") String roleId);

    @Query("select ru.roleId from DSysUserRole ru where ru.userCode = :userCode")
    String getRoleIdByUserCode(@Param("userCode") String userCode);

    @Modifying
    @Transactional
    @Query("delete from DSysUserRole ru where ru.userCode = :userId")
    public void deleteByUserId(@Param("userId") String userId);

}


