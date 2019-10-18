package com.github.mydemo.controller;

import com.github.mydemo.common.utils.DateUtil;
import com.github.mydemo.model.User;
import com.github.mydemo.model.vo.UserExt;
import com.github.mydemo.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @ClassName
 * @Decription TOO
 * @Author HanniOvO
 * @Date 2019/10/15 12:22
 */
@RestController
@RequestMapping("/user")
@Log4j2
public class TestController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "单个查询--id",notes = "单个查询--")
    @PostMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Integer userId){
        try {
            log.info("【用户模块】--{单个用户查询}--[通过id查询]--id="+userId);

            User user=userService.getOneUserById(userId);

            return user;

        }catch (Exception e){
            e.printStackTrace();
            log.error("【用户模块】--{单个用户查询}--[通过id查询的异常信息]="+e);
        }
        return null;
    }

    @ApiOperation(value = "单个查询--name",notes = "单个查询--name")
    @PostMapping("/getUserByName")
    public User getUserByName(@RequestBody String username){

        try {
            log.info("【用户模块】--{单个用户查询}--[通过name查询]--name="+username);
            User user=userService.getUserByName(username);
            return user;

        }catch (Exception e){
            e.printStackTrace();
            log.error("【用户模块】--{单个用户查询}--[通过name查询的异常信息]="+e);
        }

        return null;
    }


    @ApiOperation(value = "单个查询--id",notes = "单个查询--id")
    @PostMapping("/getOneUserById/{id}")
    public Map<Integer,Object> getOneUserById(@PathVariable("id") Integer userId){
        try {
            log.info("【用户模块】--{单个用户查询}--[通过id查询]--id="+userId);


            Map<Integer,Object> map=new HashMap<>();
            map=userService.getUserById(userId);

            return map;

        }catch (Exception e){
            e.printStackTrace();
            log.error("【用户模块】--{单个用户查询}--[通过id查询的异常信息]="+e);
        }
        return null;
    }


    @ApiOperation(value = "单个查询--name",notes = "单个查询--name")
    @PostMapping("/getOneUserByName")
    public Map<Integer,Object> getOneUserByName(@RequestBody String username){

        try {
            log.info("【用户模块】--{单个用户查询}--[通过name查询]--name="+username);
            Map<Integer,Object> map=new HashMap<>();
            map=userService.getOneUserByName(username);
            return map;

        }catch (Exception e){
            e.printStackTrace();
            log.error("【用户模块】--{单个用户查询}--[通过name查询的异常信息]="+e);
        }

        return null;
    }

    @ApiOperation(value = "添加用户",notes = "添加用户")
    @PostMapping("/addUserNew")
    public Map<Integer,Object> addUserNew(@RequestBody User user){

        try {
            log.info("【用户模块】--{添加用户}--[添加用户]--user="+user.toString());
            Map<Integer,Object> map=new HashMap<>();
            map=userService.addUser(user);
            return map;

        }catch (Exception e){
            e.printStackTrace();
            log.error("【用户模块】--{添加用户}--[添加用户的异常信息]="+e);
        }

        return null;
    }


    @ApiOperation(value = "编辑用户",notes = "编辑用户")
    @PostMapping("/editUserNew")
    public Map<Integer,Object> editUserNew(@RequestBody User user){
        try {
            log.info("【用户模块】--{编辑用户}--[编辑用户]--user="+user.toString());
            Map<Integer,Object> map=new HashMap<>();
            map=userService.editUser(user);
            return map;

        }catch (Exception e){
            e.printStackTrace();
            log.error("【用户模块】--{编辑用户}--[编辑用户的异常信息]="+e);
        }

        return null;
    }


    @ApiOperation(value = "删除单个用户",notes = "删除单个用户")
    @PostMapping("/delOneUser/{id}")
    public Map<Integer,Object> delOneUser(@PathVariable("id") Integer userId){
        try {
            log.info("【用户模块】--{删除用户}--[删除单个用户]--userId="+userId);

            Map<Integer,Object> map=new HashMap<>();
            map=userService.deleteOneUserById(userId);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            log.info("【用户模块】--{删除用户}--[删除单个用户的异常信息]--e="+e);
        }
        return null;
    }


    @ApiOperation(value = "删除多个用户",notes = "删除多个用户")
    @PostMapping("/delMoreUser/{id}")
    public Map<Integer,Object> delMoreUser(@PathVariable("id") Integer[] userIds){
        try {

            for (Integer userId:userIds) {
                log.info("【用户模块】--{删除用户}--[删除多个用户]--userIds=" + userId);
            }

            return  userService.deleteMoreUsers(userIds);

        }catch (Exception e){
            e.printStackTrace();
            log.info("【用户模块】--{删除用户}--[删除多个用户的异常信息]--e="+e);
        }
        return null;
    }

    @ApiOperation(value = "删除多个用户",notes = "删除多个用户")
    @PostMapping("/delMoreUserAnother/{id}")
    public Map<Integer,Object> delMoreUserAnother(@PathVariable("id") Integer[] userIds){
        try {

            for (Integer userId:userIds) {
                log.info("【用户模块】--{删除用户}--[删除多个用户]--userIds=" + userId);
            }

            return  userService.deleteMoreUsersAnother(userIds);

        }catch (Exception e){
            e.printStackTrace();
            log.info("【用户模块】--{删除用户}--[删除多个用户的异常信息]--e="+e);
        }
        return null;
    }

    @ApiOperation(value = "查询所有",notes ="查询所有")
    @PostMapping("/listAllUsers")
    public List<User> listAllUsers() {
        try {
            List<User> userList = userService.queryAllUsers();
            return userList;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/queryUsersPage")
    public PageInfo<User> queryUsersPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestBody UserExt userExt){
        try {

            log.info("【用户模块】--{查询用户}--[分页查询用户]--pageNum="+pageNum);
            log.info("【用户模块】--{查询用户}--[分页查询用户]--pageSize="+pageSize);
            log.info("【用户模块】--{查询用户}--[分页查询用户]--userExt="+userExt.toString());


            PageInfo<User> userPageInfo=userService.queryAllUsersPage(pageNum,pageSize,userExt);
            return userPageInfo;

        }catch (Exception e){
            e.printStackTrace();
            log.info("【用户模块】--{查询用户}--[分页查询用户的异常信息]--e="+e);

        }
        return null;
    }

}
