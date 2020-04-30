package com.taiji.dianthus.common;


import com.taiji.dianthus.domain.sys.DSysUser;
import com.taiji.dianthus.security.SecurityUtil;

import java.util.UUID;


/**
 * Controller 公共方法类
 * @author  H.M
 */
public class BasicController {
    /**
     *  获得当前登录用户对象
     * @return
     */
    public DSysUser currentUser() {
        Object currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            return null;
        }
        if (currentUser instanceof DSysUser) {
            return (DSysUser) currentUser;
        } else {
            return null;
        }
    }




    /**
     * 处理页码
     * @param pageSize
     * @return
     */
    public Integer getPageSize(Integer pageSize) {
        if (null == pageSize) {
            pageSize = 10;
        }
        return pageSize;
    }

    /**
     * 处理当前页
     * @param currentPage
     * @return
     */
    public Integer getCurrentPage(Integer currentPage) {
        //从前台传来的页数
        if (null == currentPage || currentPage == 0) {
            currentPage = 1;
        }
        return currentPage - 1;
    }
}
