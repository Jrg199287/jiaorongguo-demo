package com.primeton.jiaorongguo.demo.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.jiaorongguo.demo.common.Const;
import com.primeton.jiaorongguo.demo.common.Response;
import com.primeton.jiaorongguo.demo.common.ResponseCode;
import com.primeton.jiaorongguo.demo.dao.OrganizationMapper;
import com.primeton.jiaorongguo.demo.exception.CommonException;
import com.primeton.jiaorongguo.demo.model.Organization;
import com.primeton.jiaorongguo.demo.model.User;
import com.primeton.jiaorongguo.demo.service.IOrganizationService;
import com.primeton.jiaorongguo.demo.util.EmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 组织机构实现类
 *
 * @Description: 组织机构实现层
 * @Author: 作者姓名
 * @CreateDate: 2018/10/29 14:43
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/29 14:43
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
@Service
@Slf4j
@Transactional(rollbackFor = CommonException.class)
public class OrganizationServiceImpl implements IOrganizationService {
    @Autowired
    private OrganizationMapper OrganizationMapper;

    /**
     * 创建组织机构
     *
     * @param organization 组织机构对象
     * @return
     * @throws CommonException
     */
    @Override
    @Transactional
    public Response<Organization> createOrganization(Organization organization) throws CommonException {
        if (EmptyUtils.isEmpty(organization.getGroupName())) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(), ResponseCode.PARAM_NOT_NULL.getDec());
        }
        if (EmptyUtils.isEmpty(organization.getParentId())) {
            organization.setParentId(700038);
        }
        Response response = this.Exist(organization.getGroupName(), Const.GROUPNAME);
        if (!response.isSuccess()) {
            return response;
        }
        int count = OrganizationMapper.insert(organization);
        if (count == 0) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.GROUADD_REEOR.getCode(), ResponseCode.GROUADD_REEOR.getDec());
        }
        return Response.createBySuccess("添加分组成功", organization);
    }

    /**
     * 删除组织机构
     *
     * @param groupId 组织机构id
     * @return
     * @throws CommonException
     */
    @Override
    @Transactional
    public Response<Organization> removeOrganization(Integer groupId) {
        if (EmptyUtils.isEmpty(groupId)) {
            return Response.createByErrorCodeMessage(ResponseCode.PARAM_NOT_NULL.getCode(), ResponseCode.PARAM_NOT_NULL.getDec());
        }
        Organization organization = OrganizationMapper.get(groupId);
        if (organization == null) {
            return Response.createByErrorCodeMessage(ResponseCode.GROUP_NOT_EXIST.getCode(), ResponseCode.GROUP_NOT_EXIST.getDec());
        }
        //判断该组织下还有没有成员
        List<User> users = OrganizationMapper.queryOrganizationMembers(groupId);
        if(users.size()>0){
            return Response.createByErrorCodeMessage(ResponseCode.ORGMEMBER_ERROR.getCode(), ResponseCode.ORGMEMBER_ERROR.getDec());
        }
        //判断该组织下还有没有子机构
        List<Organization> orgs = OrganizationMapper.queryOrganizationChildrens(groupId);
        if(orgs.size()>0){
            return Response.createByErrorCodeMessage(ResponseCode.ORGCHILD_ERROR.getCode(), ResponseCode.ORGCHILD_ERROR.getDec());
        }
        int result = OrganizationMapper.delete(groupId);
        if (result == 0) {
            return Response.createByErrorCodeMessage(ResponseCode.REMOVE_ERROR.getCode(), ResponseCode.REMOVE_ERROR.getDec());
        }
        return Response.createBySuccess("删除成功", organization);
    }

    /**
     * 更改组织机构
     *
     * @param organization 组织机构对象
     * @return
     * @throws CommonException
     */
    @Override
    @Transactional
    public Response<Organization> modifyOrganization(Organization organization) throws CommonException {
        if (EmptyUtils.isEmpty(organization.getGroupName())) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(), ResponseCode.PARAM_NOT_NULL.getDec());
        }
        Exist(organization.getGroupName(), Const.GROUPNAME);
        int result = OrganizationMapper.update(organization);
        if (result == 0) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.UPDATE_ERROR.getCode(), ResponseCode.UPDATE_ERROR.getDec());
        }
        Organization Organizations = OrganizationMapper.query(organization.getGroupName());
        return Response.createBySuccess("更新成功", Organizations);
    }


    /**
     * 查询该组人员详情
     *
     * @param pageIndex 起始页数
     * @param pageSize  每页数量
     * @param groupId   组织机构id
     * @return
     * @throws CommonException
     */
    @Override
    @Transactional(readOnly = true)
    public Response<PageInfo> queryOrganizationMembers(Integer pageIndex, Integer pageSize, Integer groupId) throws CommonException {
        if (EmptyUtils.isEmpty(groupId)) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(), ResponseCode.PARAM_NOT_NULL.getDec());
        }
        if (EmptyUtils.isEmpty(pageSize)) {
            pageSize = 10;
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<User> list = OrganizationMapper.queryOrganizationMembers(groupId);
        if (list == null) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SELECT_ERROR.getCode(), ResponseCode.SELECT_ERROR.getDec());
        }
        PageInfo pageInfo = new PageInfo<>(list, pageSize);
        return Response.createBySuccess("查询成功", pageInfo);
    }


    /**
     * 查询该机构下的所有子机构
     *
     * @param parentId 父节点id
     * @return
     * @throws CommonException
     */
    @Override
    public Response<List<Organization>> queryOrganizations(Integer parentId) throws CommonException {
        List<Organization> organization = null;
        if (EmptyUtils.isEmpty(parentId)) {
            organization = OrganizationMapper.queryOrganizations();
        } else {
            organization = OrganizationMapper.queryOrganizationChildrens(parentId);
        }
        if (organization == null) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SELECT_ERROR.getCode(), ResponseCode.SELECT_ERROR.getDec());
        }
        return Response.createBySuccess("查询成功", organization);
    }


    /**
     * 主键查询
     *
     * @param groupId 主键id
     * @return
     * @throws CommonException
     */
    @Override
    @Transactional(readOnly = true)
    public Response<Organization> getOrganization(Integer groupId) {
        if (EmptyUtils.isEmpty(groupId)) {
            return Response.createByErrorCodeMessage(ResponseCode.PARAM_NOT_NULL.getCode(), ResponseCode.PARAM_NOT_NULL.getDec());
        }
        Organization organization = OrganizationMapper.get(groupId);
        if (organization == null) {
            return Response.createByErrorCodeMessage(ResponseCode.SELECT_ERROR.getCode(), ResponseCode.SELECT_ERROR.getDec());
        }
        return Response.createBySuccess("主键查询成功", organization);
    }


    /**
     * 验正该机构是否存在
     *
     * @param str
     * @param type
     * @return
     * @throws CommonException
     */
    public Response<String> Exist(String str, String type) throws CommonException {
        if (Const.GROUPNAME.equals(type)) {
            //验正该机构是否存在
            Organization resultCount = OrganizationMapper.query(str);
            if (resultCount != null) {
                throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.GROUNAME_EXIST.getCode(), ResponseCode.GROUNAME_EXIST.getDec());
            }
        }
        return Response.createBySuccessMessage("校验成功");
    }
}
