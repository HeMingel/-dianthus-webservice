package com.taiji.dianthus.service.sys.impl;


import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.dao.sys.MenuRoleRepository;
import com.taiji.dianthus.dao.sys.RoleRepository;
import com.taiji.dianthus.domain.sys.DSysRole;
import com.taiji.dianthus.domain.sys.DSysRoleMenu;
import com.taiji.dianthus.service.sys.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName RoleServiceImpl
 * @Description
 * @Author H.M
 * @Date 2019/12/24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MenuRoleRepository menuRoleRepository;

    /**
     * 获取所有角色
     *
     * @return
     */
    @Override
    public BusinessMessage listRoles(Integer pageSize, Integer currentPage) {
        BusinessMessage message = new BusinessMessage();
        try {
            //排序
            Sort sort = new Sort(Sort.Direction.DESC, "createTime");
            //分页参数
            Pageable pageable = PageRequest.of(currentPage, pageSize, sort);
            Page<DSysRole> rolesPage;
            Specification<DSysRole> spec = (Root<DSysRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                List<Predicate> pl = new ArrayList<Predicate>();
                // 查询出未删除的
                pl.add(cb.equal(root.<Integer>get("enableFlag"), 1));
                return cb.and(pl.toArray(new Predicate[0]));
            };
            rolesPage = roleRepository.findAll(spec, pageable);
            message.setData(rolesPage);
            message.setSuccess(true);
            message.setMsg("查询角色列表成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg("查询角色列表失败");
        }

        return message;
    }

    /**
     * 根据ID详情
     *
     * @param id
     * @return
     */
    @Override
    public DSysRole getRoleById(String id) {
        return roleRepository.getOne(id);
    }

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    @Override
    public BusinessMessage saveRole(DSysRole role) {
        BusinessMessage message = new BusinessMessage();
        try {
            roleRepository.save(role);
            message.setSuccess(true);
            message.setMsg("保存角色成功！");
        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg("保存角色失败！");
        }
        return message;
    }

    /**
     * @param menuIds    选择的菜单Id的字符串，以逗号分隔的
     * @param preMenuIds 旧的被选中的ids
     * @param roleId     void  选择的角色Id
     * @Description: 保存角色菜单的分配
     */
    @Override
    public BusinessMessage saveOrUpdateRoleMenu(String[] menuIds, String preMenuIds, String roleId) {
        BusinessMessage message = new BusinessMessage();
        try {
            String joinUsersIDs = StringUtils.join(menuIds, ",");
            if (joinUsersIDs == null) {
                joinUsersIDs = "";
            }
            if (preMenuIds == null) {
                preMenuIds = "";
            }
            if (menuIds == null) {
                menuIds = new String[0];
            }
            //所有被选中的ID数组转list
            List<String> menuIdList = Arrays.asList(menuIds);
            //旧的被选中的数组转list
            List<String> preUserIdList = Arrays.asList(preMenuIds.split(","));
            preUserIdList.get(0);
            if (roleId != null && roleId.length() > 0) {
                // 求差集
                List<String> differenceSet = menuIdList.stream().filter(t -> !preUserIdList.contains(t)).collect(Collectors.toList());
                List<String> differenceSet2 = preUserIdList.stream().filter(t -> !menuIdList.contains(t)).collect(Collectors.toList());
                //此处指的是将与l2重复的删除
                differenceSet.removeAll(differenceSet2);
                //此处指加上l2
                differenceSet.addAll(differenceSet2);

                for (int i = 0; i < differenceSet.size(); i++) {
                    String ssd = differenceSet.get(i);
                    //取差集中在旧ID中进行删除
                    if (preMenuIds.contains(ssd) && preMenuIds != null && !"".equals(preMenuIds) && !"".equals(ssd) && ssd != null) {
                        DSysRoleMenu mr = new DSysRoleMenu();
                        mr.setRoleId(roleId);
                        mr.setMenuId(differenceSet.get(i));
                        this.menuRoleRepository.delete(mr);
                        //取差集中在新ID中进行存储
                    } else if (joinUsersIDs.contains(differenceSet.get(i)) && differenceSet.get(i) != null && !"".equals(differenceSet.get(i)) && joinUsersIDs != null && !"".equals(joinUsersIDs)) {
                        DSysRoleMenu mr = new DSysRoleMenu();
                        mr.setRoleId(roleId);
                        mr.setMenuId(differenceSet.get(i));
                        this.menuRoleRepository.saveAndFlush(mr);
                    }
                }

            }
            message.setMsg("更新角色菜单成功");
            message.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg("更新角色菜单失败");
        }

        return message;
    }

    /**
     * 删除方法
     *
     * @param id
     */
    @Override
    public void deleteRole(String id) {
        roleRepository.updateFlag(id);
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<DSysRole> listAll() {
        return roleRepository.listAll();
    }


}
