package com.springboot.service.impl;

import com.springboot.dao.user.UserDao;
import com.springboot.entity.user.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author pzy
 */
@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public Boolean addUser(User user) {
        if (userDao.addUser(user) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateUser(User user) {

        if (userDao.updateUser(user) > 0) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean deleteUser(Integer id) {

        if (userDao.deleteUser(id) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User findByParam(Map<String, Object> map) {
        return userDao.findByParam(map);
    }

    @Override
    public List<User> findByMap(Map<String, Object> map) {
        return userDao.findByMap(map);
    }

}
