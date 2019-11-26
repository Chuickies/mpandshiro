package com.zcj.mybatisplus.utils;

import com.zcj.mybatisplus.annotation.AttributeMean;

import java.lang.reflect.Field;
import java.util.*;

public class EquatorUtils {

    /**
     * 记录对象属性值的变化
     */
    public static StringBuffer getModifyLog(Object newObject, Object oldObject) throws IllegalAccessException {
        StringBuffer modLog = new StringBuffer();
        List<Map<String, String>> mapList = EquatorUtils.comparableForObject(newObject, oldObject);

        for (Map<String, String> objectMap : mapList) {
            Set<Map.Entry<String, String>> entries = objectMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                String key = entry.getKey();
                String value = entry.getValue();
                String[] split = value.split(":");
                modLog = modLog.append(key + "由【" + split[0] + "】更改成了").append("【" + split[1] + "】").append(" ");
            }
        }
        return modLog;
    }

    /**
     * 对比同一对象的属性值是否相同如果不相同则记录
     */
    public static List<Map<String, String>> comparableForObject(Object o1, Object o2) throws IllegalAccessException {
        Field[] o1Fields = o1.getClass().getDeclaredFields();
        Field[] o2Fields = o2.getClass().getDeclaredFields();
        List<Map<String, String>> mapList = new LinkedList<>();
        for (int i = 0; i < o1Fields.length; i++) {
            for (int j = 0; j < o2Fields.length; j++) {
                //相同属性名,属性值相同则设置直接跳出，不同则记录属性的值
                if (o1Fields[i].getName().equals(o2Fields[j].getName())) {
                    o1Fields[i].setAccessible(true);
                    o2Fields[j].setAccessible(true);
                    if (compareTwo(o1Fields[i].get(o1), o2Fields[j].get(o2))) {
                        break;
                    }
                    Map<String, String> map = new HashMap<>();
                    //获取某个字段注解属性的值
                    AttributeMean annotation = o1Fields[i].getAnnotation(AttributeMean.class);
                    String attributeMean = annotation.name();

                    Object oldFieldValue = o1Fields[i].get(o1);
                    Object newFieldValue = o2Fields[j].get(o2);
                    String value= oldFieldValue+":"+newFieldValue;
                    map.put(attributeMean, value);
                    mapList.add(map);
                }
            }
        }
        return mapList;
    }

    /**
     * 对比两个数据是否内容相同
     *
     * @param object1
     * @param object2
     * @return
     */
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
