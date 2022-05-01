package com.example.streamtest;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

        // 无限生成 Stream是懒加载的 直到被使用时才会执行
        Stream<String> generate = Stream.generate(() -> UUID.randomUUID().toString()).limit(5);
        generate.forEach(System.out::println);
        Stream<Double> generate2 = Stream.generate(Math::random).limit(5);
        System.out.println(generate2.collect(Collectors.toList()));

        List<Apple> apples = Arrays.asList(
                Apple.builder().id(1).color(Apple.Color.GREEN).origin("JS1").weight(1.5f).build(),
                Apple.builder().id(2).color(Apple.Color.RED).origin("JS2").weight(2.5f).build(),
                Apple.builder().id(3).color(Apple.Color.YELLOW).origin("JS3").weight(3.5f).build()
        );

        List<Apple> apples1 = filterApplesByAppleFilter(apples,
                apple -> apple.getColor().equals(Apple.Color.GREEN) && apple.getOrigin().equals("JS1"));
        System.out.println(apples1);

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // 不执行
        Arrays.stream(arr).peek(System.out::println);
        System.out.println(IntStream.of(1, 2, 3, 4)
                .filter(e -> e > 2)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(e -> e * e)
                .peek(e -> System.out.println("Mapped value: " + e))
                .sum());


        List<Integer> numbers3 = Arrays.asList(1, 2, 3, 4, 5);

        Stream<Integer> stream = numbers3.stream().map(n -> n / 0).filter(n -> n % 2 == 0);

        // Internal iteration, using new default method Iterable#forEach(Consumer<? super T> action)
        List<String> list =
                Arrays.asList("Apple", "Orange", "Banana");
        //using lambda expression
        list.forEach(s -> System.out.println(s));
        //or using method reference on System.out instance
        list.forEach(System.out::println);

        // Counting even numbers in a list, using Collection#stream() and java.util.stream.Stream
        List<Integer> list2 =
                Arrays.asList(3, 2, 12, 5, 6, 11, 13);
        long count = list2.stream()
                .filter(i -> i % 2 == 0)
                .count();
        System.out.println(count);

        // Retrieving even number list
        List<Integer> list3 =
                Arrays.asList(3, 2, 12, 5, 6, 11, 13);
        List<Integer> evenList =
                list3.stream()
                        .filter(i -> i % 2 == 0)
                        .collect(Collectors.toList());
        System.out.println(evenList);
        // Or if we are only interested in printing:
        List<Integer> list4 =
                Arrays.asList(3, 2, 12, 5, 6, 11, 13);
        list4.stream().filter(i -> i % 2 == 0)
                .forEach(System.out::println);

        // Finding sum of all even numbers
        List<Integer> list5 =
                Arrays.asList(3, 2, 12, 5, 6, 11, 13);
        int sum = list5.stream()
                .filter(i -> i % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);
        // Alternatively
        List<Integer> list6 =
                Arrays.asList(3, 2, 12, 5, 6, 11, 13);
        int sum2 = list6.stream()
                .filter(i -> i % 2 == 0)
                .reduce(0, (i, c) -> i + c);
        System.out.println(sum2);

        // Finding whether all integers are less than 10 in the list
        // Also look at Stream#anyMatch(...) method
        List<Integer> list7 =
                Arrays.asList(3, 2, 12, 5, 6, 11, 13);
        boolean b = list7.stream()
                .allMatch(i -> i < 10);
        System.out.println(b);

        // Finding all sub-directory names in a directory. Using new static methods, Arrays#stream(T[] array)
        List<String> allDirNames =
                Arrays.stream(new File("C:\\")
                                .listFiles())
                        .filter(File::isDirectory)
                        .map(File::getName)
                        .collect(Collectors.toList());
        System.out.println(allDirNames);
    }

}
