package com.zcj.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zcj.mybatisplus.annotation.AttributeMean;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("sys_user")
public class SysUser implements Serializable {
     /*用户Id*/
    @TableId(value = "user_id",type = IdType.AUTO )
    @AttributeMean(name = "用户编号")
    private Integer userId;
    /*用户名*/
    @AttributeMean(name = "用户姓名")
    private String userName;
    /*邮箱*/
    @AttributeMean(name = "邮箱")
    private String email;
    /*密码*/
    @AttributeMean(name = "密码")
    private String password;
    /*手机号*/
    @AttributeMean(name = "手机号")
    private String phoneNum;
    /*状态*/
    @AttributeMean(name = "状态")
    private int status;
    /*用户角色*/
    @TableField(exist = false)
    @AttributeMean(name = "角色列表")
    private List<SysRole> roleList;
}
