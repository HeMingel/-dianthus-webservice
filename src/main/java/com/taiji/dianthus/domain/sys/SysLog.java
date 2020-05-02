package com.taiji.dianthus.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "系统日志表")
public class SysLog {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    @ApiModelProperty(value = "主键", name = "id")
    private String id;

    @Column(name = "user_name")
    @ApiModelProperty(value = "用户姓名", name = "userName")
    private String userName;

    @Column(name = "user_id")
    @ApiModelProperty(value = "用户唯一标识", name = "userId")
    private String userId;

    @Column(name = "operation")
    @ApiModelProperty(value = "操作 1 登陆 2 save 3 update 4 delete 5 select 6..", name = "operation")
    private Integer operation;

    @Column(name = "ip_address")
    @ApiModelProperty(value = "ip 地址", name = "ipAddress")
    private String ipAddress;

    @Column(name = "operation_desc")
    @ApiModelProperty(value = "操作内容", name = "operationDesc")
    private String operationDesc;

    @Column(name = "operation_result")
    @ApiModelProperty(value = "操作结果 0 失败 1 成功", name = "operationResult")
    private Integer operationResult;

    /**
     * 创建时间戳
     */
    @Column(name = "create_time", insertable = false, updatable = false)
    private String createTime;

    @Column(name = "enable_flag", insertable = false, updatable = false)
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
