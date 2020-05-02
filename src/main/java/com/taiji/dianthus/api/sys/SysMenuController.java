package com.taiji.dianthus.api.sys;

import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysMenu;
import com.taiji.dianthus.service.sys.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @ClassName SysMenuController
 * @Description 系统菜单权限api
 * @Author H.M
 * @Date 2020/1/4
 */
@Api(value = "系统菜单api")
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
    @ApiOperation(value = "保存/修改菜单", notes = "菜单保存/修改接口")
    @ApiImplicitParam(name = "form", value = "保存/修改菜单", required = true, dataType = "DSysMenu")
    public BusinessMessage saveMenu(@Valid  @RequestBody DSysMenu menu) {
        return menuService.saveMenu(menu);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    @ApiOperation(value = "删除菜单", notes = "删除菜单接口")
    @ApiImplicitParam(name="id",value="主键",dataType="string", paramType = "query")
    public BusinessMessage deleteMenu(String id) {
        return menuService.deleteMenu(id);
    }
}
