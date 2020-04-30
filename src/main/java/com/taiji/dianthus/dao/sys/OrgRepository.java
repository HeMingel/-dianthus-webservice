package com.taiji.dianthus.dao.sys;

import com.taiji.dianthus.domain.sys.DSysOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName OrgRepository
 * @Description
 * @Author H.M
 * @Date 2019/9/26
 */
public interface OrgRepository  extends JpaRepository<DSysOrg, String>, JpaSpecificationExecutor<DSysOrg> {

    /**
     * 逻辑删除
     * @param id
     */
    @Modifying
    @Transactional
    @Query("update DSysOrg p set p.enableFlag = 0  where p.id=:id")
    void updateFlag(@Param("id") String id);


    /**
     * 根据ID 获取组织
     * @param id
     * @return
     */
    @Query("select p from  DSysOrg p where  p.id=:id and p.enableFlag = 1 ")
    DSysOrg getById(@Param("id") String id);

    /**
     * 查询
     * @param orgId
     * @return
     */
    @Query("select count(u.id) from  DSysOrg u where u.parentId =:orgId and u.enableFlag=1")
    Integer countByParentId(@Param("orgId") String orgId);

    @Query("select p from  DSysOrg p where  p.enableFlag = 1 ")
    List<DSysOrg> listAll();

    /**
     * 根据组织代码查询
     * @param pushId
     * @return
     */
    @Query("select p from  DSysOrg p where p.pushId =:pushId and  p.enableFlag = 1")
    DSysOrg getByPushId(@Param("pushId") String pushId);

    /**
     * 查询下级节点
     * @param parentId
     * @return
     */
    @Query("select org from DSysOrg org where org.parentId =:parentId")
    List<DSysOrg> findByParentCode(@Param("parentId") String parentId);

}
