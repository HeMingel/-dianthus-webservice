package com.taiji.dianthus.dao.sys;


import com.taiji.dianthus.domain.sys.DSysRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName MenuRoleRepository
 * @Description MenuRole 持久层
 * @Author H.M
 * @Date 2020/3/16
 */
@Repository
public interface MenuRoleRepository extends JpaRepository<DSysRoleMenu, String>, JpaSpecificationExecutor<DSysRoleMenu> {


    /**
     * 根据菜单查询role
     * @param menuId
     * @return
     */
    @Query("select mr from DSysRoleMenu  mr where mr.menuId =:menuId ")
    List<DSysRoleMenu> listByMenu(@Param("menuId") String menuId);

    /**
     * 根据角色ID查询
     * @param roleId
     * @return
     */
    @Query("select mr.menuId from DSysRoleMenu  mr where mr.roleId =:roleId")
    List<String> listByRoleId(@Param("roleId") String roleId);

    /**
     * 删除
     * @param menuId
     * @param roleId
     */
    @Modifying
    @Query("delete from DSysRoleMenu mr  where mr.menuId =:menuId and mr.roleId=:roleId ")
    void delete(@Param("menuId") String menuId, @Param("roleId") String roleId);

    /**
     * 保存方法
     * @param menuId
     * @param roleId
     */
    @Modifying
    @Query(value = "insert into d_sys_role_menu(menu_id,role_id) values(:menuId,:roleId) ",
        nativeQuery=true)
    void saveMenuRole(@Param("menuId") String menuId, @Param("roleId") String roleId);

    /**
     * 根据菜单删除
     * @param roleId
     */
    @Modifying
    @Query("delete from DSysRoleMenu mr  where  mr.roleId=:roleId")
    void deleteByRole(@Param("roleId") String roleId);
}
