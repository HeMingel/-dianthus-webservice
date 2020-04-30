package com.taiji.dianthus.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName SysLog
 * @Description 日志实体
 * @Author H.M
 * @Date 2019/9/27
 * <p></p>
 * );
 */
@Entity
@Table(name = "d_sys_log")
@Proxy(lazy = false)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class SysLog {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户32位码
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * 操作 1 登陆 2 save 3 update 4 delete 5 select 6..
     */
    @Column(name = "operation")
    private Integer operation;

    /**
     * ip 地址
     */
    @Column(name = "ip_address")
    private String ipAddress;


    /**
     * 操作内容
     */
    @Column(name = "operation_desc")
    private String operationDesc;

    /**
     * 操作结果 0 失败 1 成功
     */
    @Column(name = "operation_result")
    private Integer  operationResult;

    /**
     * 创建时间戳
     */
    @Column(name = "create_time",insertable =false,updatable = false)
    private String createTime;

    @Column(name = "enable_flag",insertable =false,updatable = false)
    private Date enableFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }

    public Integer getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(Integer operationResult) {
        this.operationResult = operationResult;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Date getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Date enableFlag) {
        this.enableFlag = enableFlag;
    }
}
