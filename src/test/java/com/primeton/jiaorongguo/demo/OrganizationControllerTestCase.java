package com.primeton.jiaorongguo.demo;

import com.github.pagehelper.PageInfo;
import com.primeton.jiaorongguo.demo.common.Response;
import com.primeton.jiaorongguo.demo.controller.OrganizationController;
import com.primeton.jiaorongguo.demo.exception.CommonException;
import com.primeton.jiaorongguo.demo.model.Organization;
import com.primeton.jiaorongguo.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 组的管理测试
 *
 * @Description: 分组测试
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
public class OrganizationControllerTestCase {

    /**
     * 创建分组测试
     *
     * @throws CommonException
     */

    @Autowired
    private OrganizationController OrganizationController;

    private Organization organization;

    @Test
    public void testOrganization() throws Exception {
        organization = this.initOrganization();
        //删除分组测试
        this.removeOrganization();
        //创建分组测试
        this.createOrganization();
        //更新分组测试
        this.modifyOrganization();
        //查询分组测试
        this.queryOrganization();
        //查询每组人员信息
        this.queryOrganizationMembers();
        //主键查询
        this.getOrganization();
        //删除分组测试
        this.removeOrganization();
    }

    /**
     * 创建分组测试
     *
     * @throws CommonException
     */

    public void createOrganization() throws CommonException {
        Response<Organization> response = OrganizationController.createOrganization(organization);
        Assert.assertEquals("添加分组成功", response.getMsg());
    }

    /**
     * 更新分组测试
     *.
     * @throws CommonException
     */
    public void modifyOrganization() throws CommonException {
        organization.setGroupName("测试部cg");
        Response<Organization> response = OrganizationController.modifyOrganization(organization);
        Assert.assertEquals("更新成功", response.getMsg());
//        Assert.assertNotNull(response.getMessage());
    }

    /**
     * 查询每组人员信息
     *
     * @throws CommonException
     */
    public void queryOrganization() throws CommonException {
        Response<List<Organization>> response = OrganizationController.queryOrganizations(0);
        Assert.assertEquals("查询成功", response.getMsg());
    }

    /**
     * 条件查询分组测试
     *
     * @throws CommonException
     */
    public void queryOrganizationMembers() throws CommonException {
        Response<PageInfo> response = OrganizationController.queryOrganizationMembers(1,3,700047);
        Assert.assertEquals("查询成功", response.getMsg());
//        Assert.assertEquals(7, response.getMessage().size());
    }

    /**
     * 主键查询分组测试
     *
     * @throws CommonException
     */
    public void getOrganization() throws CommonException {
        Response<Organization> response = OrganizationController.getOrganization(organization.getGroupId());
        Assert.assertEquals("主键查询成功", response.getMsg());
    }

    /**
     * 删除分组测试
     *
     * @throws CommonException
     */
    public void removeOrganization() throws CommonException {
        Response<Organization> response = OrganizationController.removeOrganization(organization.getGroupId());
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * 创建组
     *
     * @return
     */
    public Organization initOrganization() {
        Organization organization = new Organization();
        organization.setGroupId(700047);
        organization.setGroupName("测试组133");
        return organization;
    }
}
