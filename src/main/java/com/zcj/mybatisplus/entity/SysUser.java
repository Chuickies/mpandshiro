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
     /*用户Id*/
    @TableId(value = "user_id",type = IdType.AUTO )
    private Long UserId;
    /*用户名*/
    private String userName;
    /*邮箱*/
    private String email;
    /*密码*/
    private String password;
    /*手机号*/
    private String phoneNum;
    /*状态*/
    private int status;
    /*用户角色*/
    @TableField(exist = false)
    private List<SysRole> roleList;
}
