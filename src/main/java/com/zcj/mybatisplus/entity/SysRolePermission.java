package com.zcj.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class SysRolePermission {
    @TableField("permissionId")
    private Integer permissionId;
    @TableField("roleId")
    private Integer roleId;
}
