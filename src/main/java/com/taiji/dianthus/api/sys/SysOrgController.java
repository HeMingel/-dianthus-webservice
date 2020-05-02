package com.taiji.dianthus.api.sys;


import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysOrg;
import com.taiji.dianthus.service.sys.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysOrgController
 * @Description 组织api
 * @Author H.M
 * @Date 2020/1/7
 */
@Api(value = "组织api")
@RestController
@RequestMapping(value = "/api/org")
public class SysOrgController {

    @Autowired
    private OrgService pushOrgService;

    @PostMapping(value = "/save")
    @ResponseBody
    @ApiOperation(value = "保存/修改组织方法", notes = "组织保存/修改接口")
    @ApiImplicitParam(name = "form", value = "保存/修改组织", required = true, dataType = "DSysOrg")
    public BusinessMessage saveOrg(DSysOrg pushOrg) {
        return pushOrgService.save(pushOrg);
    }

    /**
     * 删除组织
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    @ApiOperation(value = "删除组织", notes = "删除组织接口")
    @ApiImplicitParam(name="id",value="主键",dataType="string", required = true,paramType = "query")
    public BusinessMessage deleteOrg(String  id) {
        return pushOrgService.delete(id);
    }
}
