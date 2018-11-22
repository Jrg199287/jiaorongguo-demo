package com.primeton.jiaorongguo.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "组织对象实体类")
public class Organization implements Serializable {
    private static final long serialVersionUID = 3856523950597317110L;
    @ApiModelProperty("组织ID")
    private Integer groupId;
    @ApiModelProperty("组名")
    private String groupName;
    @ApiModelProperty("用户ID")
    private Integer parentId;


    public Organization() {
        super();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", parentId=" + parentId +
                '}';
    }

    public Organization(String groupName, Integer groupId, Integer parentId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.parentId = parentId;
    }
}