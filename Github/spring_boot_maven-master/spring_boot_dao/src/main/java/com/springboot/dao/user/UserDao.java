package com.springboot.dao.user;


import com.springboot.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserDao {

    public List<User> getAll();

    public Integer addUser(User user);

    public Integer updateUser(User user);

    public Integer deleteUser(Integer id);

    public User findByParam(Map<String,Object> map);

    public List<User> findByMap(Map<String,Object> map);

}
