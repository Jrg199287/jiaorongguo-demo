package com.primeton.jiaorongguo.demo.exception;

import com.primeton.jiaorongguo.demo.common.Response;
import com.primeton.jiaorongguo.demo.common.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 统一异常处理
 *
 * @Description: 对统一异常进行处理
 * @Author: 作者姓名
 * @CreateDate: 2018/10/25 16:26
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/25 16:26
 * @Version: 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //处理自定义异常
    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity processException(CommonException ex, HttpServletRequest request, HttpServletResponse response) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity(new Response<>(ex.getErrCode(),ex.getMessage()),ex.getHttpStatus());
    }
    //处理所有异常
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleServerException(Exception exception) {
        log.error("系统异常：",exception.getMessage(),exception);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity(new Response<>(ResponseCode.SYSTEM_ERROR.getCode(), ResponseCode.SYSTEM_ERROR.getDec()),httpStatus);
    }
}
