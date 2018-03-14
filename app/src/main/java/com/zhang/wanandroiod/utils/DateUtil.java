package com.zhang.wanandroiod.utils;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2018/3/2.
 */

public class DateUtil {
    private static SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String getDatePoor(long time1) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = System.currentTimeMillis() - time1;
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;

        if (1 <= day && day <= 3) {

            return day + "天前";

        } else {
            if (day < 1) {
                return hour + "小时" + min + "分钟前";
            } else {
                return mFormat.format(time1);
            }

        }

    }

}
