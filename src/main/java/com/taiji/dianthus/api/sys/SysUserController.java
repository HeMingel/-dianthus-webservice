package com.taiji.dianthus.api.sys;


import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.dto.UserDto;
import com.taiji.dianthus.service.sys.RoleUserService;
import com.taiji.dianthus.service.sys.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysUserController
 * @Description
 * @Author H.M
 * @Date 2020/1/8
 */
@Api(value = "用户API")
@RestController
@RequestMapping(value = "/api/user")
public class SysUserController {

    @Autowired
    public UserService userService;

    @Autowired
    public RoleUserService roleUserService;

    @PostMapping(value = "/save")
    @ResponseBody
    @ApiOperation(value = "保存/修改用户方法", notes = "保存/修改用户接口")
    @ApiImplicitParam(name = "form", value = "保存/修改用户", required = true, dataType = "UserDto")
    public BusinessMessage saveRole(@RequestBody UserDto user) {
        //此逻辑只针对角色 用户 1v1 关系
        if (!StringUtils.isEmpty(user.getId()) && !StringUtils.isEmpty(user.getRoleId())) {
            String roleId = roleUserService.getByUser(user.getId());
            if (StringUtils.isEmpty(roleId)) {
                roleUserService.save(user.getRoleId(), user.getId());
            } else {
                if (!roleId.equals(user.getRoleId())) {
                    roleUserService.update(user.getId(), user.getRoleId());
                }
            }
        }
        return userService.add( StringUtils.isEmpty(user.getId()) ? null :user.getId(),user.getName(), user.getUserLoginName(), user.getUserPassword(),
                user.getUserMail(), user.getUserPhone(), user.getOrgId(), null,user.getRoleId());
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    @ApiOperation(value = "删除用户", notes = "删除用户接口")
    @ApiImplicitParam(name="id",value="主键",dataType="string", required = true,paramType = "query")
    public BusinessMessage delete(String id) {
        return userService.delete(id);
    }
}
