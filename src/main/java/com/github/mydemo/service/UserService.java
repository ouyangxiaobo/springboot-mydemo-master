package com.github.mydemo.service;

import com.github.mydemo.model.User;
import com.github.mydemo.model.vo.UserExt;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface UserService {

    /*单个查询--id*/
    User getOneUserById(Integer userId);

    /*单个查询--name*/
    User getUserByName(String username);

    /*单个查询--id*/
    Map<Integer,Object> getUserById(Integer userId);

    /*模糊查询--name*/
    Map<Integer,Object> getOneUserByName(String username);


    /*添加用户*/
    Map<Integer,Object> addUser(User user) throws  Exception;

    /*编辑用户*/
    Map<Integer,Object> editUser(User user) throws Exception;


    /*删除单个用户*/
    Map<Integer,Object> deleteOneUserById(Integer userId) throws Exception;

    /*删除多个用户---方法一*/
    Map<Integer,Object> deleteMoreUsers(Integer[] userIds) throws Exception;

   /*删除多个用户---方法二*/
    Map<Integer,Object> deleteMoreUsersAnother(Integer[] userIds)throws  Exception;

    /*查询所有用户*/
    List<User> queryAllUsers();

    /*模糊查询用户且分页*/
    PageInfo<User> queryAllUsersPage(Integer pageNum, Integer pageSize, UserExt userExt);






}
