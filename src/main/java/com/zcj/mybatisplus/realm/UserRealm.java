package com.zcj.mybatisplus.realm;

import com.zcj.mybatisplus.entity.SysPermission;
import com.zcj.mybatisplus.entity.SysRole;
import com.zcj.mybatisplus.entity.SysUser;
import com.zcj.mybatisplus.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {  //授权
        System.out.println("====执行授权====");
        Subject subject = SecurityUtils.getSubject();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser user = (SysUser) subject.getPrincipal();
        List<SysRole> roleList = user.getRoleList();
        if (roleList != null && roleList.size()>0) {
            for (SysRole sysRole : roleList) {
                authorizationInfo.addRole(sysRole.getRoleName());
                for (SysPermission permission : sysRole.getPermissions()) {
                    authorizationInfo.addStringPermission(permission.getPermissionName());
                }
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {  //身份验证

        System.out.println("身份验证...");
        String username = (String) authenticationToken.getPrincipal();

        SysUser sysUser = userService.findUserByUserName(username);
        if(sysUser==null){
            throw  new AccountException("找不到这个用户....");
        }else if(sysUser.getStatus()==0){
            throw new DisabledAccountException("账号不许允许登录");
        }
        SimpleAuthenticationInfo authorizationInfo  = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), getName());
        return authorizationInfo;
    }
}
