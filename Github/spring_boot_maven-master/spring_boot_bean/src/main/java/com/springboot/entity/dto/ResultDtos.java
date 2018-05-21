package com.springboot.entity.dto;

import com.springboot.entity.user.User;

import java.util.List;

public class ResultDtos<T> {

    private Integer code;

    private String msg;

    private List<User> dto;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<User> getDto() {
        return dto;
    }

    public void setDto(List<User> dto) {
        this.dto = dto;
    }
}
