package com.zcj.mybatisplus;

import com.zcj.mybatisplus.entity.SysUser;
import com.zcj.mybatisplus.utils.EquatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Configuration
@SpringBootTest
@RunWith(SpringRunner.class)
public class CompareTest {
    @Test
    public void test() throws IllegalAccessException {
        SysUser sysUser1 = new SysUser();
        sysUser1.setUserName("张三");
        sysUser1.setPassword("12345");
        sysUser1.setEmail("2131@qq.com");
        sysUser1.setPhoneNum("333333");
        sysUser1.setStatus(0);
        sysUser1.setUserId(1);


        SysUser sysUser2 = new SysUser();
        sysUser2.setUserName("张三");
        sysUser2.setPassword("54321");
        sysUser2.setEmail("errw@qq.com");
        sysUser2.setPhoneNum("11111111");
        sysUser2.setStatus(1);
        sysUser2.setUserId(1);
        StringBuffer modifyLog = EquatorUtils.getModifyLog(sysUser1, sysUser2);
        log.info("modInfo={}",modifyLog);
    }
    @Test
    public  void prin() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//设置起时间
        cal.add(Calendar.YEAR, 50);//增加一年
        Date time = cal.getTime();
        log.info("输出::"+simpleDateFormat.format(time));
    }
    @Test
    public void test1(){

    }
}
