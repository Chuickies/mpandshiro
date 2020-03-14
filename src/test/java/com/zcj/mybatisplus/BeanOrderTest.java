package com.zcj.mybatisplus;

import com.zcj.mybatisplus.entity.SysUser;
import com.zcj.mybatisplus.utils.CompareUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Configuration
@SpringBootTest
@RunWith(SpringRunner.class)
public class BeanOrderTest {

    public Date getDate(String time) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(time);
        return date;
    }

    @Test
    public void testSort() throws ParseException {
        List<SysUser> list = new ArrayList<>();
        SysUser user = new SysUser();
        user.setUserName("张三");
        user.setPhoneNum("13980761453");
        user.setPassword("88889999");
        user.setEmail("23213@qq.com");
        user.setCreateTime(getDate("2018-11-17"));

        SysUser user1 = new SysUser();
        user1.setUserName("李四");
        user1.setPhoneNum("14808049313");
        user1.setPassword("231221");
        user1.setEmail("qweqwe23213@qq.com");
        user1.setCreateTime(getDate("2019-11-17"));

        SysUser user9 = new SysUser();
        user9.setUserName("王五");
        user9.setPhoneNum("14808049313");
        user9.setPassword("231221");
        user9.setEmail("oiyitt23213@qq.com");
        user9.setCreateTime(getDate("2019-12-27"));

        SysUser user2 = new SysUser();
        user2.setUserName("赵六");
        user2.setPhoneNum("14808049313");
        user2.setPassword("231221");
        user2.setEmail("kkks23213@qq.com");
        user2.setCreateTime(getDate("2019-10-15"));

        SysUser user3 = new SysUser();
        user3.setUserName("气球");
        user3.setPhoneNum("14808049313");
        user3.setPassword("231221");
        user3.setEmail("wangyi23213@qq.com");
        user3.setCreateTime(getDate("2019-09-17"));

        SysUser user4 = new SysUser();
        user4.setUserName("哈巴");
        user4.setPhoneNum("14808049313");
        user4.setPassword("231221");
        user4.setEmail("q023213@qq.com");
        user4.setCreateTime(getDate("2019-12-07"));

        SysUser user5 = new SysUser();
        user5.setUserName("急救");
        user5.setPhoneNum("19012124212");
        user5.setPassword("231221");
        user5.setEmail("144ferw@qq.com");
        user5.setCreateTime(getDate("2017-11-17"));


        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user9);
        Collections.sort(list, CompareUtil.createComparator(-1,"createTime","userName" ));
        for (SysUser sysUser : list) {
            Date createTime = sysUser.getCreateTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(createTime);
            sysUser.setTime(format);
            System.out.println(sysUser);
        }
    }
}
