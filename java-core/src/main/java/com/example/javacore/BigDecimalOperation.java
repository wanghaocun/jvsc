package com.example.javacore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

/**
 * @author wanghc
 **/
public class BigDecimalOperation {

    public static void main(String[] args) {

        BigDecimal b1 = new BigDecimal("0.5");

        // >是1 =是0 <是-1
        System.out.println(new BigDecimal("0.5").compareTo(new BigDecimal("0.5")));

        System.out.println(new BigDecimal("0.5").doubleValue());
        System.out.println(new BigDecimal("0.5").floatValue());
        System.out.println(new BigDecimal("0.5").longValue());
        System.out.println(new BigDecimal("0.5").intValue());

        // 保留两位小数
        double d = 3.1415926;
        // 1.
        // %.2f %.表示小数点前任意位数 2表示两位小数 格式后的结果为f表示浮点型
        System.out.println(String.format("%.2f", d));
        // 2.
        System.out.println(new DecimalFormat("0.00").format(d));
        // 3.
        System.out.println(new DecimalFormat("#.00").format(d));
        // 4.
        System.out.println(new DecimalFormat("#.##").format(d));

        // 计算
        double a = 4887233385.5;
        double b = 0.85;

        System.out.println("result1-->" + a * b);  // result1-->4.1541483776749997E9

        BigDecimal a1 = BigDecimal.valueOf(a);
        BigDecimal b11 = new BigDecimal(b);

        System.out.println("result2-->" + a1.multiply(b11));//result2-->4154148377.674999891481619无限不循环

        BigDecimal aBigDecimal = new BigDecimal(String.valueOf(a));
        BigDecimal bBigDecimal = new BigDecimal(String.valueOf(b));

        // 或者下面这种写法
//		BigDecimal aBigDecimal = new BigDecimal(Double.toString(a));
//		BigDecimal bBigDecimal = new BigDecimal(Double.toString(b));

        System.out.println("result3-->" + aBigDecimal.multiply(bBigDecimal)); //result3-->4154148377.675

        // 四舍五入
        System.out.println(new BigDecimal(d).setScale(2, RoundingMode.HALF_UP));

        // 格式化
        // 由于NumberFormat类的format()方法可以使用BigDecimal对象作为其参数，可以利用BigDecimal对超出16位有效数字的货币值，百分值，以及一般数值进行格式化控制。
        NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
        percent.setMaximumFractionDigits(3); //百分比小数点最多3位

        BigDecimal loanAmount = new BigDecimal("150.48"); //贷款金额
        BigDecimal interestRate = new BigDecimal("0.008"); //利率
        BigDecimal interest = loanAmount.multiply(interestRate); //相乘

        System.out.println("贷款金额:\t" + currency.format(loanAmount)); //贷款金额: ￥150.48
        System.out.println("利率:\t" + percent.format(interestRate));  //利率: 0.8%
        System.out.println("利息:\t" + currency.format(interest)); //利息: ￥1.20

        // 格式化
        DecimalFormat df = new DecimalFormat();
        double data = 1234.56789; //格式化之前的数字

        //1、定义要显示的数字的格式（这种方式会四舍五入）
        String style = "0.0";
        df.applyPattern(style);
        System.out.println("1-->" + df.format(data));  //1234.6

        //2、在格式后添加诸如单位等字符
        style = "00000.000 kg";
        df.applyPattern(style);
        System.out.println("2-->" + df.format(data));  //01234.568 kg


        //3、 模式中的"#"表示如果该位存在字符，则显示字符，如果不存在，则不显示。
        style = "##000.000 kg";
        df.applyPattern(style);
        System.out.println("3-->" + df.format(data));  //1234.568 kg

        //4、 模式中的"-"表示输出为负数，要放在最前面
        style = "-000.000";
        df.applyPattern(style);
        System.out.println("4-->" + df.format(data)); //-1234.568


        //5、 模式中的","在数字中添加逗号，方便读数字
        style = "-0,000.0#";
        df.applyPattern(style);
        System.out.println("5-->" + df.format(data));  //5-->-1,234.57


        //6、模式中的"E"表示输出为指数，"E"之前的字符串是底数的格式，
        // "E"之后的是字符串是指数的格式
        style = "0.00E000";
        df.applyPattern(style);
        System.out.println("6-->" + df.format(data));  //6-->1.23E003


        //7、 模式中的"%"表示乘以100并显示为百分数，要放在最后。
        style = "0.00%";
        df.applyPattern(style);
        System.out.println("7-->" + df.format(data));  //7-->123456.79%


        //8、 模式中的"\u2030"表示乘以1000并显示为千分数，要放在最后。
        style = "0.00\u2030";
        //在构造函数中设置数字格式
        DecimalFormat df1 = new DecimalFormat(style);
        //df.applyPattern(style);
        System.out.println("8-->" + df1.format(data));  //8-->1234567.89‰

        // 比较
        // 注意不能使用equals方法来比较大小。
        // 使用BigDecimal的坏处是性能比double和float差，在处理庞大，复杂的运算时尤为明显，因根据实际需求决定使用哪种类型。
        BigDecimal a111 = new BigDecimal("1");
        BigDecimal b111 = new BigDecimal("2");
        BigDecimal c = new BigDecimal("1");
        int result1 = a111.compareTo(b111);
        int result2 = a111.compareTo(c);
        int result3 = b111.compareTo(a111);

        System.out.println(result1);  //-1
        System.out.println(result2);  //0
        System.out.println(result3);  //1

        // 科学计数法
        // 有些项目可能会涉及到从Excel导入数据，但如果Excel里单元格类型为数值，但内容数据太长时（如银行账号），导入时，会默认读取为科学计数法，用以下代码便轻松解决。
        BigDecimal bd = new BigDecimal("3.40256010353E11");
        String result = bd.toPlainString();
        System.out.println(result);  //340256010353

        // java中价格的数字中间有逗号的处理
        StringTokenizer st = new StringTokenizer("123,456,789", ",");
        StringBuffer sb = new StringBuffer();
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
        }
        System.out.println(sb);  //123456789

        // 或者
        String str = "123,456,789";
        str = str.replace(",", "");
        System.out.println(str);  //123456789

        // 精确计算
        System.out.println(b1.add(new BigDecimal("0.1")));
        System.out.println(b1.subtract(new BigDecimal("0.1")));
        System.out.println(b1.multiply(new BigDecimal("0.1")));
        System.out.println(b1.divide(new BigDecimal("0.1"), 2, RoundingMode.HALF_UP));

    }

}
