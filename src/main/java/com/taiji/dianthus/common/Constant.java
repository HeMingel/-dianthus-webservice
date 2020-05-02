package com.taiji.dianthus.common;

/**
 * @ClassName Constant
 * @Description 通用常量类
 * @Author H.M
 * @Date 2019/9/26
 */
public final class Constant {

    /**
     * ip 判断
     */
    public static final String PM_UNKNOWN = "unknown";

    /**
     * 本机地址
     */
    public static final String PM_LOCALHOST = "127.0.0.1";

    /**
     * 分隔符
     */
    public static final String PM_SEPARATOR =",";


    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD  = "123456";

    /**
     * 操作日志 类型  登陆
     */
    public static final  Integer LOG_OPERATION_LOGIN = 1;
    /**
     * 操作日志 类型  save
     */
    public static final  Integer LOG_OPERATION_SAVE = 2;
    /**
     * 操作日志 类型  update
     */
    public static final  Integer LOG_OPERATION_UPDATE = 3;
    /**
     * 操作日志 类型  delete
     */
    public static final  Integer LOG_OPERATION_DEL = 4;
    /**
     * 操作日志 类型  select
     */
    public static final  Integer LOG_OPERATION_QUERY = 5;
    /**
     * 操作日志 类型  其他
     */
    public static final  Integer LOG_OPERATION_OTHER = 6;

    /**
     * 符号
     */
    public static final String TICK = String.valueOf((char)8730);
}
