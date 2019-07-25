package com.zcj.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcj.mybatisplus.entity.SysRole;
import com.zcj.mybatisplus.mapper.RoleMapper;
import com.zcj.mybatisplus.service.RoleService;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {
}
