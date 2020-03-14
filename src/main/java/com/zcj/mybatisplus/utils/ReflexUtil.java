package com.zcj.mybatisplus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class ReflexUtil {
    static Logger logger = LoggerFactory.getLogger(ReflexUtil.class);

    //getMethod
    static Object invokeMethod(String propertiesName, Object object) {
        try {
            if (object == null) return null;
            //如果传入的字段名不包含. 说明是一个单纯的字段，直接返回get方法执行的结果
            if (!propertiesName.contains(".")) {
                String methodName = "get" + getMethodName(propertiesName);
                Method method = object.getClass().getMethod(methodName);
                return method.invoke(object);
            }
            //如果是带有.的字符串，需要先获取真实的字段值，即.后面的值
            String methodName = "get" + getMethodName(propertiesName.substring(0, propertiesName.indexOf(".")));
            Method method = object.getClass().getMethod(methodName);
            return invokeMethod(propertiesName.substring(propertiesName.indexOf(".") + 1), method.invoke(object));
        } catch (Exception e) {
            logger.error(e.toString(), e);
            return null;
        }
    }

    //将首字母大写
    private static String getMethodName(String filedName) {
        byte[] items = filedName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}
