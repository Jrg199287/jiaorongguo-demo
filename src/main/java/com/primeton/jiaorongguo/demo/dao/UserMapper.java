package com.primeton.jiaorongguo.demo.dao;

import com.primeton.jiaorongguo.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    int delete(Integer id);

    int insert(User record);

    User get(Integer id);

    int update(User record);

    List<User> queryUsers(@Param("userName") String userName,@Param("groupId") Integer groupId);

    int counts(String userName);

    User checkUser(@Param("userName") String userName, @Param("passWord") String passWord);
}