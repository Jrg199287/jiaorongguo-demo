package com.primeton.jiaorongguo.demo.common;

/**
 * java类简单作用描述
 *
 * @Description: 响应码
 * @Author: 作者姓名
 * @CreateDate: 2018/10/24 10:43
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/24 10:43
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public enum ResponseCode {
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    SYSTEM_ERROR(500,"系统异常"),
    PARAM_NOT_NULL(2001, "参数不能为空"),
    USERNAME_EXIST(2002, "用户名已存在"),
    REGISTER_REEOR(2003, "注册失败"),
    USER_NOT_EXIST(2004, "用户不存在"),
    USER_PASS_ERROR(2005, "用户名或密码错误"),
    GROUNAME_EXIST(2006, "该组已存在"),
    GROUP_NOT_EXIST(2007, "该组织不存在"),
    GROUADD_REEOR(2008, "添加分组失败"),
    UPDATE_ERROR(2009, "更新失败"),
    SELECT_ERROR(2010, "查询失败"),
    ORGMEMBER_ERROR(2011, "该组织存在成员"),
    LOGIN_ERROR(2012, "用户未登录"),
    REMOVE_ERROR(2013, "删除失败"),
    ORGCHILD_ERROR(2014, "请删除子机构");


    private final int code;
    private final String dec;

    ResponseCode(int code, String dec) {
        this.code = code;
        this.dec = dec;
    }

    public int getCode() {
        return code;
    }

    public String getDec() {
        return dec;
    }
}
