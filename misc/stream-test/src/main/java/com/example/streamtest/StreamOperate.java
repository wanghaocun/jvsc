package com.example.streamtest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * 将筛选条件封装成接口
     *
     * @param apples
     * @param filter
     * @return
     */
    public static List<Apple> filterApplesByAppleFilter(List<Apple> apples, AppleFilter filter) {
        List<Apple> filterApples = new ArrayList<>();
        for (final Apple apple : apples) {
            if (filter.accept(apple)) {
                filterApples.add(apple);
            }
        }

        return filterApples;
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

        //Stream.generate(()->UUID.randomUUID().toString()).toList().forEach(System.out::println);

        List<Apple> apples = Arrays.asList(
                Apple.builder().id(1).color(Apple.Color.GREEN).origin("JS1").weight(1.5f).build(),
                Apple.builder().id(2).color(Apple.Color.RED).origin("JS2").weight(2.5f).build(),
                Apple.builder().id(3).color(Apple.Color.YELLOW).origin("JS3").weight(3.5f).build()
        );

        List<Apple> apples1 = filterApplesByAppleFilter(apples,
                apple -> apple.getColor().equals(Apple.Color.GREEN)&&apple.getOrigin().equals("js"));
        System.out.println(apples1);

    }

}
