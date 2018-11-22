package com.primeton.jiaorongguo.demo;

import com.github.pagehelper.PageInfo;
import com.primeton.jiaorongguo.demo.common.Response;
import com.primeton.jiaorongguo.demo.controller.UserController;
import com.primeton.jiaorongguo.demo.exception.CommonException;
import com.primeton.jiaorongguo.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 用户系统测试
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2018/11/5 12:46
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/11/5 12:46
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class UserControllerTestCase {

    protected MockMvc mockMvc;
    private MockHttpSession session;
    private User user;
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private UserController userController;

    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
        session = new MockHttpSession();
    }

    @Test
    public void testUser() throws Exception {
        user = this.initUser();
        //用户删除
        this.removeUser();
        //用户注册测试User
        this.createUser();
        //用户login测试
        this.login();
        //用户更新测试
        this.modifyUser();
        // 用户查询测试
        this.queryUser();
        //用户主键查询
        this.getUser();
        //用户删除
        this.removeUser();
    }

    /**
     * 用户注册测试
     *
     * @throws CommonException
     */
    public void createUser() throws CommonException {
        Response<User> response = userController.createUser(user);
        Assert.assertEquals("注册成功", response.getMsg());
    }

    /**
     * 用户login测试
     *
     * @throws CommonException
     */
    public void login() throws Exception {
        String requestBody = "{\"userName\":\"admin\", \"passWord\":\"admin\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/actions/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .session(new MockHttpSession())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 用户更新测试
     *
     * @throws CommonException
     */
    public void modifyUser() throws Exception {
        user = this.initUser();
        user.setUserName("test更新");
        Response<User> response = userController.modifyUser(user);
        Assert.assertEquals("更新成功", response.getMsg());
    }


    /**
     * 用户分页查询测试
     *
     * @throws CommonException
     */

    public void queryUser() throws CommonException {
        Response<PageInfo> response = userController.queryUsers(2, 3,null,null);
        Assert.assertEquals("查询成功", response.getMsg());
        Assert.assertNotNull(response.getMessage());
    }


    /**
     * 用户主键查询测试
     *
     * @throws CommonException
     */
    public void getUser() throws CommonException {
        Response<User> response = userController.getUser(user.getId());
        Assert.assertEquals("主键查询成功", response.getMsg());
    }


    /**
     * 用户删除测试
     *
     * @throws CommonException
     */
    public void removeUser() throws CommonException {
        Response<User> response = userController.removeUser(user.getId());
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * 创建用户信息
     *
     * @return
     */
    public User initUser() {
        User user = new User();
        user.setId(52);
        user.setGroupId(700041);
        user.setUserName("test最新");
        user.setPassWord("123456");
        session.setAttribute("currentUser", user);
        return user;
    }

}
