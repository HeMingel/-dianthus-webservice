package com.taiji.dianthus.service.sys.impl;

import com.taiji.dianthus.dao.sys.RoleRepository;
import com.taiji.dianthus.dao.sys.RoleUserRepository;
import com.taiji.dianthus.domain.sys.DSysRole;
import com.taiji.dianthus.domain.sys.DSysUserRole;
import com.taiji.dianthus.service.sys.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName RoleUserServiceImpl
 * @Description
 * @Author H.M
 * @Date 2019/10/21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleUserServiceImpl implements RoleUserService {


    @Autowired
    private RoleUserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Set<DSysRole> listByUser(String userCode) {
       List<DSysUserRole> roleUserList = repository.listByUser(userCode);
       Set<DSysRole> roles = new HashSet<>();
       for (DSysUserRole roleUser :roleUserList) {
           DSysRole role = roleRepository.getOne(roleUser.getRoleId());
         roles.add(role);
       }
       return  roles;
    }

    /**
     * 查询用户角色
     * @return
     */
    @Override
    public String getByUser(String userCode) {
        return repository.getRoleIdByUser(userCode);
    }

    /**
     * 更改用户权限
     *
     * @param userCode
     */
    @Override
    public void update(String userCode,String roleId) {
        repository.updateRole(userCode,roleId);
    }

    /**
     * 根据roleId　查询结果集
     *
     * @param roleId
     * @return
     */
    @Override
    public Integer countByRole(String roleId) {
        return repository.countByRole(roleId);
    }


    /**
     * 保存方法
     * @param roleId
     * @param userCode
     */
    @Override
    public void save(String roleId,String userCode) {
        repository.save(userCode,roleId);
    }


    @Override
    public String getRoleIdByUserCode(String userCode) {
        return repository.getRoleIdByUserCode(userCode);
    }
}
