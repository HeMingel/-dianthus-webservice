package com.taiji.dianthus.dao.sys;

import com.taiji.dianthus.domain.sys.DSysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName RoleRepository
 * @Description Role 持久层
 * @Author yc
 * @Date 2019/10/18
 */
@Repository
public interface RoleRepository extends JpaRepository<DSysRole, String>, JpaSpecificationExecutor<DSysRole> {
    /**
     * 逻辑删除
     * @param id
     */
    @Modifying
    @Query("update DSysRole p set p.enableFlag = 0  where p.id=:id")
    void updateFlag(@Param("id") String id);

    /**
     * 查询所有角色
     * @return
     */
    @Query("select t from DSysRole t where t.enableFlag = 1")
    List<DSysRole> listAll();




}
