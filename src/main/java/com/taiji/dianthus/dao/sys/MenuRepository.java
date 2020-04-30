package com.taiji.dianthus.dao.sys;

import com.taiji.dianthus.domain.sys.DSysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName MenuRepository
 * @Description Menu 持久层
 * @Author H.M
 * @Date 2020/3/16
 */
@Repository
public interface MenuRepository extends JpaRepository<DSysMenu, String>, JpaSpecificationExecutor<DSysMenu> {
    /**
     * 逻辑删除
     * @param id
     */
    @Modifying
    @Query("update DSysMenu p set p.enableFlag = 0  where p.id=:id")
    void updateFlag(@Param("id") String id);



    /**
     * 获取主菜单
     *
     * @param roleIds
     * @return
     */
    @Query(value = "select distinct  m.* from d_sys_menu m right  join  d_sys_role_menu  mr on m.id = mr.menu_id " +
            "where mr.role_id in (:roleIds) and  m.parent_id is null and m.enable_flag = 1  order by m.menu_order ",
            countQuery = "select count(distinct m.*) from  d_sys_menu m right  join   d_sys_role_menu  mr on m.id = mr.menu_id " +
                    "where mr.role_id in (:roleIds) and  m.parent_id is null and m.enable_flag = 1  order by m.menu_order ",
            nativeQuery = true)
    List<DSysMenu> getRootMenuByRole(@Param("roleIds") List<String> roleIds);

    /**
     * 获取角色所有菜单权限
     * @param roleIds
     * @return
     */
    @Query(value = "select distinct  m.* from  d_sys_menu m right  join   d_sys_role_menu  mr on m.id = mr.menu_id " +
            "where mr.role_id in ( :roleIds )  and m.enable_flag = 1  order by m.menu_order ",
            countQuery = "select count(distinct m.*) from  d_sys_menu m right  join   d_sys_role_menu  mr on m.id = mr.menu_id " +
                    "where mr.role_id in ( :roleIds )  and m.enable_flag = 1  order by m.menu_order ",
            nativeQuery = true)
    List<DSysMenu> getAllMenuByRole(@Param("roleIds") List<String> roleIds);

    /**
     * 查询子菜单
     *
     * @param parentId
     * @return
     */
    @Query("select m from DSysMenu  m where m.parentId =:parentId and m.enableFlag = 1 order by m.menuOrder")
    List<DSysMenu> getChildMenus(@Param("parentId") String parentId);

    /**
     * 查询菜单集合
     * @param menuIds
     * @return
     */
    @Query("select m from DSysMenu m where m.id in (:menuIds) and m.enableFlag = 1 order by m.menuOrder")
    List<DSysMenu> getMenuByIds(@Param("menuIds") List<String> menuIds);

}
