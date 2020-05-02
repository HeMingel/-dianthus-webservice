package com.taiji.dianthus.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName SysDictionary
 * @Description sys_dictionary 表实体
 * @Author H.M
 * @Date 2020/3/17
 */

@Entity
@Table(name = "d_sys_dictionary")
@Proxy(lazy = false)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@ApiModel(description = "字典表")
public class SysDictionary {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    @ApiModelProperty(value = "主键", name = "id")
    private String id;

    @Column(name = "parent_id")
    @ApiModelProperty(value = "上级ID", name = "parentId")
    private String parentId;

    @Column(name = "dict_name")
    @ApiModelProperty(value = "字典名", name = "dictName")
    private String dictName;

    @Column(name = "dict_value")
    @ApiModelProperty(value = "字典值", name = "dict_value")
    private String dictValue;

    @Column(name = "dict_type")
    @ApiModelProperty(value = "字典类型", name = "dictType")
    private String dictType;

    @Column(name = "dict_desc")
    @ApiModelProperty(value = "字典说明", name = "dict_desc")
    private String dictDesc;

    @Column(name = "sort_number")
    @ApiModelProperty(value = "排序", name = "sort_number")
    private Integer sortNumber;

    /**
     * 创建时间戳
     */
    @Column(name = "create_time", insertable = false, updatable = false)
    private Date createTime;

    @Column(name = "enable_flag", insertable = false, updatable = false)
    private Integer enableFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Integer enableFlag) {
        this.enableFlag = enableFlag;
    }
}
