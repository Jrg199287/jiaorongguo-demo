package com.primeton.jiaorongguo.demo.controller;

import com.github.pagehelper.PageInfo;
import com.primeton.jiaorongguo.demo.common.Response;
import com.primeton.jiaorongguo.demo.exception.CommonException;
import com.primeton.jiaorongguo.demo.model.User;
import com.primeton.jiaorongguo.demo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户管理类
 *
 * @Description: 用户管理的增删改查入口
 * @Author: jiaorongguo
 * @CreateDate: 2018/10/31 10:03
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/31 10:03
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
@RestController
@Slf4j
@Api(value = "User", description = "用户管理")
@RequestMapping(value = "/api/users")
public class UserController {


    @Autowired
    private IUserService userService;

    /**
     * 用户注册
     *
     * @param user 用户对象
     * @return
     * @throws CommonException
     */
    @ApiOperation(value = "添加用户", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户对象", name = "user", dataType = "User", required = true),
    })
    @PostMapping
    public Response<User> createUser(@RequestBody User user) throws CommonException {
        return userService.createUser(user);
    }

    /**
     * 用户登录
     *
     * @param user 用户对象
     * @return
     * @throws CommonException
     */

    @ApiOperation(value = "用户登陆", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户对象", name = "user", dataType = "User", required = true),
    })
    @PostMapping(value = "/actions/login")
    public Response<User> login(HttpSession session, @RequestBody User user) throws CommonException {
        return userService.login(session, user.getUserName(), user.getPassWord());
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return
     */

    @ApiOperation(value = "删除用户", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id", name = "id", dataType = "Integer", required = true),
    })
    @DeleteMapping(value = "/{id}")
    public Response<User> removeUser(@PathVariable("id") Integer id) {
        return userService.removeUser(id);
    }


    /**
     * 更新用户信息
     *
     * @param user 用户对象
     * @return
     * @throws CommonException
     */
    @ApiOperation(value = "更新用户信息", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户对象", name = "user", dataType = "User", required = true),
    })
    @PutMapping
    public Response<User> modifyUser(@RequestBody User user) throws CommonException {
        return userService.modifyUser(user);
    }


    /**
     * 根据条件分页查询
     *
     * @param pageIndex 起始页数
     * @param pageSize  每页数量
     * @param userName  用户名称
     * @param groupId   组织id
     * @return
     * @throws CommonException
     */

    @ApiOperation(value = "分页查询", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始页数", name = "pageIndex", dataType = "int", required = true),
            @ApiImplicitParam(value = "每页数量", name = "pageSize", dataType = "int", required = true),
            @ApiImplicitParam(value = "用户名称", name = "userName", dataType = "String", required = true),
            @ApiImplicitParam(value = "组织id", name = "groupId", dataType = "int", required = true),
    })
    @GetMapping
    public Response<PageInfo> queryUsers(Integer pageIndex, Integer pageSize, String userName, Integer groupId) throws CommonException {
        return userService.queryUsers(pageIndex, pageSize, userName, groupId);
    }

    /**
     * 主键查询
     *
     * @param id 用户id
     * @return
     */
    @ApiOperation(value = "主键查询", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id", name = "id", dataType = "Integer", required = true),
    })
    @GetMapping("/{id}")
    public Response<User> getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

}
