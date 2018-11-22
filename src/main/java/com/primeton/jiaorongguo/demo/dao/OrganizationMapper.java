package com.primeton.jiaorongguo.demo.dao;

import com.primeton.jiaorongguo.demo.model.Organization;
import com.primeton.jiaorongguo.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrganizationMapper {
    int delete(Integer groupId);

    int insert(Organization record);

    Organization get(Integer groupId);

    Organization query(String groupName);

    int update(Organization record);

    List<Organization> queryOrganizations();

    List<Organization> queryOrganizationChildrens(Integer parentId);

    List<User>  queryOrganizationMembers(Integer groupId);

}