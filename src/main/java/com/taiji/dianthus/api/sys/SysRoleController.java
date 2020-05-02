package com.taiji.dianthus.api.sys;


import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysRole;
import com.taiji.dianthus.domain.sys.DSysRoleMenu;
import com.taiji.dianthus.service.sys.MenuRoleService;
import com.taiji.dianthus.service.sys.RoleService;
import com.taiji.dianthus.service.sys.RoleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SysRoleController
 * @Description
 * @Author H.M
 * @Date 2019/12/24
 */
@Api(value = "角色api")
@RestController
@RequestMapping(value = "/api/role")
public class SysRoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private MenuRoleService menuRoleService;

    @PostMapping(value = "/save")
    @ResponseBody
    @ApiOperation(value = "保存/修改角色方法", notes = "保存/修改角色接口")
    @ApiImplicitParam(name = "form", value = "保存/修改角色", required = true, dataType = "DSysRole")
    public BusinessMessage saveRole(@RequestBody DSysRole role) {
        return roleService.saveRole(role);
    }


    @GetMapping(value = "/saveMenuRole")
    @ResponseBody
    @ApiOperation(value = "保存角色的菜单权限", notes = "保存角色的菜单权限接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="menuIds",value="菜单主键,多个以,号隔开",dataType="string", required = true,paramType = "query"),
            @ApiImplicitParam(name="roleId",value="角色ID",dataType="string", required = true,paramType = "query") })
    public BusinessMessage saveRole(HttpServletRequest request, String menuIds, String roleId) {
        BusinessMessage message = new BusinessMessage();
        try {
            String[] menuIdsArr = menuIds.split(",");
            if (menuIdsArr.length == 0) {
                message.setMsg("选择的权限为空");
                return message;
            }
            List<String> menuIdList = Arrays.asList(menuIdsArr);
            List<String> preMenuIds = menuRoleService.getMenuIdsByRoleId(roleId);
            //求差集
            List<String> differenceSet = menuIdList.stream().filter(t -> !preMenuIds.contains(t)).collect(Collectors.toList());
            List<String> differenceSet2 = preMenuIds.stream().filter(t -> !menuIdList.contains(t)).collect(Collectors.toList());
            //此处指的是将与l2重复的删除
            differenceSet.removeAll(differenceSet2);
            //此处指加上l2
            differenceSet.addAll(differenceSet2);
            for (int i = 0; i < differenceSet.size(); i++) {
                String did= differenceSet.get(i);
               //取差集中在旧ID中进行删除
                if (null != preMenuIds && preMenuIds.size() >0 && !StringUtils.isEmpty(did) &&preMenuIds.contains(did)) {
                     DSysRoleMenu menuRole = new DSysRoleMenu();
                    menuRole.setRoleId(roleId);
                    menuRole.setMenuId(did);
                    menuRoleService.delete(menuRole);
                    //取差集中在新ID中进行存储
                }else if ( !StringUtils.isEmpty(did) && menuIdList.contains(did)){
                    DSysRoleMenu menuRole = new  DSysRoleMenu();
                    menuRole.setRoleId(roleId);
                    menuRole.setMenuId(did);
                    menuRoleService.save(menuRole);
                }
            }
            message.setSuccess(true);
            message.setMsg("保存成功");

        } catch (Exception e ) {
            e.printStackTrace();
            message.setMsg("保存出错");

        }
        return message;
    }


    @PostMapping(value = "/delete")
    @ResponseBody
    @ApiOperation(value = "删除角色", notes = "删除角色接口")
    @ApiImplicitParam(name="id",value="主键",dataType="string", required = true,paramType = "query")
    public BusinessMessage deleteRole(@Param("id") String id) {
        BusinessMessage message = new BusinessMessage();
        Integer result = roleUserService.countByRole(id);
        try {
            if (result > 0) {
                message.setMsg("角色下存在用户，不能删除");
            } else {
                roleService.deleteRole(id);
                menuRoleService.deleteByRole(id);
                message.setSuccess(true);
                message.setMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg("删除失败");
        }

        return message;
    }
}
