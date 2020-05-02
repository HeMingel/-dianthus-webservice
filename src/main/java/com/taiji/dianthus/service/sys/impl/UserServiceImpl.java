package com.taiji.dianthus.service.sys.impl;


import com.taiji.dianthus.common.BusinessMessage;
import com.taiji.dianthus.dao.sys.UserRepository;
import com.taiji.dianthus.domain.sys.DSysMenu;
import com.taiji.dianthus.domain.sys.DSysRole;
import com.taiji.dianthus.domain.sys.DSysUser;
import com.taiji.dianthus.service.sys.RoleUserService;
import com.taiji.dianthus.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import static com.taiji.dianthus.common.Constant.DEFAULT_PASSWORD;


/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author H.M
 * @Date 2019/9/27
 */
@Service("DSysUser")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleUserService roleUserService;

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    /**
     * 获取用户名
     *
     * @param userCode
     * @return
     */
    @Override
    public String getNameByCode(String userCode) {
        return repository.getById(userCode).getName();
    }

    /**
     * @param loginName
     * @return
     */
    @Override
    public DSysUser findByLoginName(String loginName) {
        return repository.findByLoginName(loginName);
    }

    /**
     * @param name
     * @param useLoginName
     * @param userPassword
     * @param userMail
     * @param userPhone
     * @param orgId
     * @param lastLoginTime
     * @return
     */
    @Override
    public BusinessMessage add(String name, String useLoginName, String userPassword, String userMail, String userPhone, String orgId, String lastLoginTime) {
        return add(null, name, useLoginName, userPassword,userMail, userPhone, orgId,lastLoginTime,null);
    }

    /**
     *
     * @param name
     * @param useLoginName
     * @param userPassword
     * @param userMail
     * @param orgId
     * @param lastLoginTime
     * @return
     */
    @Override
    public BusinessMessage add( String id ,String name, String useLoginName, String userPassword,
                               String userMail, String userPhone, String orgId, String lastLoginTime,
                                String roleId) {
        BusinessMessage message = new BusinessMessage();
        try {
            DSysUser dSysUser = new DSysUser();
            dSysUser.setName(name);
            if (null != id) {
                dSysUser = repository.getById(id);
            }
            dSysUser.setUserLoginName(useLoginName);
            if (StringUtils.isEmpty(userPassword)) {
                dSysUser.setUserPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
            }else {
                dSysUser.setUserPassword(userPassword);
            }
            dSysUser.setUserMail(userMail);
            dSysUser.setUserPhone(userPhone);
            dSysUser.setOrgId(orgId);
            dSysUser.setLastLoginTime(lastLoginTime);
            dSysUser = repository.save(dSysUser);
            //生成角色
//            if(!StringUtils.isEmpty(roleId)) {
//                roleUserService.save(roleId, dSysUser.getId());
//            }
            message.setData(dSysUser);
            message.setMsg("新建/用户成功");
            message.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg("新建/用户失败");
            log.info("新建/用户失败，");
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
    @Transactional(rollbackFor = Exception.class)
    public BusinessMessage delete(String id) {
        BusinessMessage message = new BusinessMessage();
        try {
            repository.updateFlag(id);
            message.setSuccess(true);
            message.setMsg("删除用户成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg("删除用户失败");
            log.info("删除用户失败");
        }
        return message;
    }

    /**
     * 根据用户唯一码获取菜单
     *
     * @param userCode
     * @return
     */
    @Override
    public List<DSysMenu> listMenuByUser(String userCode) {
        Set<DSysRole> roleSet = roleUserService.listByUser(userCode);
        return null;
    }

    /**
     * 获取列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public BusinessMessage list(Integer currentPage, Integer pageSize) {
        return list(currentPage,pageSize,null);
    }

    @Override
    public BusinessMessage list(Integer currentPage, Integer pageSize, String userName) {
        BusinessMessage message  = new BusinessMessage();
        try  {
            Sort sort = new Sort(Sort.Direction.DESC, "createTime");
            //分页参数
            Pageable pageable = PageRequest.of(currentPage, pageSize, sort);
            Page<DSysUser> userPage;
            Specification<DSysUser> spec = (Root<DSysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->{
                List<Predicate> pl = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(userName)) {
                    pl.add(cb.like(root.<String>get("name"),userName+"%"));
                }
                //查询未删除的
                pl.add(cb.equal(root.<Integer>get("enableFlag"),1));
                return cb.and(pl.toArray(new Predicate[0]));
            };
            userPage = repository.findAll(spec,pageable);
            message.setMsg("查询用户列表成功");
            message.setData(userPage);
            message.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg("查询用户列表失败");
        }

        return message;
    }

    /**
     * 根据ID 获取
     *
     * @param id
     * @return
     */
    @Override
    public DSysUser getById(String id) {
        return repository.getById(id);
    }

    @Override
    public List<DSysUser> getUserCodeByOrgId(String orgId){
        return repository.getUserCodeByOrgId(orgId);
    }


    @Override
    public List<String> getUserCodesByOrgId(String orgId){
        return repository.getUserCodesByOrgId(orgId);
    }

    @Override
    public DSysUser getByPushId(String pushId) {
        return repository.getUserByPushId(pushId);
    }
}
