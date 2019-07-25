package com.zcj.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcj.mybatisplus.entity.*;
import com.zcj.mybatisplus.mapper.*;
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
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;

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
        List<Integer> roleIds = null;
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

    /**
     * 查询用户的权限
     *
     * @param userId
     * @return
     */
    public List<SysPermission> findPermission(Integer userId) {
        List<SysRole> sysRoles = this.findRoles(userId);
        List<Integer> sysRoleIds = null;
        if (sysRoles != null && sysRoles.size() > 0) {
            sysRoleIds = new ArrayList<>(sysRoles.size());
            for (SysRole sysRole : sysRoles) {
                sysRoleIds.add(sysRole.getId());
            }
        }

        QueryWrapper<SysRolePermission> rpWrapper = new QueryWrapper<>();
        rpWrapper.in("roleId", sysRoleIds);
        List<SysRolePermission> rolePermissions = rolePermissionMapper.selectList(rpWrapper);
        List<Integer> permissionIds = null;
        if (rolePermissions != null && rolePermissions.size() > 0) {
            permissionIds = new ArrayList<>(rolePermissions.size());
            for (SysRolePermission rolePermission : rolePermissions) {
                permissionIds.add(rolePermission.getPermissionId());
            }
        }

        if (permissionIds.size() != 0) {
            QueryWrapper<SysPermission> permissionWrapper = new QueryWrapper<>();
            permissionWrapper.in("permissionId", permissionIds);
            return permissionMapper.selectList(permissionWrapper);
        }
        return new ArrayList<>();
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

    /**
     * 查询出所有的用户
     * @return
     */
    public List<SysUser> queryAllUser(){
        return userMapper.selectList(null);
    }

    @Override
    public SysUser findUserByUserName(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        SysUser sysUser = userMapper.selectOne(queryWrapper);
        return sysUser;
    }
}
