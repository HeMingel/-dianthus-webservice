package com.taiji.dianthus.service.sys;


import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysOrg;

import java.util.List;

/**
 * @ClassName PushOrgService
 * @Description
 * @Author H.M
 * @Date 2020/1/7
 */
public interface OrgService {

    /**
     * 查询组织列表
     * @param currentPage
     * @param PageSize
     * @return
     */
    BusinessMessage list(Integer currentPage, Integer PageSize);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    DSysOrg getOrgById(String id);

    /**
     * 保存方法
     * @param pushOrg
     * @return
     */
    BusinessMessage save(DSysOrg pushOrg);

    /**
     * 删除
     * @param id
     * @return
     */
    BusinessMessage delete(String id);


    /**
     * 获取名称
     * @param id
     * @return
     */
     String getOrgNameById(String id);

    /**
     * 查询所有列表
     * @return
     */
    List<DSysOrg> listAll();

}
