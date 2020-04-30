package com.taiji.dianthus.service.sys.impl;

import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.dao.sys.OrgRepository;
import com.taiji.dianthus.dao.sys.UserRepository;
import com.taiji.dianthus.domain.sys.DSysOrg;
import com.taiji.dianthus.service.sys.OrgService;
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
import java.util.List;

/**
 * @ClassName PushOrgServiceImpl
 * @Description
 * @Author H.M
 * @Date 2020/1/7
 */
@Service("pushOrg")
@Transactional(rollbackFor = Exception.class)
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgRepository orgRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 查询组织列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public BusinessMessage list(Integer currentPage, Integer pageSize) {
        BusinessMessage message = new BusinessMessage();
        try {
            //排序
            List<Sort.Order> orders = new ArrayList<>();
            orders.add(new Sort.Order(Sort.Direction.ASC, "orgOrder"));
            orders.add(new Sort.Order(Sort.Direction.DESC, "createTime"));
            //分页参数
            Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(orders));
            Page<DSysOrg> orgPage;
            Specification<DSysOrg> spec = (Root<DSysOrg> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                List<Predicate> pl = new ArrayList<Predicate>();
                // 查询出未删除的
                pl.add(cb.equal(root.<Integer>get("enableFlag"), 1));
                return cb.and(pl.toArray(new Predicate[0]));
            };
            orgPage = orgRepository.findAll(spec, pageable);
            message.setData(orgPage);
            message.setSuccess(true);
            message.setMsg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg("查询失败");

        }
        return message;
    }

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    @Override
    public DSysOrg getOrgById(String id) {
        return orgRepository.getById(id);
    }

    @Override
    public String getOrgNameById(String id) {
        DSysOrg pushOrg = orgRepository.getById(id);
        return pushOrg.getOrgName();
    }

    /**
     * 查询所有列表
     *
     * @return
     */
    @Override
    public List<DSysOrg> listAll() {
        return orgRepository.listAll();
    }

    /**
     * 保存方法
     *
     * @param pushOrg
     * @return
     */
    @Override
    public BusinessMessage save(DSysOrg pushOrg) {
        BusinessMessage message = new BusinessMessage();
        try {
            orgRepository.save(pushOrg);
            message.setMsg("保存成功");
            message.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg("保存失败");
        }
        return message;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public BusinessMessage delete(String id) {
        BusinessMessage message = new BusinessMessage();
        try {
            //查询是否有用户存在
            Integer count = userRepository.countByOrgId(id);
            if (count > 0) {
                message.setMsg("删除失败，该组织下还有用户");
                return message;
            }
            Integer child = orgRepository.countByParentId(id);
            if (child > 0) {
                message.setMsg("删除失败，该组织下还有子组织");
                return message;
            }
            orgRepository.updateFlag(id);
            message.setMsg("删除成功");
            message.setSuccess(true);

        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg("删除失败，系统错误");
        }

        return message;
    }
}
