package com.taiji.dianthus.service.sys;



import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.domain.sys.DSysMenu;
import com.taiji.dianthus.domain.sys.DSysUser;

import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author H.M
 * @Date 2019/9/9
 */
public interface UserService {
    /**
     * 获取用户名
     * @param userCode
     * @return
     */
    String getNameByCode(String userCode);

    /**
     *
     * @param loginName
     * @return
     */
    DSysUser findByLoginName(String loginName);


    /**
     *
     * @param name
     * @param useLoginName
     * @param userPassword
     * @param userMail
     * @param userPhone
     * @param orgId
     * @param lastLoginTime
     * @return
     */
    BusinessMessage add( String name, String useLoginName, String userPassword,
                                String userMail, String userPhone, String orgId, String lastLoginTime);

    BusinessMessage add( String id ,String name, String useLoginName, String userPassword,
                         String userMail, String userPhone, String orgId, String lastLoginTime , String roleId);
    /**
     * 删除
     * @param id
     * @return
     */
    BusinessMessage delete(String id);

    /**
     * 根据用户唯一码获取菜单
     * @param userId
     * @return
     */
    List<DSysMenu> listMenuByUser(String userId);

    /**
     * 获取列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    BusinessMessage list(Integer currentPage, Integer pageSize);

    BusinessMessage list(Integer currentPage, Integer pageSize,String name);

    /**
     * 根据ID 获取
     * @param id
     * @return
     */
    DSysUser getById(String id);

    /**
     * 根据orgid查询出user表所有的相关数据
     * @param orgId
     * @return
     */
    List<DSysUser > getUserCodeByOrgId(String orgId);

    List<String> getUserCodesByOrgId(String  orgId);

    DSysUser getByPushId(String pushId);

}


