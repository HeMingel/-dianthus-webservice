package com.taiji.dianthus.service.sys.impl;

import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.dao.sys.MenuRepository;
import com.taiji.dianthus.dao.sys.MenuRoleRepository;
import com.taiji.dianthus.domain.sys.DSysMenu;
import com.taiji.dianthus.domain.sys.DSysRoleMenu;
import com.taiji.dianthus.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName MenuServiceImpl
 * @Description
 * @Author H.M
 * @Date 2019/10/21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository repository;

    @Autowired
    private MenuRoleRepository menuRoleRepository;

    @Override
    public List<DSysMenu> findAll() {
        List<DSysMenu> all = repository.findAll();
        //去除逻辑删除部分
        List<DSysMenu> menus = all.stream().filter(t -> t.getEnableFlag() == 1).collect(Collectors.toList());
        return menus;
    }

    /**
     * 根据ID集合查询菜单集合
     *
     * @param ids
     * @return
     */
    @Override
    public List<DSysMenu> listByIds(List<String> ids) {
        return repository.getMenuByIds(ids);
    }

    /**
     * 获取所有主菜单
     *
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public BusinessMessage listRootMenus(Integer pageSize, Integer currentPage) {
        BusinessMessage message = new BusinessMessage();
        try {
            //排序
            List<Sort.Order> orders = new ArrayList<>();
            orders.add(new Sort.Order(Sort.Direction.ASC, "menuOrder"));
            orders.add(new Sort.Order(Sort.Direction.DESC, "createTime"));
            //分页参数
            Pageable pageable = PageRequest.of(currentPage, pageSize,  Sort.by(orders));
            Page<DSysMenu> menuPage;
            Specification<DSysMenu> spec = (Root<DSysMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                List<Predicate> pl = new ArrayList<Predicate>();
                //只查询主菜单
                pl.add(cb.isNull(root.<String>get("parentId")));
                // 查询出未删除的
                pl.add(cb.equal(root.<Integer>get("enableFlag"), 1));
                return cb.and(pl.toArray(new Predicate[0]));
            };
            menuPage = repository.findAll(spec, pageable);
            message.setData(menuPage);
            message.setSuccess(true);
            message.setMsg("查询菜单列表成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg("查询菜单列表失败");
        }

        return message;
    }

    /**
     * 保存菜单信息
     *
     * @param menu
     * @return
     */
    @Override
    public BusinessMessage saveMenu(DSysMenu menu) {
        BusinessMessage message = new BusinessMessage();
        try {
            repository.save(menu);
            message.setMsg("保存菜单信息成功");
            message.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg("保存菜单信息失败");

        }
        return message;
    }

    /**
     * 根据ID 获取菜单
     *
     * @param menuId
     * @return
     */
    @Override
    public DSysMenu getByMenuId(String menuId) {
        if (StringUtils.isEmpty(menuId)) {
            return null;
        }
        return repository.getOne(menuId);
    }




    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    @Override
    public BusinessMessage deleteMenu(String menuId) {
        BusinessMessage message = new BusinessMessage();
        try {
            List<DSysRoleMenu> roleList = menuRoleRepository.listByMenu(menuId);
            if (null != roleList && roleList.size()>0) {
                message.setMsg("菜单和现有角色已经关联，不能删除");
                return message;
            }
            repository.updateFlag(menuId);
            message.setMsg("删除成功");
            message.setSuccess(true);
        }catch (Exception e) {
            e.printStackTrace();
            message.setMsg("删除错误");
        }


        return message;
    }

    /**
     * 获取子菜单
     *
     * @param menuId
     * @return
     */
    @Override
    public List<DSysMenu> listChildMenu(String menuId) {
        return  repository.getChildMenus(menuId);
    }
}
