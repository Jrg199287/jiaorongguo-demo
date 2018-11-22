package com.primeton.jiaorongguo.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "用户对象实体类")
public class User implements Serializable {

    private static final long serialVersionUID = 5408699143801033394L;
    @ApiModelProperty("用户ID")
    private Integer id;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户密码")
    private String passWord;
    @ApiModelProperty("用户分组id")
    private Integer groupId;
    @ApiModelProperty("分组名称")
    private String groupName;

    public User() {

    }

    public User(Integer id, String userName, String passWord, Integer groupId, String groupName) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public User(Integer id, String userName, Integer groupId, String groupName) {
        this.id = id;
        this.userName = userName;
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public User(Integer id, String userName, String passWord, Integer groupId) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}