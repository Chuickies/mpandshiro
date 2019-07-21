package com.zcj.mybatisplus;

import com.zcj.mybatisplus.entity.Emp;
import com.zcj.mybatisplus.entity.SysUser;
import com.zcj.mybatisplus.mapper.EmpMapper;
import com.zcj.mybatisplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MybatisPlusApplicationTests {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private UserService userService;

    /**
     * 查询所有的员工信息
     */
    @Test
    public void QueryAllEmp() {
      System.out.println("-------Select All DataSource----");
        List<Emp> empList = empMapper.selectList(null);
        empList.stream().forEach((emp)->{System.out.println(emp.toString()); });
    }
    @Test
    public void QueryUserById(){
        SysUser sysUser = userService.getById(1);
        System.out.println(sysUser);
    }

}
