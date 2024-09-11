package ai.remi.comm.util.date;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 时间处理工具类
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/4/24 0024 9:54
 * @return
 */
public class LocalDateTimeUtils {

    /**
     * 时间格式
     */
    private static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY_MM = "yyyy-MM";
    private static final String YYYY = "yyyy";

    /**
     * 获取时间类型
     * 1-包含开始和结束时间(默认)
     * 2-包含结束-不包含开始时间   // 开始时间+1天
     * 3-包含开始-不包含结束时间   // 结束时间-1天
     * 4-不包含开始和结束时间 // 开始时间+1天  or 结束时间-1天
     */
    private static final int BETWEEN_TYPE_ONE = 1;
    private static final int BETWEEN_TYPE_TWO = 2;
    private static final int BETWEEN_TYPE_THREE = 3;
    private static final int BETWEEN_TYPE_FOUR = 4;

    private static Random random;

    static {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断时间 小于
     * <P>   t1 < t2 = true （如：2019-10-13 11:11:00 < 2020-11-13 13:13:00 = true）  </P>
     *
     * @author wangsong
     */
    public static boolean isBefore(LocalDateTime t1, LocalDateTime t2) {
        return t1.isBefore(t2);
    }

    /**
     * 判断时间 大于
     * <P>   t1 > t2 = true  </P>
     *
     * @author wangsong
     */
    public static boolean isAfter(LocalDateTime t1, LocalDateTime t2) {
        return t1.isAfter(t2);
    }


    /**
     * 自构建 LocalDateTime ==> 年，月，日，时，分
     *
     * @author wangsong
     */
    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    /**
     * 自构建 LocalDateTime ==> 年，月，日，时，分，秒，毫秒（精确到9位数）
     *
     * @author wangsong
     */
    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond);
    }


    //========================================================================================================
    //========================================================================================================
    //========================================================================================================
    //============================================== 时间获取 =================================================
    //========================================================================================================
    //========================================================================================================

    /**
     * 获取指定某一天的开始时间 00:00:00
     *
     * @param time
     * @return java.time.LocalDateTime
     * @author wangsong
     * @date 2020/12/24 0024 15:10
     * @version 1.0.1
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }


    /**
     * 获取指定某一天的结束时间  23:59:59.999999
     *
     * @author wangsong
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        // 年 月  天 时 分 秒 毫秒（这里精确到6位数）
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999);
    }

    /**
     * 获取指定时间是周几  1到7
     *
     * @author wangsong
     */
    public static int week(LocalDateTime time) {
        return time.getDayOfWeek().getValue();
    }


    /**
     * 获取指定时间之后的日期
     * <P>  根据field不同加不同值 , field为ChronoUnit.*
     * 秒   ChronoUnit.SECONDS
     * 分   ChronoUnit.MINUTES
     * 时   ChronoUnit.HOURS
     * 半天  ChronoUnit.HALF_DAYS
     * 天    ChronoUnit.DAYS
     * 月    ChronoUnit.MONTHS
     * 年    ChronoUnit.YEARS
     * </P>
     *
     * @author wangsong
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }


    /**
     * 获取两个日期的时间差
     *
     * @param startTime 开始时间
     * @param endTime   计算时间
     * @param field     根据field不同减不同值 , field 为 ChronoUnit.*
     * @return startTime小 endTime大 返回正数，则反之
     * @author wangsong
     * <p>
     * 秒    ChronoUnit.SECONDS
     * 分    ChronoUnit.MINUTES
     * 时    ChronoUnit.HOURS
     * 半天  ChronoUnit.HALF_DAYS
     * 天    ChronoUnit.DAYS
     * 月    ChronoUnit.MONTHS
     * 年    ChronoUnit.YEARS
     * </P>
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12L + period.getMonths();
        }
        return field.between(startTime, endTime);
    }


    /**
     * 获取指定时间之前的日期
     *
     * @author wangsong
     * <P> 根据field不同减不同值, field 为 ChronoUnit.*
     * 秒   ChronoUnit.SECONDS
     * 分   ChronoUnit.MINUTES
     * 时   ChronoUnit.HOURS
     * 半天  ChronoUnit.HALF_DAYS
     * 天    ChronoUnit.DAYS
     * 月    ChronoUnit.MONTHS
     * 年    ChronoUnit.YEARS
     * </P>
     * @version 1.0.1
     */
    public static LocalDateTime subtract(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }


    /**
     * 获取指定时间 加或减N周的第一天 00:00:00
     *
     * @author wangsong
     */
    public static LocalDateTime weekFirstDay(LocalDateTime time, int num) {
        int week = week(LocalDateTime.now());
        LocalDateTime newTime = subtract(LocalDateTime.now(), week - 1L, ChronoUnit.DAYS);
        newTime = plus(newTime, num * 7L, ChronoUnit.DAYS);
        return getDayStart(newTime);
    }


    /**
     * 获取指定时间 加或减N周的最后一天  23:59:59:999999
     *
     * @author wangsong
     */
    public static LocalDateTime weekLastDay(LocalDateTime time, int num) {
        int week = week(LocalDateTime.now());
        LocalDateTime newTime = plus(LocalDateTime.now(), 7L - week, ChronoUnit.DAYS);
        newTime = plus(newTime, num * 7L, ChronoUnit.DAYS);
        return getDayEnd(newTime);
    }


    /**
     * 获取指定月 加或减N月的第一天 00:00:00
     *
     * @author wangsong
     */
    public static LocalDateTime monthFirstDay(LocalDateTime time, int num) {
        LocalDateTime newTime = plus(time, num, ChronoUnit.MONTHS);
        newTime = newTime.with(TemporalAdjusters.firstDayOfMonth());
        return getDayStart(newTime);
    }

    /**
     * 获取指定月 加或减N月的最后一天 23:59:59:999999
     *
     * @author wangsong
     */
    public static LocalDateTime monthLastDay(LocalDateTime time, int num) {
        LocalDateTime newTime = plus(time, num, ChronoUnit.MONTHS);
        newTime = newTime.with(TemporalAdjusters.lastDayOfMonth());
        return getDayEnd(newTime);
    }


    /**
     * 获取指定年 加或减N年的第一天 00:00:00
     *
     * @author wangsong
     */
    public static LocalDateTime yearFirstDay(LocalDateTime time, int num) {
        LocalDateTime newTime = plus(time, num, ChronoUnit.YEARS);
        int year = newTime.getYear();
        // 年 月  天 时 分 秒 毫秒（这里精确到9位数）
        return LocalDateTime.of(year, 1, 1, 0, 0, 0);
    }

    /**
     * 获取指定年 加或减N年最后一天  23:59:59:999999
     *
     * @author wangsong
     */
    public static LocalDateTime yearLastDay(LocalDateTime time, int num) {
        LocalDateTime newTime = subtract(time, num, ChronoUnit.YEARS);
        int year = newTime.getYear();
        // 年 月  天 时 分 秒 毫秒（这里精确到6位数）
        return LocalDateTime.of(year, 12, 31, 23, 59, 59, 999999);
    }


    /**
     * 获取17位时间戳字符串+3位随机数
     * <p>  这里增加了线程锁和延时一毫秒，单体项目100%不会重复，可用于生成订单号  </p>
     * 20200101125959999  2020-01-01 12:59:59:999
     *
     * @return
     * @author wangsong
     */
    public static synchronized String getNo() {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        timeStamp += (random.nextInt(10) + "") + (random.nextInt(10) + "") + (random.nextInt(10) + "");
        return timeStamp;
    }


    /**
     * 获取整点--  把指定时间的 分+秒设置为0
     *
     * @param time time
     * @return java.time.LocalDateTime
     * @author wangsong
     * @date 2020/12/24 0024 15:10
     * @version 1.0.1
     */
    public static LocalDateTime getTheHour(LocalDateTime time) {
        // 分   // 秒   // 毫秒（这里精确到9位数）
        return time.withMinute(0)
                .withSecond(0)
                .withNano(0);
    }


    /**
     * 获取整分--  把指定时间的 秒设置为0
     * <p>
     * //	 * 如：
     * //	 * 2020-01-01 12:10  ===>  等于 2020-01-01 12:20
     * //	 * 2020-01-01 12:11  ===>  等于 2020-01-01 12:20
     * //	 * 2020-01-01 12:19  ===>  等于 2020-01-01 12:20
     * </P>
     *
     * @param time
     * @return java.time.LocalDateTime
     * @author wangsong
     * @date 2020/12/24 0024 15:21
     * @version 1.0.1
     */
    public static LocalDateTime getTheMinute(LocalDateTime time) {
        // 秒    // 毫秒（这里精确到9位数）
        return time.withSecond(0).withNano(0);
    }


    //========================================================================================================
    //========================================================================================================
    //========================================================================================================
    //============================================== 转换相关 =================================================
    //========================================================================================================
    //========================================================================================================

    /**
     * LocalDateTime 转为 天 的字符串，如 1号返回 01
     *
     * @author wangsong
     */
    public static Integer parseDayInt(LocalDateTime time) {
        return Integer.parseInt(parse(time, "dd"));
    }


    /**
     * Date 转 LocalDateTime
     *
     * @author wangsong
     */
    public static LocalDateTime parseLdt(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime 转 Date
     *
     * @author wangsong
     */
    public static Date parseDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转 毫秒
     *
     * @author wangsong
     */
    public static Long parseMillisecond(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * LocalDateTime 转 秒
     *
     * @author wangsong
     */
    public static Long parseSecond(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }


    /**
     * 将时间戳转 为 LocalDateTime
     *
     * @param timestamp
     * @return java.lang.String
     * @author wangsong
     * @date 2021/5/12 0012 17:13
     * @version 1.0.1
     */
    public static LocalDateTime parseTimestamp(Long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.ofHours(8));
    }

    /**
     * 将LocalDateTime 转 为时间戳
     *
     * @return java.lang.String
     * @author wangsong
     * @date 2021/5/12 0012 17:13
     * @version 1.0.1
     */
    public static Long parseTimestamp(LocalDateTime time) {
        return time.toEpochSecond(ZoneOffset.ofHours(8));
    }


    /**
     * String 类型转成 LocalDateTime ,必须为完整时间,如：2020-01-20 00:00:00
     *
     * @param timeStr 时间字符串
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime parse(String timeStr) {
        return parse(timeStr, YYYY_MM_DD_HH_MM_SS);
    }


    /**
     * String (2020-01-20 00:00:00)类型转成 LocalDateTime
     *
     * @param timeStr timeStr 时间字符串
     * @param pattern pattern 格式
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime parse(String timeStr, String pattern) {
        if (pattern.equals(YYYY)) {
            timeStr += "-01-01 00:00:00";
        } else if (pattern.equals(YYYY_MM)) {
            timeStr += "-01 00:00:00";
        } else if (pattern.equals(YYYY_MM_DD)) {
            timeStr += " 00:00:00";
        } else if (pattern.equals(YYYY_MM_DD_HH)) {
            timeStr += ":00:00";
        } else if (pattern.equals(YYYY_MM_DD_HH_MM)) {
            timeStr += ":00";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
        return LocalDateTime.parse(timeStr, dtf);
    }


    /**
     * LocalDateTime 转完整 String 类型的时间 如：2020-01-20 00:00:00
     *
     * @param time time
     * @return java.lang.String
     */
    public static String parse(LocalDateTime time) {
        return parse(time, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * LocalDateTime 转指定类型的字符串
     *
     * @param time    time    时间
     * @param pattern pattern 格式
     * @return java.lang.String
     * @author wangsong
     */
    public static String parse(LocalDateTime time, String pattern) {
        if (time == null) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(time);
    }


    /**
     * Date 转指定格式的字符串
     *
     * @param time
     * @author wangsong
     */
    public static String parse(Date time, String pattern) {
        if (time == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(time);
    }


    /**
     * 获取指定天的24小时(0-23)  |  yyyy-MM-dd HH 格式
     *
     * @param t 开始月
     * @return
     */
    public static List<String> getDay24Hour(LocalDateTime t) {
        if (t == null) {
            return new ArrayList<>();
        }
        List<String> times = new ArrayList<>();
        String time = parse(t, YYYY_MM_DD);
        int hourNum = 24;
        for (int i = 0; i < hourNum; i++) {
            if (i < 10) {
                times.add(time + " 0" + i);
            } else {
                times.add(time + " " + i);
            }
        }
        return times;
    }


    /**
     * 获取每一天的时间 (指定时间 前n月前的第一天 到 n月后的最后一天的所有时间)
     * <P>  一天一条数据 List<DateDays>  </P>
     *
     * @param startNum 前n月，当前月开始为 0
     * @param endNum   后n月，当前月就是为 0
     * @return java.util.List<com.lplb.common.utils.LocalDateTimeUtil.DateDays>
     * @author wangsong
     */
    public static List<DateDays> getBetweenDaysUpListByMonth(LocalDateTime time, Integer startNum, Integer endNum) {
        // 本月第一天  00:00:00
        LocalDateTime startTime = monthFirstDay(time, startNum);
        // n月后的最后一天 23:59:59.999
        LocalDateTime endTime = monthLastDay(time, endNum);
        return getBetweenDaysUpList(startTime, endTime, BETWEEN_TYPE_ONE);
    }


    /**
     * 获取每一天的时间 (指定开始时间和结束时间)
     * <p>
     * 一天一条数据 List<DateDays>
     * 返回数据包括 开始时间 和 结束时间 的当天数据
     * </P>
     *
     * @param startTime 开始时间 (时分秒已开始时间位为准)
     * @param endTime   结束时间
     * @param type      1-包含开始和结束时间  2-包含结束-不包含开始时间  3-包含开始-不包含结束时间  4-不包含开始和结束时间
     * @return java.util.List<com.lplb.common.utils.LocalDateTimeUtil.DateDays>
     * @author wangsong
     */
    public static List<DateDays> getBetweenDaysUpList(LocalDateTime startTime, LocalDateTime endTime, Integer type) {
        List<DateDays> dateDaysList = new ArrayList<>();
        List<LocalDateTime> betweenList = getBetweenDaysList(startTime, endTime, type);
        for (LocalDateTime localDateTime : betweenList) {
            dateDaysList.add(new DateDays(localDateTime, week(localDateTime)));
        }
        return dateDaysList;
    }


    /**
     * 获取指定开始时间到指定结束时间的每一天, 包括开始时间，不包括结束时间，如：2020-5-16到2020-5-18 获得时间为：[2020-5-16,2020-5-17]
     *
     * @param startTime
     * @param endTime
     * @param type      1-包含开始和结束时间  2-包含结束-不包含开始时间  3-包含开始-不包含结束时间  4-不包含开始和结束时间
     * @return java.util.List<java.time.LocalDateTime>
     * @author wangsong
     * @date 2020/12/24 0024 15:16
     * @version 1.0.1
     */
    public static List<LocalDateTime> getBetweenDaysList(LocalDateTime startTime, LocalDateTime endTime, Integer type) {
        // 指定开始时间  00:00:00  // 指定结束时间  00:00:00
        LocalDateTime oldStartTime = getDayStart(startTime);
        LocalDateTime oldEndTime = getDayStart(endTime);
        // 1-包含开始和结束时间(默认) BetweenType
        // 2-包含结束-不包含开始时间   // 开始时间+1天
        // 3-包含开始-不包含结束时间   // 结束时间-1天
        // 4-不包含开始和结束时间 // 开始时间+1天  or 结束时间-1天
        if (type == BETWEEN_TYPE_TWO) {
            oldStartTime = plus(oldStartTime, 1, ChronoUnit.DAYS);
        } else if (type == BETWEEN_TYPE_THREE) {
            oldEndTime = subtract(endTime, 1, ChronoUnit.DAYS);
        } else if (type == BETWEEN_TYPE_FOUR) {
            oldStartTime = plus(oldStartTime, 1, ChronoUnit.DAYS);
            oldEndTime = subtract(endTime, 1, ChronoUnit.DAYS);
        }
        // 返回数据
        List<LocalDateTime> everyDays = new ArrayList<>();
        // 第一天数据
        everyDays.add(oldStartTime);
        while (true) {
            // 获取之后的每一天时间
            LocalDateTime nextDay = plus(everyDays.get(everyDays.size() - 1), 1, ChronoUnit.DAYS);
            // 大于最后一天-跳出循环
            if (isAfter(nextDay, oldEndTime)) {
                break;
            }
            everyDays.add(nextDay);
        }
        return everyDays;
    }


    /**
     * 获取月 (返回每一个月的字串， yyyy-MM 格式)
     * <p> 包含结束月，不包含开始月 </>
     *
     * @param startTime 开始月
     * @param endTime   结束月
     * @return
     */
    public static List<String> getBetweenMonthsList(LocalDateTime startTime, LocalDateTime endTime) {
        List<String> times = new ArrayList<>();
        if (startTime != null && endTime != null) {
            // 获取开始月的第一天
            endTime = monthFirstDay(endTime, 0);
            times.add(parse(startTime, YYYY_MM));
            while (isBefore(startTime, endTime)) {
                startTime = plus(startTime, 1, ChronoUnit.MONTHS);
                times.add(parse(startTime, YYYY_MM));
            }
        }
        return times;
    }


    /**
     * 获取日期端的数据保存对象
     *
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/5/7 0007 9:41
     */
    public static class DateDays {
        // 当天时间- 年月日/00:00:00
        private LocalDateTime dayTime;
        // 当天是周几
        private int week;

        public DateDays(LocalDateTime dayTime, int week) {
            this.dayTime = dayTime;
            this.week = week;
        }

        public LocalDateTime getDayTime() {
            return dayTime;
        }

        public void setDayTime(LocalDateTime dayTime) {
            this.dayTime = dayTime;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }
    }

    /**
     * 计算两个日期之间的天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 从开始日期到结束日期（包括这两个日期）之间的总天数
     */
    public static long betweenDays(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();

        while (!startDate.isEqual(endDate)) {
            dates.add(startDate);
            startDate = startDate.plusDays(1);
        }

        dates.add(endDate);

        long totalDays = dates.size();

        return totalDays;
    }

    /**
     * 计算两个日期之间的工作日数，移除了星期六和星期日
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 两个日期之间的工作日数
     */
    public static long removeWeekends(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();

        while (!startDate.isEqual(endDate)) {
            dates.add(startDate);
            startDate = startDate.plusDays(1);
        }

        dates.add(endDate);

        long totalDays = dates.size();
        long weekends = 0;

        for (LocalDate date : dates) {
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                weekends++;
            }
        }

        return totalDays - weekends;
    }

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2024, 8, 22);
        LocalDate endDate = LocalDate.of(2024, 8, 26);

        long daysBetween = betweenDays(startDate, endDate);
        System.out.println("Total days between: " + daysBetween);

        long weekendsRemoved = removeWeekends(startDate, endDate);
        System.out.println("Days without weekends: " + weekendsRemoved);
    }
}
