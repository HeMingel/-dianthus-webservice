package com.taiji.dianthus.dao.sys;


import com.taiji.dianthus.domain.sys.DSysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserRepository
 * @Description 用户相关
 * @Author H.M
 * @Date 2019/9/9
 */
@Repository
public interface UserRepository  extends JpaRepository<DSysUser, String>, JpaSpecificationExecutor<DSysUser> {

    /**
     * 获取用户名
     * @param userCode
     * @return
     */
    @Query("select u.name from DSysUser u where u.id=:userCode and u.enableFlag=1")
    String getNameByCode(@Param("userCode") String userCode);

    /**
     * 逻辑删除
     * @param id
     */
    @Modifying
    @Transactional
    @Query("update DSysUser p set p.enableFlag = 0  where p.id=:id")
    void updateFlag(@Param("id") String id);

    /**
     * 根据登录名查找
     * @param loginName
     * @return
     */
    @Query("select u from DSysUser u where u.userLoginName =:loginName and u.enableFlag=1")
    DSysUser findByLoginName(@Param("loginName") String loginName);

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    @Query("select u from DSysUser u where u.id =:id and u.enableFlag=1")
    DSysUser getById(@Param("id") String id);

    /**
     * 查询
     * @param orgId
     * @return
     */
    @Query("select count(*) from DSysUser u where u.orgId =:orgId and u.enableFlag=1")
    Integer countByOrgId(@Param("orgId") String orgId);

    @Query("select u from DSysUser u where u.orgId =:orgId and u.enableFlag=1")
    List<DSysUser> getUserCodeByOrgId(@Param("orgId") String orgId);

    @Query("select u.id from DSysUser u where u.orgId =:orgId and u.enableFlag=1")
    List<String> getUserCodesByOrgId(@Param("orgId") String orgId);


    @Query("select u from DSysUser u where u.id in (:ids) and u.enableFlag=1")
    List<DSysUser> listByIds(@Param("ids") String [] ids );

    @Query("select t from DSysUser  t where  t.pushId =:pushId  and t.enableFlag = 1")
    DSysUser getUserByPushId(String pushId);

}
