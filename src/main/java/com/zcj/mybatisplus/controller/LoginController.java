package com.zcj.mybatisplus.controller;

import com.zcj.mybatisplus.entity.SysUser;
import com.zcj.mybatisplus.entity.param.UserParam;
import com.zcj.mybatisplus.entity.result.ResultInfo;
import com.zcj.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/page")
    public String page(){
        return "register";
    }

    @RequestMapping("/register")
    public ResultInfo register(@RequestBody( required = true) SysUser sysUser) {
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
