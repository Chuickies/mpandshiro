package com.zcj.mybatisplus.controller;

import com.zcj.mybatisplus.entity.SysUser;
import com.zcj.mybatisplus.entity.param.UserParam;
import com.zcj.mybatisplus.entity.result.ResultInfo;
import com.zcj.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/page")
    public String page() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultInfo register(@RequestBody SysUser sysUser) {
        sysUser.setStatus(0);
        Boolean success = userService.addUser(sysUser);
        if (success) {
            return new ResultInfo(true,"注册成功");
        } else {
            return new ResultInfo(true,"注册失败");
        }
    }

    @RequestMapping("/login")
    public void login() {

    }
}
