package com.zcj.mybatisplus.utils;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 根据首个汉字的首字母来排序
 */
@Slf4j
public class ChinaSortUtils {


    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("安子");
        arrayList.add("逼格");
        arrayList.add("参数");
        arrayList.add("周氏");
        arrayList.add("刘氏");
        arrayList.add("贾式");
        arrayList.add("徐氏");
        arrayList.add("钟氏");
        arrayList.add("黄氏");
        arrayList.add("阿是");
        Map<String, List<String>> sort = sort(arrayList);
        log.info("排序后的字段是：{}",sort);
    }

    /**
     *@Author:xyl
     *@Date: 2019/8/24 16:47
     *@Description: 名字首字母排序
     */
    public static Map<String, List<String>>  sort(List<String> list){
        Map<String, List<String>> rtMap = new HashMap<>();
        Map<String, List<String>> result=new LinkedHashMap();
        for (String s : list) {
            char[] arr = s.toCharArray();
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            if (arr[0] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[0], defaultFormat);
                    if (temp != null) {
                        if (rtMap.containsKey(String.valueOf(temp[0].charAt(0)))) {
                            rtMap.get(String.valueOf(temp[0].charAt(0))).add(s);
                        } else {
                            List<String> li=new ArrayList<>();
                            li.add(s);
                            rtMap.put(String.valueOf(temp[0].charAt(0)), li);
                        }
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                if (rtMap.containsKey(String.valueOf(arr[0]))) {
                    rtMap.get(String.valueOf(arr[0])).add(s);
                } else {
                    List<String> li=new ArrayList<>();
                    li.add(s);
                    rtMap.put(String.valueOf(arr[0]), li);
                }
            }

            result =rtMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        }
        return result;
    }


}
