package com.springboot.service;


import com.springboot.entity.user.User;

import java.util.List;
import java.util.Map;

/**
 * @author pzy
 */
public interface UserService {

    public List<User> getAll();

    public Boolean addUser(User user);

    public Boolean updateUser(User user);

    public Boolean deleteUser(Integer id);

    public User findByParam(Map<String,Object> map);

    public List<User> findByMap(Map<String,Object> map);

}
