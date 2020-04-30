package com.taiji.dianthus.controller.sys;


import com.taiji.dianthus.common.BasicController;
import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysOrg;
import com.taiji.dianthus.service.sys.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SysOrgViewController
 * @Description 组织相关
 * @Author H.M
 * @Date 2020/1/7
 */
@RequestMapping(value = "/sys/org")
@Controller
public class SysOrgViewController extends BasicController {

    @Autowired
    private OrgService pushOrgService;

    /**
     * 查询列表
     * @param model
     * @param pageSize
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Model model,Integer pageSize,Integer currentPage) {
        pageSize = getPageSize(pageSize);
        currentPage = getCurrentPage(currentPage);
        BusinessMessage message =pushOrgService.list(currentPage,pageSize);
        model.addAttribute("data",message.getData());
        return "pages/sys/orgList";
    }

    /**
     * 新增页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Model model,String id) {
        DSysOrg pushOrg = new DSysOrg();
        if( null != id ) {
           pushOrg = pushOrgService.getOrgById(id);
        }
        model.addAttribute("org",pushOrg);
        return "pages/sys/orgAdd";
     }
}
