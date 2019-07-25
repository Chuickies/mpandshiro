package com.zcj.mybatisplus.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式化工具类
 *
 * @author zhoucj
 */
public class TimeUtils implements Serializable {
    public static final String format1 = "yyyy-MM-dd HH:mm:ss";
    public static final String format2 = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String format3 = "yyyy-MM-dd";
    public static final String format4 = "yyyy年MM月dd日";

    public static String FormatTime(Date time, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(time);
    }
}
