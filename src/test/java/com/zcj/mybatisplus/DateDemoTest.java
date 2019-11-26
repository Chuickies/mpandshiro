package com.zcj.mybatisplus;

import com.zcj.mybatisplus.utils.DateUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemoTest {
   private  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd mm-HH-ss");
    @Test
    public void getBeginTimeOfTheDay(){
        Date dayBegin = DateUtils.getDayBegin();

        String format = simpleDateFormat.format(dayBegin);
        System.out.println(format);
    }
    @Test
    public void getStartTimeOfWeek(){
        Date beginDayOfWeek = DateUtils.getBeginDayOfWeek();
        Date endDayOfWeek = DateUtils.getEndDayOfWeek();
        System.out.println("本周的开始时间："+simpleDateFormat.format(beginDayOfWeek));
        System.out.println("本周的结束时间："+simpleDateFormat.format(endDayOfWeek));
    }
}