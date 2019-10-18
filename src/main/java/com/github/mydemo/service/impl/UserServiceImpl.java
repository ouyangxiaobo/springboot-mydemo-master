package com.github.mydemo.service.impl;

import com.github.mydemo.common.exception.ParentException;
import com.github.mydemo.common.resp.UserEnum;
import com.github.mydemo.common.utils.DateUtil;
import com.github.mydemo.dao.UserMapper;
import com.github.mydemo.model.User;
import com.github.mydemo.model.vo.UserExt;
import com.github.mydemo.service.UserService;
import com.github.pagehelper.PageException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.DateTimeException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @ClassName
 * @Decription TOO
 * @Author HanniOvO
 * @Date 2019/10/15 12:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getOneUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.selectUserByName(username);
    }


    /**
     * @return map
     * @Author ouyang
     * @Description //TODO  根据用户ID查询指定用户
     * @Date 11:14 2019/10/16
     * @Param userId
     **/
    @Override
    public Map<Integer, Object> getUserById(Integer userId) {
        Map<Integer, Object> map = new HashMap<>();
        if (userId == null || userId <= 0) {
            map.put(UserEnum.USER_ID_IS_NULL.getCode(), UserEnum.USER_ID_IS_NULL.getMessage());
            return map;
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            map.put(UserEnum.USER_IS_NOT_EXIST.getCode(), UserEnum.USER_IS_NOT_EXIST.getMessage());
            return map;
        } else {
            map.put(UserEnum.SUCCESS.getCode(), user);
            return map;
        }
    }


    /**
     * @return map
     * @Author ouyang
     * @Description //TODO 根据用户名查询指定用户
     * @Date 11:15 2019/10/16
     * @Param username
     **/
    @Override
    public Map<Integer, Object> getOneUserByName(String username) {

        Map<Integer, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(username)) {
            map.put(UserEnum.USER_NAME_IS_NOT_NULL.getCode(), UserEnum.USER_NAME_IS_NOT_NULL.getMessage());
            return map;
        }
        List<User> user = userMapper.selectLikeUsers(username);
        if (user == null) {
            map.put(UserEnum.USER_IS_NOT_EXIST.getCode(), UserEnum.USER_IS_NOT_EXIST.getMessage());
            return map;
        } else {
            map.put(UserEnum.SUCCESS.getCode(), user);
            return map;
        }

    }


    /**
     * @return map
     * @Author ouyang
     * @Description //TODO 添加用户
     * @Date 11:16 2019/10/16
     * @Param user
     **/

    @Override
    public Map<Integer, Object> addUser(User user) throws Exception {
        Map<Integer, Object> map = new HashMap<>();
        if (user == null) {
            map.put(UserEnum.USER_IS_NULL.getCode(), UserEnum.USER_IS_NULL.getMessage());
            return map;
        }
        User userinfo = userMapper.selectUserByName(user.getUsername());
        if (userinfo != null) {
            map.put(UserEnum.USER_NAME_IS_EXIST.getCode(), UserEnum.USER_NAME_IS_EXIST.getMessage());
            return map;
        }
        User userNew = new User();
        userNew.setBirthday(new Date());
        userNew.setUsername(user.getUsername());
        userNew.setPassword(user.getPassword());
        int result = userMapper.insertSelective(userNew);
        log.info("添加对象==", userNew.toString());
        log.info("添加结果==", result);
        if (result > 0) {
            map.put(result, UserEnum.SUCCESS.getMessage());
            return map;
        } else {
            map.put(result, UserEnum.ERROR.getMessage());
            return map;
        }
    }


    /**
     * @return map
     * @Author ouyang
     * @Description //TODO 编辑用户
     * @Date 11:20 2019/10/16
     * @Param user
     **/

    @Override
    public Map<Integer, Object> editUser(User user) throws Exception {

        Map<Integer, Object> map = new HashMap<>();
        if (user == null) {
            map.put(UserEnum.USER_IS_NULL.getCode(), UserEnum.USER_IS_NULL.getMessage());
            return map;
        }
        User userinfo = userMapper.selectUserByName(user.getUsername());
        if (userinfo != null) {
            map.put(UserEnum.USER_NAME_IS_EXIST.getCode(), UserEnum.USER_NAME_IS_EXIST.getMessage());
            return map;
        }
        User userEdit = new User();
        userEdit.setId(user.getId());
        userEdit.setUsername(user.getUsername());
        userEdit.setPassword(user.getPassword());
        userEdit.setBirthday(new Date());
        int result = userMapper.updateByPrimaryKeySelective(userEdit);
        if (result > 0) {
            map.put(result, UserEnum.SUCCESS.getMessage());
            log.info("添加结果=", result, "添加成功");
            return map;
        } else {
            map.put(result, UserEnum.ERROR.getMessage());
            log.error("添加结果=", result, "添加失败");
            return map;
        }

    }


    /**
     * @return map
     * @Author ouyang
     * @Description //TODO 根据ID删除指定的用户
     * @Date 11:28 2019/10/16
     * @Param userId
     **/

    @Override
    public Map<Integer, Object> deleteOneUserById(Integer userId) throws Exception {

        Map<Integer, Object> map = new HashMap<>();
        if (userId == null || userId <= 0) {
            map.put(UserEnum.USER_ID_IS_NULL.getCode(), UserEnum.USER_ID_IS_NULL.getMessage());
            return map;
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            map.put(UserEnum.USER_IS_NOT_EXIST.getCode(), UserEnum.USER_IS_NOT_EXIST.getMessage());
            return map;
        }
        int result = userMapper.deleteByPrimaryKey(userId);
        if (result > 0) {
            map.put(result, UserEnum.SUCCESS.getMessage());
            log.info("删除结果=", result, "删除成功");
            return map;
        } else {
            map.put(result, UserEnum.ERROR.getMessage());
            log.error("删除结果=", result, "删除失败 ");
            return map;
        }

    }


    /**
     * @return map
     * @Author ouyang
     * @Description //TODO 批量删除用户 ---方法一
     * @Date 12:21 2019/10/16
     * @Param userIds
     **/

    @Override
    public Map<Integer, Object> deleteMoreUsers(Integer[] userIds) throws Exception {
        Map<Integer, Object> map = new HashMap<>();
        if (userIds == null) {
            map.put(UserEnum.USER_ID_IS_NULL.getCode(), UserEnum.USER_ID_IS_NULL.getMessage());
            return map;
        }
        for (Integer userId : userIds) {
          /*  User user=userMapper.selectByPrimaryKey(userId);
            if(user==null){
                map.put(UserEnum.USER_IS_NOT_EXIST.getCode(),UserEnum.USER_IS_NOT_EXIST.getMessage());
                return map;
            }*/
            int result = userMapper.deleteByPrimaryKey(userId);
            if (result > 0) {
                map.put(result, UserEnum.SUCCESS.getMessage());
                return map;
            }
            if (result < 0) {
                map.put(result, UserEnum.ERROR.getMessage());
                return map;
            }

        }
        return map;

    }


    /**
     * @return map
     * @Author ouyang
     * @Description //TODO 批量删除用户 ---方法二
     * @Date 12:22 2019/10/16
     * @Param userIds
     **/

    @Override
    public Map<Integer, Object> deleteMoreUsersAnother(Integer[] userIds) throws Exception {
        Map<Integer, Object> map = new HashMap<>();
        if (userIds == null) {
            map.put(UserEnum.USER_ID_IS_NULL.getCode(), UserEnum.USER_ID_IS_NULL.getMessage());
            return map;
        }

        int result = userMapper.deleteMoreUsersAnother(userIds);
        if (result > 0) {
            map.put(result, UserEnum.SUCCESS.getMessage());
            log.info("删除多个用户成功.....");
            return map;
        } else {
            map.put(result, UserEnum.ERROR.getMessage());
            log.info("删除多个用户失败.....");
            return map;
        }

    }



    /**
     * @Author ouyang
     * @Description //TODO 查询所有用户
     * @Date 12:24 2019/10/16
     * @Param
     * @return list
     **/
    @Override
    public List<User> queryAllUsers() {
        return userMapper.queryAllUsers();
    }


    /**
     * @Author ouyang
     * @Description //TODO 分页查询
     * @Date 9:24 2019/10/17
     * @Param pageNum，pageSize，userExt
     * @return  PageInfo<User>
     **/
    @Override
    public PageInfo<User> queryAllUsersPage(Integer pageNum, Integer pageSize, UserExt userExt)   {
        if(pageNum==null || pageNum==0){
            pageNum=1;
        }
        if(pageSize==null || pageSize==0){
            pageSize=2;
        }

        PageHelper.startPage(pageNum,pageSize);
        List<User> userList=userMapper.pageAllUsers(userExt);
        PageInfo<User> userPageInfo=new PageInfo<>(userList);

        return userPageInfo;
    }
}
