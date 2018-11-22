package com.primeton.jiaorongguo.demo.service;

import com.github.pagehelper.PageInfo;
import com.primeton.jiaorongguo.demo.common.Response;
import com.primeton.jiaorongguo.demo.exception.CommonException;
import com.primeton.jiaorongguo.demo.model.Organization;
import com.primeton.jiaorongguo.demo.model.User;

import java.util.List;

/**
 * 组织机构接口
 *
 * @Description: 组织机构接口
 * @Author: jiaorongguo
 * @CreateDate: 2018/10/29 14:43
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/29 14:43
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public interface IOrganizationService {

    /**
     * 创建组织机构
     *
     * @param organization 组织机构对象
     * @return
     * @throws CommonException
     */
    Response<Organization> createOrganization(Organization organization) throws CommonException;

    /**
     * 删除组织机构
     *
     * @param groupId 分组id
     * @return
     * @throws CommonException
     */
    Response<Organization> removeOrganization(Integer groupId);

    /**
     * 更改组织机构
     *
     * @param organization 组织机构对象
     * @return
     * @throws CommonException
     */
    Response<Organization> modifyOrganization(Organization organization) throws CommonException;


    /**
     * 主键查询
     *
     * @param groupId 分组id
     * @return
     * @throws CommonException
     */
    Response<Organization> getOrganization(Integer groupId);

    /**
     * 查询该组人员详情
     *
     * @param pageIndex 起始页数
     * @param pageSize  每页数量
     * @param groupId 组织机构id
     * @return
     * @throws CommonException
     */
    Response<PageInfo> queryOrganizationMembers(Integer pageIndex, Integer pageSize,Integer groupId) throws CommonException;


    /**
     * 查询该机构下的所有子机构
     * @param parentId 父节点id
     * @return
     * @throws CommonException
     */
    Response< List<Organization>> queryOrganizations(Integer parentId) throws CommonException;

}
