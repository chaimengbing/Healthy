package com.health.infrared.utils;


import android.net.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(sdate);
        } catch (Exception e) {
            return null;
        }
    }

    public static String friendlyTimeFormat(String sdate) {
        Date time = toDate(sdate);
        if (time == null) {
            return "";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        //判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }


        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 30) {
            ftime = days + "天前";
        } else if (days > 30 && days <= 60) {
            ftime = "1个月前";
        } else if (days > 60 && days <= 90) {
            ftime = "2个月前";
        } else if (days > 90 && days <= 120) {
            ftime = "3个月前";
        } else if (days > 120 && days <= 150) {
            ftime = "4个月前";
        } else if (days > 150 && days <= 180) {
            ftime = "5个月前";
        } else if (days > 180 && days <= 210) {
            ftime = "6个月前";
        } else if (days > 210 && days <= 240) {
            ftime = "7个月前";
        } else if (days > 240 && days <= 270) {
            ftime = "8个月前";
        } else if (days > 270 && days <= 300) {
            ftime = "9个月前";
        } else if (days > 300 && days <= 330) {
            ftime = "10个月前";
        } else if (days > 330 && days <= 360) {
            ftime = "11个月前";
        } else if (days > 360 && days <= 720) {
            ftime = "一年前";
        } else if (days > 720 && days <= 1080) {
            ftime = "两年前";
        } else if (days > 1080) {
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }


    /**
     * 掉此方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）返回时间戳
     *
     * @param time
     * @return
     */
    public String data(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return times;
    }


    public static String getWeek(long timeStamp) {
        String week = null;
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(timeStamp));
        int mydate = cd.get(Calendar.DAY_OF_WEEK);
        // 获取指定日期转换成星期几
        if (mydate == 1) {
            week = "周日";
        } else if (mydate == 2) {
            week = "周一";
        } else if (mydate == 3) {
            week = "周二";
        } else if (mydate == 4) {
            week = "周三";
        } else if (mydate == 5) {
            week = "周四";
        } else if (mydate == 6) {
            week = "周五";
        } else if (mydate == 7) {
            week = "周六";
        }
        return week;
    }

    // 并用分割符把时间分成时间数组

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14-16-09-00"）
     *
     * @param time
     * @return
     */
    //
    public static String timesOne(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

        String times = sdr.format(new Date(time * 1000L));
        return times;
    }

    public String timesOnes(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }

    /**
     * 返回给定时间的年份
     *
     * @param time 时间戳
     * @return
     */
    public static String timesY(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy");

        String times = sdr.format(new Date(time * 1000L));
        return times;
    }

    /**
     * 返回给定时间的秒数
     *
     * @param time 时间戳
     * @return
     */
    public static String timesMon(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM");
        String times = sdr.format(new Date(time * 1000L));
        return times;
    }

    /**
     * 返回给定时间的天数
     *
     * @param time 时间戳
     * @return
     */
    public static String timesD(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("dd");

        String times = sdr.format(new Date(time * 1000L));
        return times;
    }

    /**
     * 返回给定时间的小时数
     *
     * @param time 时间戳
     * @return
     */
    public static String timesH(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH");

        String times = sdr.format(new Date(time * 1000L));
        return times;
    }

    /**
     * 返回给定时间的分钟数
     *
     * @param time 时间戳
     * @return
     */
    public static String timesMin(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("mm");

        String times = sdr.format(new Date(time * 1000L));
        return times;
    }

    /**
     * 并用分割符把时间分成时间数组
     *
     * @param time
     * @return
     */
    public static String[] timestamp(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        String[] fenge = times.split("[年月日时分秒]");
        return fenge;
    }

    /**
     * 根据传递的类型格式化时间
     *
     * @param str
     * @param type 例如：yy-MM-dd
     * @return
     */
    public static String getDateTimeByMillisecond(String str, String type) {

        Date date = new Date(Long.valueOf(str));

        SimpleDateFormat format = new SimpleDateFormat(type);

        String time = format.format(date);

        return time;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(new Date(System.currentTimeMillis()));
    }
}
