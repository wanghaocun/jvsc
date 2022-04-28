package com.example.streamtest;

import java.util.*;
import java.util.stream.Collectors;

public class StreamOperate {

    /**
     * Stream.max(Integer::max)求取最大值
     */
    private static void max(List<Integer> numbers) {
        // 取最大值  最小值同理
        System.out.println(numbers.stream().max(Comparator.naturalOrder()).get());
        Optional<Integer> reduce = numbers.stream().reduce(Integer::max);
        System.out.println(reduce.orElse(0));
        OptionalInt max = numbers.stream().mapToInt(Integer::intValue).max();
        System.out.println(max.orElse(0));
        System.out.println(numbers.stream().mapToInt(Integer::intValue).max().getAsInt());
        System.out.println(numbers.stream().mapToInt(i -> i).max().getAsInt());

    }

    private static void summaryStatistics(List<Integer> numbers) {
        // 概要统计
        IntSummaryStatistics intSummaryStatistics = numbers.stream().mapToInt(Integer::intValue).summaryStatistics();
        System.out.println("列表中最大的数 : " + intSummaryStatistics.getMax());
        System.out.println("列表中最小的数 : " + intSummaryStatistics.getMin());
        System.out.println("所有数之和 : " + intSummaryStatistics.getSum());
        System.out.println("平均数 : " + intSummaryStatistics.getAverage());
        System.out.println("平均数 : " + intSummaryStatistics.getCount());
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-1, 2, 3, 4, 5);

        max(numbers);
        summaryStatistics(numbers);

        String[] strs = {"java8", "is", "easy", "to", "use"};

        // map和flatmap
        List<String[]> collect = Arrays.stream(strs).map(s -> s.split("")).distinct().toList();
        collect.forEach(c -> System.out.println(Arrays.toString(c)));
        System.out.println("======");
        List<String> strings = Arrays.stream(strs).map(s -> s.split("")).flatMap(Arrays::stream).distinct().toList();
        System.out.println(strings);
    }

}
