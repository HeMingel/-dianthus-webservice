package com.taiji.dianthus.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName ShiftDto
 * @Description
 * @Author H.M
 * @Date 2020/4/15
 */
public class ShiftDto {

    private String id;

    /**
     * 班次名称
     */
    private String shiftName;

    /**
     * 班次说明
     */
    private String shiftDesc;

    /**
     * 班次代码
     */
    private String shiftCode;

    private String shiftColorCode1;
    private String shiftColorCode2;

    /**
     * 值班地点
     */
    private String shiftOffice;

    /**
     * 值班人数
     */
    private Integer peopleNumber;

    /**
     * 排序
     */
    private String shiftOrder;

    protected Date createTime;

    protected Integer enableFlag;


    private String dianthusKindId;

    public String getDianthusKindId() {
        return dianthusKindId;
    }

    public void setDianthusKindId(String dianthusKindId) {
        this.dianthusKindId = dianthusKindId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getShiftDesc() {
        return shiftDesc;
    }

    public void setShiftDesc(String shiftDesc) {
        this.shiftDesc = shiftDesc;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public String getShiftOrder() {
        return shiftOrder;
    }

    public void setShiftOrder(String shiftOrder) {
        this.shiftOrder = shiftOrder;
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

    public String getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(String shiftCode) {
        this.shiftCode = shiftCode;
    }

    public String getShiftOffice() {
        return shiftOffice;
    }

    public void setShiftOffice(String shiftOffice) {
        this.shiftOffice = shiftOffice;
    }

    public String getShiftColorCode1() {
        return shiftColorCode1;
    }

    public void setShiftColorCode1(String shiftColorCode1) {
        this.shiftColorCode1 = shiftColorCode1;
    }

    public String getShiftColorCode2() {
        return shiftColorCode2;
    }

    public void setShiftColorCode2(String shiftColorCode2) {
        this.shiftColorCode2 = shiftColorCode2;
    }
}

