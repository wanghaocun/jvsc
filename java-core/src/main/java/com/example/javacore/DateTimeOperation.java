package com.example.javacore;

import java.time.LocalDate;

/**
 * @author wanghc
 **/
public class DateTimeOperation {

    public static void main(String[] args) {
        //获取当前时间
        LocalDate localDate=LocalDate.now();
        //指定时间，注意，如果使用下面的这种获取方式，一定要注意必须为严格的yy-mm-dd,9月必须为09,1号必须为01，否则会报错
        LocalDate localDate1=LocalDate.parse("2019-01-05");
        //获取两个日期的天数差，为前一个减去后一个，正数则为前面的日期较晚
        // 3 年
        System.out.println(localDate.compareTo(localDate1));

        LocalDate localDate2 = LocalDate.of(2022, 1, 1);
        // 5 月
        System.out.println(localDate.compareTo(localDate2));

        LocalDate localDate3 = LocalDate.of(2022, 6, 1);
        // 12 天
        System.out.println(localDate.compareTo(localDate3));
    }

}
