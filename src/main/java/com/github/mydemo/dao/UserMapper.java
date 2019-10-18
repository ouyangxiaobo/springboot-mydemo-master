package com.github.mydemo.dao;

import com.github.mydemo.model.User;
import com.github.mydemo.model.vo.UserExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByName( String username);

    List<User> selectLikeUsers(@Param(value = "username")String username);

    int deleteMoreUsersAnother(Integer[] userIds);

    List<User> queryAllUsers();

    List<User> pageAllUsers(UserExt userExt);
}