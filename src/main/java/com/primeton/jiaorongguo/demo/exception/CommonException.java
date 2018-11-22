package com.primeton.jiaorongguo.demo.exception;


import org.springframework.http.HttpStatus;

/**
 * 异常封装类
 *
 * @Description: 自定义异常封装
 * @Author: 作者姓名
 * @CreateDate: 2018/10/31 20:47
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/31 20:47
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class CommonException extends Exception {

    private static final long serialVersionUID = -3876319857610564892L;

    /**
     * http状态码
     */
    private HttpStatus httpStatus ;

    private Integer errCode;

    private String message;

    private Object[] params;

    /**
     * 额外信息
     */
    private Object additional;

    /**
     * 无参构造方法.
     *
     * @deprecated 不推荐使用
     */
    public CommonException() {
        super();
    }

    /**
     * 构造方法.
     *
     * @param httpStatus http状态码
     * @param errCode 异常编号
     * @param message 用户自定义异常描述信息
     * @param params  异常描述时用到的格式化参数
     * @param cause   上层异常
     * @param additional 额外信息，前端用
     */
    public CommonException(HttpStatus httpStatus, Integer errCode, String message, Object[] params, Throwable cause, Object additional) {
        super(cause);
        this.httpStatus = httpStatus;
        this.errCode = errCode;
        this.message = message;
        this.params = params;
        this.additional = additional;
    }

    /**
     * 构造方法.
     *
     * @param httpStatus http状态码
     * @param errCode 异常编号
     * @param message 用户自定义异常描述信息
     * @param params  异常描述时用到的格式化参数
     */
    public CommonException(HttpStatus httpStatus, Integer errCode, String message, Object... params) {
        this(httpStatus, errCode, message, params, null, null);
    }

    /**
     * 构造方法.
     *
     * @param httpStatus http状态码
     * @param errCode 异常编号
     * @param cause   上层异常
     */
    public CommonException(HttpStatus httpStatus, Integer errCode, Throwable cause) {
        this(httpStatus, errCode, null, null, null, null);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


    public Integer getErrCode() {
        return errCode;
    }

    public Object[] getParams() {
        return params;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
