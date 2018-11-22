package com.primeton.jiaorongguo.demo.controller;

import com.github.pagehelper.PageInfo;
import com.primeton.jiaorongguo.demo.common.Response;
import com.primeton.jiaorongguo.demo.exception.CommonException;
import com.primeton.jiaorongguo.demo.model.Organization;
import com.primeton.jiaorongguo.demo.model.User;
import com.primeton.jiaorongguo.demo.service.IOrganizationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织机构管理类
 *
 * @Description: 组织机构增删改查入口
 * @Author: jiaorongguo
 * @CreateDate: 2018/10/31 13:04
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/31 13:04
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */

@Slf4j
@Api(value = "Organization", description = "组织机构")
@RequestMapping(value = "/api/orgs")
@RestController
public class OrganizationController {
    @Autowired
    private IOrganizationService OrganizationService;

    /**
     * 创建组织机构
     *
     * @param organization 组织机构对象
     * @return
     * @throws CommonException
     */
    @PostMapping
    @ApiOperation(value = "创建分组", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "组织机构信息", name = "organization", dataType = "Organization", required = true),
    })
    public Response<Organization> createOrganization(@RequestBody Organization organization) throws CommonException {
        return OrganizationService.createOrganization(organization);
    }

    /**
     * 更改组织机构
     *
     * @param organization 组织机构对象
     * @return
     * @throws CommonException
     */
    @PutMapping
    @ApiOperation(value = "更改组织机构", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "组织机构信息", name = "organization", dataType = "Organization", required = true),
    })
    public Response<Organization> modifyOrganization(@RequestBody Organization organization) throws CommonException {
        return OrganizationService.modifyOrganization(organization);
    }


    /**
     * 删除组织机构
     *
     * @param groupId 组织id
     * @return
     * @throws CommonException
     */
    @DeleteMapping("/{groupId}")
    @ApiOperation(value = "删除组织机构", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "主键id", name = "groupId", dataType = "Integer", required = true)
    })
    public Response<Organization> removeOrganization(@PathVariable("groupId") Integer groupId){
        return OrganizationService.removeOrganization(groupId);
    }


    /**
     * 主键查询
     *
     * @param groupId 组织id
     * @return
     * @throws CommonException
     */
    @ApiOperation(value = "主键查询", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "部门id", name = "groupId", dataType = "Integer", required = true),
    })
    @GetMapping("/{groupId}")
    public Response<Organization> getOrganization(@PathVariable("groupId") Integer groupId){
        return OrganizationService.getOrganization(groupId);
    }


    /**
     * 查询该组人员详情
     *
     * @param pageIndex 起始页数
     * @param pageSize  每页数量
     * @param groupId 组织机构id
     * @return
     * @throws CommonException
     */
    @GetMapping
    @ApiOperation(value = "查询该组人员详情", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始页数", name = "pageIndex", dataType = "int", required = true),
            @ApiImplicitParam(value = "每页数量", name = "pageSize", dataType = "int", required = true),
            @ApiImplicitParam(value = "组织机构id", name = "groupId", dataType = "Integer", required = true)
    })
    public Response<PageInfo> queryOrganizationMembers(Integer pageIndex, Integer pageSize, Integer groupId) throws CommonException {
        return OrganizationService.queryOrganizationMembers(pageIndex, pageSize, groupId);
    }

    /**
     * 查询该机构所有子机构
     * @param parentId 父节点id
     * @return
     * @throws CommonException
     */

    @ApiOperation(value = "查询所有机构", response = Response.class)
    @GetMapping(value = "/actions/query")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "上级id", name = "parentId", dataType = "int", required = true),
    })
    public Response<List<Organization>> queryOrganizations(@RequestParam(value = "parentId") Integer parentId) throws CommonException {
        return OrganizationService.queryOrganizations(parentId);
    }
}
