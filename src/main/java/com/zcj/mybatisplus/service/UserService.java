package com.zcj.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcj.mybatisplus.entity.SysRole;
import com.zcj.mybatisplus.entity.SysUser;

import java.util.List;

public interface UserService extends IService<SysUser> {

    SysUser findUserByUserName(String username);

    /**
     * 用户注册
     * @param sysUser
     * @return
     */
    Boolean addUser(SysUser sysUser);
}
