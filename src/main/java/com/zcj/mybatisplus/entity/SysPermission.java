package com.zcj.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@TableName("sys_permission")
public class SysPermission implements Serializable {
    private Long id;
    private String permissionName;
    private String url;
    private Long pid;
    private List<SysRole> roles;
}
