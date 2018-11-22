package com.primeton.jiaorongguo.demo.interceptor;

import com.primeton.jiaorongguo.demo.common.ResponseCode;
import com.primeton.jiaorongguo.demo.exception.CommonException;
import com.primeton.jiaorongguo.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义拦截器
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2018/10/28 21:11
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/28 21:11
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean flag = true;
        User user = (User) request.getSession().getAttribute("currentUser");
        if (null == user) {
            flag = false;
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR,ResponseCode.LOGIN_ERROR.getCode(), ResponseCode.LOGIN_ERROR.getDec());
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
