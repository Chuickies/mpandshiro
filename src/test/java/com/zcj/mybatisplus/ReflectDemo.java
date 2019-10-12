package com.zcj.mybatisplus;

import com.zcj.mybatisplus.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    public void  getFieldTest() throws IllegalAccessException {
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
        //List<HashMap<String, Object>> classAttributeValue = ReflectDemo.getClassAttributeValue(sysUser);
        List<Map<Object, Object>> mapList = ReflectDemo.comparableForObject(sysUser1, sysUser2);
        for (Map<Object, Object> objectObjectMap : mapList) {
            Set<Map.Entry<Object, Object>> entries = objectObjectMap.entrySet();
            for (Map.Entry<Object, Object> entry : entries) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                System.out.println("旧的属性值"+key+":"+"新的属性值"+value);
            }
        }
        //log.info("sysUser fields are ={}",);
    }
    /**
     * 对比同一对象的属性值是否相同如果不相同则记录
     */
    public static List<Map<Object,Object>> comparableForObject(Object o1,Object o2) throws IllegalAccessException {
        Field[] o1Fields = o1.getClass().getDeclaredFields();
        Field[] o2Fields = o2.getClass().getDeclaredFields();
            List<Map<Object,Object>> mapList = new LinkedList<>();
        for (int i = 0; i < o1Fields.length; i++) {
            for (int j = 0; j < o2Fields.length; j++) {
                //相同属性名,属性值相同则设置直接跳出，不同则记录属性的值
                if(o1Fields[i].getName().equals(o2Fields[j].getName())){
                    o1Fields[i].setAccessible(true);
                    o2Fields[j].setAccessible(true);
                    if(compareTwo(o1Fields[i].get(o1),o2Fields[j].get(o2))){
                        break;
                    }
                    Map<Object,Object> map =  new HashMap<>();

                    String oldFieldValue = o1Fields[i].getName()+":"+o1Fields[i].get(o1);
                    String newFieldValue=o2Fields[j].getName()+":"+o2Fields[j].get(o2);
                    map.put(oldFieldValue,newFieldValue);
                    mapList.add(map);
                }
            }
        }
        return mapList;
    }

    //对比两个数据是否内容相同
    public static boolean compareTwo(Object object1, Object object2) {

        if (object1 == null && object2 == null) {
            return true;
        }
        //以下注掉代码，看具体需求。因有时会出现源数据是没有进行赋值，因此是null；而通过EditText获取值的时候，虽然没有值，但是会变成""，但是本质是没有赋值的。
        if (object1 == "" && object2 == null) {
            return true;
        }
        if (object1 == null && object2 == "") {
            return true;
         }
        if (object1 == null && object2 != null) {
            return false;
        }
        if (object1.equals(object2)) {
            return true;
        }
        return false;
    }
}
