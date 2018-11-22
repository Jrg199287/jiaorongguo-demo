package com.primeton.jiaorongguo.demo.service;

import com.github.pagehelper.PageInfo;
import com.primeton.jiaorongguo.demo.common.Response;
import com.primeton.jiaorongguo.demo.exception.CommonException;
import com.primeton.jiaorongguo.demo.model.User;

import javax.servlet.http.HttpSession;

/**
 * 用户管理接口
 *
 * @Description: 用户管理接口
 * @Author: 作者姓名
 * @CreateDate: 2018/10/24 10:29
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/24 10:29
 * @Version: 1.0
 *
 * 身无彩凤双飞翼，心有灵犀一点通。
 */

public interface IUserService {

    /**
     * 用户登录
     *
     * @param userName 用户名称
     * @param passWord 用户密码
     * @return
     * @throws CommonException
     */
    Response<User> login(HttpSession session, String userName, String passWord) throws CommonException;

    /**
     * 创建用户
     *
     * @param user 用户对象
     * @return
     * @throws CommonException
     */
    Response<User> createUser(User user) throws CommonException;


    /**
     * 删除用户
     *
     * @param id 用户id
     * @return
     * @throws CommonException
     */
    Response<User> removeUser(Integer id);

    /**
     * 更新用户信息
     *
     * @param user    用户对象
     * @return
     * @throws CommonException
     */
    Response<User> modifyUser(User user) throws CommonException;

    /**
     * 根据条件分页查询
     *
     * @param pageIndex 起始页数
     * @param pageSize  每页数量
     * @param userName  用户名称
     * @param groupId  组织id
     * @return
     * @throws CommonException
     */
    Response<PageInfo> queryUsers(Integer pageIndex, Integer pageSize,String userName,Integer groupId) throws CommonException;

    /**
     * 主键查询
     *
     * @param id 用户id
     * @return
     * @throws CommonException
     */
    Response<User> getUser(Integer id);
}
