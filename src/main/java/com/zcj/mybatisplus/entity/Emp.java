package com.zcj.mybatisplus.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Emp {
    private Integer id;
    private String name;
    private String gender;
    private BigDecimal salary;
    private Date joinDate;
    private Integer deptId;

}
