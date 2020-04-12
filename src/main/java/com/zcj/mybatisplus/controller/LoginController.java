package com.zcj.mybatisplus.controller;

import com.zcj.mybatisplus.entity.SysUser;
import com.zcj.mybatisplus.entity.result.R;
import com.zcj.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@Validated
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
    public R register(@RequestBody  SysUser sysUser) {
        sysUser.setCreateTime(new Date());
        sysUser.setStatus(0);
        Boolean success = userService.addUser(sysUser);
        return R.ok();
    }

    @RequestMapping("/login")
    public void login() {

    }
}
