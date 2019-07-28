package com.zcj.mybatisplus.controller;

import com.zcj.mybatisplus.entity.SysUser;
import com.zcj.mybatisplus.entity.param.UserParam;
import com.zcj.mybatisplus.entity.result.ResultInfo;
import com.zcj.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public ResultInfo register(@RequestParam(name = "userParam") SysUser sysUser) {
        sysUser.setStatus(0);
        boolean save = userService.save(sysUser);
        if (save)
            new ResultInfo(true, "注册成功");
        return new ResultInfo(false,"失败");
    }

    @RequestMapping("/login")
    public void login() {

    }
}
