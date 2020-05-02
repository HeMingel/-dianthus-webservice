package com.taiji.dianthus.common;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PageModel
 * @Description
 * @Author H.M
 * @Date 2019/11/18
 */
public class MingPageModel<T> {

    List<T> entityData;

    List<Map<String, Object>> mapList;

    Integer total;

    Integer totalPages;

    Integer currentPage;

    Integer pageSize;


    public MingPageModel() {
        super();
    }

    public MingPageModel(List<Map<String, Object>> list, Integer total, Integer pageSize, Integer currentPage) {
        this.total = total;
        this.pageSize = pageSize;
        this.mapList = list;
        this.currentPage = currentPage;
        setTotalPages(getTotalPages());
    }

    public List<T> getEntityData() {
        return entityData;
    }

    public void setEntityData(List<T> entityData) {
        this.entityData = entityData;
    }


    public List<Map<String, Object>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, Object>> mapList) {
        this.mapList = mapList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {

        return this.getPageSize() == 0 ? 1 : (int) Math.ceil((double) this.total / (double) this.getPageSize());
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
