package com.zcj.mybatisplus;

import com.zcj.mybatisplus.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ReflectDemo {



    /**
     * 获取某个对象的所有属性和属性值
     * @param obj
     */
    public static List<HashMap<String,Object>> getClassAttributeValue(Object obj){
        List<HashMap<String,Object>> fieldList= new ArrayList<>();
        Class<?> cls = obj.getClass();
        try {
            //获取类的所有属性值
            Field[] fields = cls.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                HashMap<String,Object> fieldMap = new HashMap<>();
                Field field = fields[i];
                //暴力获取
                field.setAccessible(true);
                //获取属性名和属性值
                String fieldName = field.getName();
                Object value = field.get(obj);
                fieldMap.put(fieldName,value);
                fieldList.add(fieldMap);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldList;
    }
    @Test
    public void  getFieldTest(){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("张三");
        sysUser.setPassword("12412");
        sysUser.setEmail("errw@qq.com");
        sysUser.setPhoneNum("13990127832");
        sysUser.setStatus(0);
        sysUser.setUserId(new Random().nextLong());
        List<HashMap<String, Object>> classAttributeValue = ReflectDemo.getClassAttributeValue(sysUser);
        log.info("sysUser fields are ={}",classAttributeValue);
    }
    /**
     * 对比同一对象的属性值
     */
    public static void comparableForObject(Object o1,Object o2){

    }
}
