package com.taiji.dianthus.api.sys;

import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysMenu;
import com.taiji.dianthus.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysMenuController
 * @Description 系统菜单权限api类
 * @Author H.M
 * @Date 2020/1/4
 */
@RestController
@RequestMapping(value = "/api/menu")
public class SysMenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 保存
     * @param menu
     * @return
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public BusinessMessage saveMenu(@RequestBody DSysMenu menu) {
        return menuService.saveMenu(menu);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public BusinessMessage deleteMenu(@Param("id") String id) {
        return menuService.deleteMenu(id);
    }
}
