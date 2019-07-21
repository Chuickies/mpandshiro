package com.zcj.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("sys_user")
public class SysUser implements Serializable {
    @TableId("userId")
    private Long UserId;
    private String username;
    private String email;
    private String password;
    @TableField("phoneNum")
    private String phoneNum;
    private int status;
    @TableField(exist = false)
    private List<SysRole> roleList;
}
