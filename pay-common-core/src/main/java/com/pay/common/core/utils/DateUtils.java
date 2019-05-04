package com.pay.common.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chenwei
 * @date 2019-05-04
 */
public class DateUtils {

    /**
     * d0结算日期
     *
     * @param tradeDate   交易日期
     * @param checkHour   结算时间：小时
     * @param checkMin    结算时间：分钟
     * @param checkSecond 结算时间：秒
     * @return d0结算日期
     */
    public static Date checkDateD0(Date tradeDate, Integer checkHour, Integer checkMin, Integer checkSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tradeDate);
        setCalendarTime(calendar, checkHour, checkMin, checkSecond);
        return calendar.getTime();
    }

    /**
     * d1结算日期
     *
     * @param tradeDate   交易日期
     * @param checkHour   结算时间：小时
     * @param checkMin    结算时间：分钟
     * @param checkSecond 结算时间：秒
     * @return d0结算日期
     */
    public static Date checkDateD1(Date tradeDate, Integer checkHour, Integer checkMin, Integer checkSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tradeDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        setCalendarTime(calendar, checkHour, checkMin, checkSecond);
        return calendar.getTime();
    }



    /**
     * 设置日历对象时间
     *
     * @param calendar    日历对象
     * @param checkHour   结算时间：小时
     * @param checkMin    结算时间：分钟
     * @param checkSecond 结算时间：秒
     */
    private static void setCalendarTime(Calendar calendar, Integer checkHour, Integer checkMin, Integer checkSecond) {
        if (checkHour != null) {
            calendar.set(Calendar.HOUR_OF_DAY, checkHour);
        }
        if (checkMin != null) {
            calendar.set(Calendar.MINUTE, checkMin);
        }
        if (checkSecond != null) {
            calendar.set(Calendar.SECOND, checkSecond);
        }
    }


    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = checkDateD1(new Date(), 21, 7, 8);
        System.out.println(sdf.format(date));
    }

}
