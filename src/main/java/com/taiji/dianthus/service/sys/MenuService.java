package com.taiji.dianthus.service.sys;

import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysMenu;

import java.util.List;

/**
 * @InterfaceName MeunService
 * @Description
 * @Author H.M
 * @Date 2019/10/21
 */
public interface MenuService {

    /**
     * 查询全部
     * @return
     */
    List<DSysMenu> findAll();

    /**
     * 根据ID集合查询菜单集合
     * @param ids
     * @return
     */
    List<DSysMenu> listByIds(List<String> ids);

    /**
     * 获取所有主菜单
     * @return
     */
    BusinessMessage listRootMenus(Integer pageSize, Integer currentPage);

    /**
     * 保存菜单信息
     * @param menu
     * @return
     */
    BusinessMessage  saveMenu(DSysMenu menu);

    /**
     * 根据ID 获取菜单
     * @param menuId
     * @return
     */
    DSysMenu getByMenuId(String menuId);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    BusinessMessage deleteMenu(String menuId);

    /**
     * 获取子菜单
     * @param menuId
     * @return
     */
    List<DSysMenu> listChildMenu(String menuId);
}
