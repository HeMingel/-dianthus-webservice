package com.taiji.dianthus.api.sys;


import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysOrg;
import com.taiji.dianthus.service.sys.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysOrgController
 * @Description
 * @Author H.M
 * @Date 2020/1/7
 */
@RestController
@RequestMapping(value = "/api/org")
public class SysOrgController {

    @Autowired
    private OrgService pushOrgService;




    /**
     * 保存角色方法
     *
     * @param pushOrg
     * @return
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public BusinessMessage saveOrg(@RequestBody DSysOrg pushOrg) {
        return pushOrgService.save(pushOrg);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public BusinessMessage deleteOrg(@Param("id") String  id) {
        return pushOrgService.delete(id);
    }
}
