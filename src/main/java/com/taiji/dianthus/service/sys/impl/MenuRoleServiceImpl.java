package com.taiji.dianthus.service.sys.impl;


import com.taiji.dianthus.dao.sys.MenuRepository;
import com.taiji.dianthus.dao.sys.MenuRoleRepository;
import com.taiji.dianthus.dao.sys.RoleRepository;
import com.taiji.dianthus.domain.sys.DSysMenu;
import com.taiji.dianthus.domain.sys.DSysRole;
import com.taiji.dianthus.domain.sys.DSysRoleMenu;
import com.taiji.dianthus.dto.MenuDto;
import com.taiji.dianthus.service.sys.MenuRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName MenuRoleServiceImpl
 * @Description
 * @Author H.M
 * @Date 2019/10/21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuRoleServiceImpl implements MenuRoleService {

    @Autowired
    private MenuRoleRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public Set<DSysRole> getRoleByMenuId(String menuId) {
        List<DSysRoleMenu> roleList = repository.listByMenu(menuId);
        Set<DSysRole> set = new HashSet<>();
        for (DSysRoleMenu menuRole : roleList) {
            DSysRole role = roleRepository.getOne(menuRole.getRoleId());
            set.add(role);
        }
        return set;
    }

    /**
     * 获取菜单
     *
     * @param roles
     * @return
     */
    @Override
    public List<MenuDto> getMenuByRoles(Set<DSysRole> roles) {
        List<String> roleIds = new ArrayList<>();
        //获取role的id 集合
        Iterator it = roles.iterator();
        while (it.hasNext()) {
            DSysRole role = (DSysRole) it.next();
            roleIds.add(role.getId());
        }
        List<MenuDto> menuDtoList = new ArrayList<>();
        List<DSysMenu> allMenu = menuRepository.getAllMenuByRole(roleIds);
        if (allMenu.size() > 0) {
            //获取主菜单
            List<DSysMenu> rootMenu = allMenu.stream().filter(t -> null == t.getParentId()).collect(Collectors.toList());
            //获取子菜单
            List<DSysMenu> childMenu = allMenu.stream().filter(t -> null != t.getParentId()).collect(Collectors.toList());
            for (DSysMenu menu : rootMenu) {
                MenuDto menuDto = new MenuDto();
                BeanUtils.copyProperties(menu, menuDto);
                if (menu.getHasSubmenu() == 1) {
                    List<DSysMenu> allChild = childMenu.stream().filter(t -> t.getParentId().equals(menu.getId())).collect(Collectors.toList());
                    menuDto.setChild(allChild);
                }
                menuDtoList.add(menuDto);
            }

        }
//        List<Menu> rootMenu = menuRepository.getRootMenuByRole(roleIds);
//        List<MenuDto> menuDtoList = new ArrayList<>();
//        for (Menu menu :rootMenu) {
//            MenuDto menuDto = new MenuDto();
//            BeanUtils.copyProperties(menu, menuDto);
//            if (menu.getHasSubmenu() == 1 ) {
//                List<Menu> childMenus = menuRepository.getChildMenus(menu.getId());
//                menuDto.setChild(childMenus);
//            }
//            menuDtoList.add(menuDto);
//        }
        return menuDtoList;
    }

    /**
     * 根据ID 获取菜单信息
     *
     * @param roleId
     * @return
     */
    @Override
    public List<DSysMenu> getMenuByRoleId(String roleId) {
        if (null == roleId || "".equals(roleId)) {
            return null;
        }
        List<String> menuIds = getMenuIdsByRoleId(roleId);
        if (menuIds.size() == 0) {
            return null;
        }
        return menuRepository.getMenuByIds(menuIds);

    }

    /**
     * 保存方法
     *
     * @param menuRole
     */
    @Override
    public void save(DSysRoleMenu menuRole) {
        repository.saveMenuRole(menuRole.getMenuId(), menuRole.getRoleId());
    }

    /**
     * 获取菜单ID
     *
     * @param roleId
     * @return
     */
    @Override
    public List<String> getMenuIdsByRoleId(String roleId) {
        return repository.listByRoleId(roleId);
    }

    /**
     * 删除方法
     *
     * @param menuRole
     */
    @Override
    public void delete(DSysRoleMenu menuRole) {
        repository.delete(menuRole.getMenuId(), menuRole.getRoleId());
    }


    /**
     * 根据角色删除
     *
     * @param roleId
     */
    @Override
    public void deleteByRole(String roleId) {
        repository.deleteByRole(roleId);
    }
}
