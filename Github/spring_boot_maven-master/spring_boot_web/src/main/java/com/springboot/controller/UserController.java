package com.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.entity.dto.ResultDto;
import com.springboot.entity.dto.ResultDtos;
import com.springboot.entity.user.User;
import com.springboot.redis.RedisService;
import com.springboot.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pzy
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redis;
    //    ,@Cacheable注解代表从缓存中查询指定的key,如果有,从缓存中取,不再执行方法.如果没有则执行方法,并且将方法的返回值和指定的key关联起来,放入到缓存中.
//   @CacheEvict则是从缓存中清除指定的key对应的数据
    @Cacheable( value = "rediss" ,key="'a'")
    @ApiOperation(value = "/getAll", notes = "查询用户", httpMethod = "GET", protocols = "http", produces = "*/*")
    @ApiResponse(code = 200, message = "查询成功", response = String.class)
    @RequestMapping("/getAll")
    public List<User> getAll() {
        List<User> list = userService.getAll();
        return list;
    }

    @ApiOperation(value = "/findByParam", notes = "根据id查询用户", httpMethod = "POST", protocols = "http", produces = "*/*")
    @ApiResponse(code = 200, message = "查询成功", response = String.class)
    @RequestMapping("/findByParam")
    public ResultDto<User> findByParam(User user) {

        ResultDto<User> resultDto = new ResultDto<User>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        User u = userService.findByParam(map);
        if (u != null) {
            resultDto.setCode(200);
            resultDto.setDto(u);
            resultDto.setMsg("查询成功");
        } else {
            resultDto.setCode(400);
            resultDto.setMsg("查询失败");
        }
        return resultDto;
    }

    @ApiOperation(value = "/addUser", notes = "添加用户", httpMethod = "GET", protocols = "http", produces = "*/*")
    @ApiResponse(code = 200, message = "添加成功", response = String.class)
    @RequestMapping("/addUser")
    public ResultDto<User> addUser(User user) {
        ResultDto<User> resultDto = new ResultDto<>();
        if (userService.addUser(user)) {
            resultDto.setCode(200);
            resultDto.setMsg("添加成功");
        } else {
            resultDto.setCode(400);
            resultDto.setMsg("添加失败");
        }
        return resultDto;

    }

    @ApiOperation(value = "/updateUser", notes = "根据id修改用户", httpMethod = "POST", protocols = "http", produces = "*/*")
    @ApiResponse(code = 200, message = "修改成功", response = String.class)
    @RequestMapping("/updateUser")
    public ResultDto<User> updateUser(User user) {
        ResultDto<User> resultDto = new ResultDto<>();
        if (userService.updateUser(user)) {
            resultDto.setCode(200);
            resultDto.setMsg("更新成功");
        } else {
            resultDto.setCode(400);
            resultDto.setMsg("更新失败");
        }
        return resultDto;

    }

    @ApiOperation(value = "/deleteUser", notes = "删除用户信息", httpMethod = "POST", protocols = "http", produces = "*/*")
    @ApiResponse(code = 200, message = "删除成功", response = String.class)
    @RequestMapping("/deleteUSer")
    public ResultDto<User> deleteUser(User user) {
        ResultDto<User> resultDto = new ResultDto<>();
        if (userService.deleteUser(user.getId())) {
            resultDto.setCode(200);
            resultDto.setMsg("删除成功");
        } else {
            resultDto.setCode(400);
            resultDto.setMsg("删除失败");
        }
        return resultDto;
    }

    @ApiOperation(value = "/findByMap", notes = "根据条件查询，返回结果集合", httpMethod = "POST", protocols = "http", produces = "*/*")
    @ApiResponse(code = 200, message = "查询成功", response = String.class)
    @RequestMapping("/findByMap")
    public ResultDtos<User> findByMap(User user) {
        ResultDtos<User> resultDto = new ResultDtos<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("userCode", user.getUserCode());
        List<User> list = userService.findByMap(map);
        if (list != null) {
            resultDto.setCode(200);
            resultDto.setMsg("查询成功");
            resultDto.setDto(list);
        } else {
            resultDto.setCode(400);
            resultDto.setMsg("查询失败哦");
        }
        return resultDto;
    }

    @ApiOperation(value = "/pagination", notes = "分页查询，返回结果集合", httpMethod = "POST", protocols = "http", produces = "*/*")
    @ApiResponse(code = 200, message = "查询成功", response = String.class)
    @RequestMapping("/pagination")
    public Object pagination(Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;   //设置默认当前页
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 5;

        }
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.getAll();
        PageInfo<User> info = new PageInfo<User>(list);
        return info;

    }

}
