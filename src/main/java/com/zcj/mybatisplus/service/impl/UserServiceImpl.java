package com.zcj.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcj.mybatisplus.entity.SysPermission;
import com.zcj.mybatisplus.entity.SysRole;
import com.zcj.mybatisplus.entity.SysUser;
import com.zcj.mybatisplus.entity.SysUserRole;
import com.zcj.mybatisplus.mapper.RoleMapper;
import com.zcj.mybatisplus.mapper.UserMapper;
import com.zcj.mybatisplus.mapper.UserRoleMapper;
import com.zcj.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户Id 查询用户的权限信息
     *
     * @param userId
     * @return
     */
    public List<SysRole> findRoles(Integer userId) {
        QueryWrapper<SysUserRole> userRoleWrapper = new QueryWrapper<>();
        userRoleWrapper.eq("userId", userId);
        List<SysUserRole> sysUserRoles = userRoleMapper.selectList(userRoleWrapper);
        List<Integer> roleIds=null;
        if (sysUserRoles != null && sysUserRoles.size() > 0) {
            roleIds = new ArrayList<>(sysUserRoles.size());
            for (SysUserRole sysUserRole : sysUserRoles) {
                roleIds.add(sysUserRole.getRoleId());
            }
        }
        //查询出所有的角色
        QueryWrapper<SysRole> roleWrapper = new QueryWrapper<>();
        roleWrapper.in("id", roleIds);
        List<SysRole> sysRoles = roleMapper.selectList(roleWrapper);
        return sysRoles;
    }

    public List<SysPermission> findPermission(Integer userId) {
        List<SysRole> sysRoles = this.findRoles(userId);
        List<Integer> sysRoleIds=null;
        if (sysRoles != null && sysRoles.size() > 0) {
           sysRoleIds= new ArrayList<>(sysRoles.size());
            for (SysRole sysRole : sysRoles) {
                sysRoleIds.add(sysRole.getId());
            }
        }

        return null;
    }

    /**
     * 根据用户Id查询出用户
     *
     * @param userId
     * @return
     */
    public SysUser findUserById(Integer userId) {
        return userMapper.selectById(userId);
    }
}
