package com.taiji.dianthus.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName SysDictionary
 * @Description sys_dictionary 表实体类
 * @Author H.M
 * @Date 2020/3/17
 *
 */

@Entity
@Table(name = "d_sys_dictionary")
@Proxy(lazy = false)
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class SysDictionary {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id")
    protected String id;

    /**
     *上级id
     */
    @Column(name = "parent_id")
    protected String parentId;

    /**
     *字典名
     */
    @Column(name ="dict_name")
    protected String dictName;

    /**
     *字典值
     */
    @Column(name ="dict_value")
    protected String dictValue;

    /**
     *字典类型
     */
    @Column(name ="dict_type")
    protected String dictType;


    /**
     *字典说明
     */
    @Column(name ="dict_desc")
    protected String dictDesc;

    /**
     *排序
     */
    @Column(name ="sort_number")
    protected Integer sortNumber;




    /**
     *创建时间戳
     */
    @Column(name ="create_time",insertable =false,updatable = false)
    protected Date createTime;

    @Column(name="enable_flag",insertable =false,updatable = false)
    protected Integer enableFlag;


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
