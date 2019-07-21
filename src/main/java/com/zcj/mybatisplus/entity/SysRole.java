package com.zcj.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@TableName("sys_role")
public class SysRole implements Serializable {
    private Integer id;
    private String roleName;
    private String roleDesc;
    private List<SysUser> userList;
    private List<SysPermission> permissions;
}
