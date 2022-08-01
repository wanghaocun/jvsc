package com.example.javacore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wanghc
 **/
public class DateTimeOperation {

    public static void main(String[] args) throws ParseException {
        //获取当前时间
        LocalDate localDate = LocalDate.now();
        //指定时间，注意，如果使用下面的这种获取方式，一定要注意必须为严格的yy-mm-dd,9月必须为09,1号必须为01，否则会报错
        LocalDate localDate1 = LocalDate.parse("2019-01-05");
        //获取两个日期的天数差，为前一个减去后一个，正数则为前面的日期较晚
        // 3 年
        System.out.println(localDate.compareTo(localDate1));

        LocalDate localDate2 = LocalDate.of(2022, 1, 1);
        // 5 月
        System.out.println(localDate.compareTo(localDate2));

        LocalDate localDate3 = LocalDate.of(2022, 6, 1);
        // 12 天
        System.out.println(localDate.compareTo(localDate3));

        //获取秒数
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(second);
        //获取毫秒数
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(milliSecond);

        //时间转字符串格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String dateTime = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
        System.out.println(dateTime);
        //字符串转时间
        String dateTimeStr = "2018-07-28 14:11:15";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeStr, df);
        System.out.println(dateTime1);

        /*
         * yyyy：年
         * MM：月
         * dd：日
         * hh：1~12小时制(1-12)
         * HH：24小时制(0-23)
         * mm：分
         * ss：秒
         * S：毫秒
         * E：星期几
         * D：一年中的第几天
         * F：一月中的第几个星期(会把这个月总共过的天数除以7)
         * w：一年中的第几个星期
         * W：一月中的第几星期(会根据实际情况来算)
         * a：上下午标识
         * k：和HH差不多，表示一天24小时制(1-24)。
         * K：和hh差不多，表示一天12小时制(0-11)。
         * z：表示时区
         */
        String sb = "yyyy年MM月dd日 HH:mm:ss" +
                " 上下午标志 a" +
                " E" +
                " 一年中的第D天" +
                // F这个出来的结果，不靠谱 还是后面的那个W靠谱。
                " 一月中的第F个星期" +
                " 一年中的第w个星期" +
                " 一月中的第W个星期" +
                " Z" +
                " z";
        SimpleDateFormat sdf = new SimpleDateFormat(sb);
        String dateString = sdf.format(new Date());
        System.out.println(dateString);


        // 测试转换是否正确
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter1.parse("2018-08-01 21:22:22");
        //将java.util.Date 转换为java8 的java.time.LocalDateTime,默认时区为东8区
        LocalDateTime convertLocalDateTime = date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        //将java8 的 java.time.LocalDateTime 转换为 java.util.Date，默认时区为东8区
        Date convertDate = Date.from(convertLocalDateTime.toInstant(ZoneOffset.of("+8")));
        Long localDateTimeSecond = convertLocalDateTime.toEpochSecond(ZoneOffset.of("+8"));
        Long dateSecond = date.toInstant().atOffset(ZoneOffset.of("+8")).toEpochSecond();
        System.out.println(dateSecond.equals(localDateTimeSecond));

        Date ss = new Date();
        System.out.println("一般日期输出：" + ss);
        System.out.println("时间戳：" + ss.getTime());
        Date aw = Calendar.getInstance().getTime();//获得时间的另一种方式，测试效果一样
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(ss.getTime());//这个就是把时间戳经过处理得到期望格式的时间
        System.out.println("格式化结果0：" + time);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        time = format1.format(ss.getTime());
        System.out.println("格式化结果1：" + time);

        // UTC时间转换为本地时间
        String str = "2022-08-01T07:35:23.310145928Z";
        DateTimeFormatter utcTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");
        DateTimeFormatter commonFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(str, utcTimeFormatter);
        System.out.println(localDateTime);
        System.out.println(commonFormatter.format(localDateTime.plus(Duration.ofHours(8))));
    }

}
