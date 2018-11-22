package com.primeton.jiaorongguo.demo.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.jiaorongguo.demo.common.Const;
import com.primeton.jiaorongguo.demo.common.Response;
import com.primeton.jiaorongguo.demo.common.ResponseCode;
import com.primeton.jiaorongguo.demo.dao.UserMapper;
import com.primeton.jiaorongguo.demo.exception.CommonException;
import com.primeton.jiaorongguo.demo.model.User;
import com.primeton.jiaorongguo.demo.service.IUserService;
import com.primeton.jiaorongguo.demo.util.EmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户接口实现
 *
 * @Description: 用户接口实现
 * @Author: 作者姓名
 * @CreateDate: 2018/10/24 11:06
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/24 11:06
 * @Version: 1.0
 */
@Service
@Slf4j
@Transactional(rollbackFor = CommonException.class)
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param userName 用户名称
     * @param passWord 用户密码
     * @return
     * @throws CommonException
     */
    @Override
    @Transactional
    public Response<User> login(HttpSession session, String userName, String passWord) throws CommonException {
        if (EmptyUtils.isEmpty(userName) || EmptyUtils.isEmpty(passWord)) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(), ResponseCode.PARAM_NOT_NULL.getDec());
        }
        //用户登陆检测
        User result = userMapper.checkUser(userName, passWord);
        if (result == null) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.USER_PASS_ERROR.getCode(), ResponseCode.USER_PASS_ERROR.getDec());
        }
        session.setAttribute("currentUser", result);
        return Response.createBySuccess("登陆成功", result);
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     * @throws CommonException
     */
    @Override
    @Transactional
    public Response<User> createUser(User user) throws CommonException {
        if (EmptyUtils.isEmpty(user.getUserName()) || EmptyUtils.isEmpty(user.getPassWord()) || EmptyUtils.isEmpty(user.getGroupId())) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(), ResponseCode.PARAM_NOT_NULL.getDec());
        }
        //校验用户名是否存在
        Response validResponse = this.Exist(user.getUserName(), user.getId(), Const.USERNAME);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        int count = userMapper.insert(user);
        if (count == 0) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.REGISTER_REEOR.getCode(), ResponseCode.REGISTER_REEOR.getDec());
        }
        return Response.createBySuccess("注册成功",user);
    }

    /**
     *
     * 删除用户
     *
     * @param id 用户id
     * @return
     */
    @Override
    @Transactional
    public Response<User> removeUser(Integer id){
        if (EmptyUtils.isEmpty(id)) {
            return Response.createByErrorCodeMessage(ResponseCode.PARAM_NOT_NULL.getCode(), ResponseCode.PARAM_NOT_NULL.getDec());
        }
        User user = userMapper.get(id);
        if (user == null) {
            return Response.createByErrorCodeMessage(ResponseCode.USER_NOT_EXIST.getCode(), ResponseCode.USER_NOT_EXIST.getDec());
        }
        int count = userMapper.delete(id);
        if (count == 0) {
            return Response.createByErrorCodeMessage(ResponseCode.REMOVE_ERROR.getCode(), ResponseCode.REMOVE_ERROR.getDec());
        }
        user.setPassWord(null);
        return Response.createBySuccess("删除成功", user);
    }

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     * @return
     * @throws CommonException
     */
    @Override
    @Transactional
    public Response<User> modifyUser(User user) throws CommonException {
        if (EmptyUtils.isEmpty(user.getId()) || EmptyUtils.isEmpty(user.getUserName())) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(), ResponseCode.PARAM_NOT_NULL.getDec());
        }
        int count = userMapper.update(user);
        if (count == 0) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.UPDATE_ERROR.getCode(), ResponseCode.UPDATE_ERROR.getDec());
        }
        User userNew = userMapper.get(user.getId());
        userNew.setPassWord(null);
        return Response.createBySuccess("更新成功", userNew);
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
    @Override
    @Transactional(readOnly = true)
    public Response<PageInfo> queryUsers(Integer pageIndex, Integer pageSize, String userName, Integer groupId) throws CommonException {
        if (EmptyUtils.isEmpty(pageIndex)) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(), ResponseCode.PARAM_NOT_NULL.getDec());
        }
        if (EmptyUtils.isEmpty(pageSize)) {
            pageSize = 10;
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<User> users = userMapper.queryUsers(userName, groupId);
        if (users == null) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SELECT_ERROR.getCode(), ResponseCode.SELECT_ERROR.getDec());
        }
        PageInfo pageInfo = new PageInfo<>(users, pageSize);
        return Response.createBySuccess("查询成功", pageInfo);
    }

    /**
     * 主键查询
     *
     * @param id 用户id
     * @return
     */
    @Override
    public Response<User> getUser(Integer id){
        if (EmptyUtils.isEmpty(id)) {
            return Response.createByErrorCodeMessage(ResponseCode.PARAM_NOT_NULL.getCode(), ResponseCode.PARAM_NOT_NULL.getDec());
        }
        User user = userMapper.get(id);
        if (user == null) {
            return Response.createByErrorCodeMessage(ResponseCode.SELECT_ERROR.getCode(), ResponseCode.SELECT_ERROR.getDec());
        }
        user.setPassWord(null);
        return Response.createBySuccess("主键查询成功", user);
    }

    /**
     * 验证用户是否存在
     *
     * @param str
     * @param type
     * @return
     * @throws CommonException
     */
    public Response<String> Exist(String str, Integer id, String type) throws CommonException {
        if (Const.USERNAME.equals(type)) {
            if (!EmptyUtils.isEmpty(id)) {
                User user = userMapper.get(id);
                if (user != null) {
                    throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.USERNAME_EXIST.getCode(), ResponseCode.USERNAME_EXIST.getDec());
                }
            }
            int resultCount = userMapper.counts(str);
            if (resultCount > 0) {
                throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.USERNAME_EXIST.getCode(), ResponseCode.USERNAME_EXIST.getDec());
            }
        }
        return Response.createBySuccessMessage("校验成功");
    }

}
