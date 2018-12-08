package com.ego.model.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author ego
 * @since 2018-12-08 14:46
 **/
public class MyDateUtil {

    public static void main(String[] args){
        // 获取今天的日期
        LocalDate today = LocalDate.now();
        System.out.println("获取今天的日期:" + today);

        // 今天是几号
        int dayOfMonth = today.getDayOfMonth();
        System.out.println("今天是几号:" + dayOfMonth);

        // 今天是周几（返回的是个枚举类型，需要再getValue()）
        int dayOfWeek = today.getDayOfWeek().getValue();
        System.out.println("今天是周几:" + dayOfWeek);

        // 今天是今年的哪一天
        int dayOfYear = today.getDayOfYear();
        System.out.println("今年是哪一年:" + dayOfYear);

        // 根据字符串取：
        // 严格按照yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
        LocalDate endOfFeb = LocalDate.parse("2018-02-28");
        System.out.println(endOfFeb);

        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("取本月第1天:" + firstDayOfThisMonth);

        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        System.out.println("取本月第2天:" + secondDayOfThisMonth);

        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("取本月最后一天:" + lastDayOfThisMonth);

        // 取下一天
        LocalDate nextDate = today.plusDays(1);
        System.out.println("取下一天:" + nextDate);

        // 取下个月第一天：
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);
        System.out.println("取下个月第一天:" + firstDayOfNextMonth);

        // 取2017年1月第一个周一：
        LocalDate firstMondayOf2017 = LocalDate.parse("2017-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println("取2017年1月第一个周一:" + firstMondayOf2017);

        // 解析特定格式的日期
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String text = today.format(formatters);
        System.out.println("解析特定格式的日期:" + text);
    }
}
