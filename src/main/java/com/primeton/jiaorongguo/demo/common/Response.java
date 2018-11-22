package com.primeton.jiaorongguo.demo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 封装请求响应
 *
 * @Description: 请求响应
 * @Author: 作者姓名
 * @CreateDate: 2018/10/24 10:33
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/24 10:33
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
@ApiModel(description = "响应信息")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Response<T> implements Serializable {

    @ApiModelProperty("响应状态")
    private int status;
    @ApiModelProperty("响应消息")
    private String msg;
    @ApiModelProperty("响应结果")
    private T message;

    private Response(int status) {
        this.status = status;
    }

    private Response(int status, T message) {
        this.status = status;
        this.message = message;
    }

    private Response(int status, String msg, T message) {
        this.message = message;
        this.msg = msg;
        this.status = status;
    }

    public Response(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getMessage() {
        return message;
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public static <T> Response<T> createBySuccess() {
        return new Response<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> Response<T> createBySuccessMessage(String msg) {
        return new Response<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> Response<T> createBySuccess(T message) {
        return new Response<T>(ResponseCode.SUCCESS.getCode(), message);
    }

    public static <T> Response<T> createBySuccess(String msg, T message) {
        return new Response<T>(ResponseCode.SUCCESS.getCode(), msg, message);
    }


    public static <T> Response<T> createByError() {
        return new Response<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDec());
    }


    public static <T> Response<T> createByErrorMessage(String errorMessage) {
        return new Response<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> Response<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new Response<T>(errorCode, errorMessage);
    }
}
