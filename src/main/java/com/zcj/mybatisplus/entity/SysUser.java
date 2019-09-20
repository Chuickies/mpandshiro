package com.zcj.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import com.github.liaochong.myexcel.core.annotation.ExcelTable;
import com.github.liaochong.myexcel.core.annotation.ExcludeColumn;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("sys_user")
@ExcelTable(sheetName = "用户表")
public class SysUser implements Serializable {
    @TableId("userId")
    @ExcelColumn(title = "编号")
    private Long UserId;

    @ExcelColumn(title = "姓名")
    private String username;

    @ExcelColumn(title = "邮箱")
    private String email;

    @ExcelColumn(title = "密码")
    private String password;

    @TableField("phoneNum")
    @ExcelColumn(title = "电话")
    private String phoneNum;

    @ExcelColumn(title = "状态")
    private int status;

    @TableField(exist = false)
    @ExcludeColumn()
    private List<SysRole> roleList;
}
