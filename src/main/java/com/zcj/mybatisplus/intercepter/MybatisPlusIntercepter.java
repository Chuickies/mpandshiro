package com.zcj.mybatisplus.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

@Slf4j
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisPlusIntercepter implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
       MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter= invocation.getArgs()[1];
        if(parameter==null){
            invocation.proceed();

        }
        if(SqlCommandType.INSERT.equals(sqlCommandType)){
            Field[] fields = parameter.getClass().getDeclaredFields();
            if(parameter.getClass().getSuperclass() != null && parameter.getClass().getSuperclass().getName().equals("com.zcj.mybatisplus.entity.BaseEntity")){
                for (Field field : fields) {
                  if(field.getName().equals("createTime")) {
                        this.fileAddValue("createTime", field, parameter);
                    }else if(field.getName().equals("updateTime")) {
                        this.fileAddValue("updateTime", field, parameter);
                    }
                }
            }
        }
        return null;
    }

    private void fileAddValue(String fildName, Field field, Object parameter) throws IllegalAccessException {
        // 注入创建时间/更新时间
        if (fildName.equals(field.getName())) {
            field.setAccessible(true);
            Object date = field.get(parameter);
            field.setAccessible(false);
            if (date == null || "updateTime".equals(fildName)) {
                field.setAccessible(true);
                field.set(parameter, new Date());
                field.setAccessible(false);
            }
        }
    }

    @Override
    public Object plugin(Object o) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
