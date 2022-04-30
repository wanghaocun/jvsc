package com.example.streamtest;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StreamExample {
    public static void main(String[] args) {

        Task task1 = Task.builder().id("1").title("Read Version Control with Git book")
                .type(TaskType.READING).createdOn(LocalDate.of(2015, Month.JULY, 1))
                .tags(Stream.of("git", "reading", "books").collect(Collectors.toSet())).build();
        Task task2 = Task.builder().id("2").title("Read Java 8 Lambdas book")
                .type(TaskType.READING).createdOn(LocalDate.of(2015, Month.JULY, 2))
                .tags(Stream.of("java8", "reading", "books").collect(Collectors.toSet())).build();
        Task task3 = Task.builder().id("3").title("Write a mobile application to store my tasks")
                .type(TaskType.CODING).createdOn(LocalDate.of(2015, Month.JULY, 3))
                .tags(Stream.of("coding", "mobile").collect(Collectors.toSet())).build();
        Task task4 = Task.builder().id("4").title("Write a blog on Java 8 Streams")
                .type(TaskType.WRITING).createdOn(LocalDate.of(2015, Month.JULY, 4))
                .tags(Stream.of("blogging", "writing").collect(Collectors.toSet())).build();
        Task task5 = Task.builder().id("5").title("Read Domain Driven Design book")
                .type(TaskType.READING).createdOn(LocalDate.of(2015, Month.JULY, 5))
                .tags(Stream.of("ddd", "books", "reading").collect(Collectors.toSet())).build();
        List<Task> tasks = Arrays.asList(task1, task2, task3, task4, task5);

        // Example 1: 找出所有READING Task的标题，并按照它们的创建时间排序。
        List<String> readingTaskTitles = tasks.stream()
                .filter(task -> TaskType.READING.equals(task.getType()))
                .sorted(Comparator.comparing(Task::getCreatedOn).reversed())
                .map(Task::getTitle)
                .collect(toList());
        System.out.println(readingTaskTitles);

        // Example 2: 去除重复的tasks
        List<Task> allDistinctTasks = tasks.stream().distinct().toList();
        System.out.println(allDistinctTasks);

        // Example 3: 根据创建时间排序，找出前5个处于reading状态的task
        List<Task> top5 =
                tasks.stream().filter(task -> TaskType.READING.equals(task.getType())).sorted(Comparator.comparing(Task::getCreatedOn)).limit(5).collect(toList());
        System.out.println(top5);
        // 可以像如下代码这样，同时使用skip方法和limit方法来创建某一页。
        // page starts from 0. So to view a second page `page` will be 1 and n will be 5.
        // page从0开始，所以要查看第二页的话 `page`应该为1，n应该为5
        int page = 1;
        int n = 5;
        List<String> readingTaskTitlesPage = tasks.stream().
                filter(task -> task.getType() == TaskType.READING).
                sorted(Comparator.comparing(Task::getCreatedOn).reversed()).
                map(Task::getTitle).
                skip(page * n).
                limit(n).toList();
        System.out.println(readingTaskTitlesPage);

        // Example 4:统计状态为reading的task的数量
        long countAllReadingTasks = tasks.stream().filter(task -> task.getType().equals(TaskType.READING)).count();
        System.out.println(countAllReadingTasks);

        // Example 5: 非重复的列出所有task中的全部标签
        List<String> allDistinctTags = tasks.stream().flatMap(task -> task.getTags().stream()).distinct().toList();
        Set<String> set = tasks.stream().flatMap(task -> task.getTags().stream()).collect(Collectors.toSet());

        System.out.println(allDistinctTags);
        System.out.println(set);

        // Example 6: 检查是否所有reading的task都有book标签
        boolean isAllReadingTasksWithTagBooks =
                tasks.stream().filter(task -> TaskType.READING.equals(task.getType())).allMatch(task -> task.getTags().contains("book"));
        System.out.println(isAllReadingTasksWithTagBooks);
        // 判断所有reading的task中是否存在一个task包含java8标签
        boolean isAnyReadingTasksWithTagJava8 =
                tasks.stream().filter(task -> TaskType.READING.equals(task.getType())).anyMatch(task -> task.getTags().contains("java8"));
        System.out.println(isAnyReadingTasksWithTagJava8);

        // Example 7: 创建一个所有title的总览
        String joinAllTaskTitles =
                tasks.stream().map(Task::getTitle).reduce((first, second) -> first + "***" + second).get();
        System.out.println(joinAllTaskTitles);

        // Example 8: 基本类型stream的操作
        //// 创建一个值区间，可以调用range方法。range方法创建一个值为0到9的stream,不包含10。
        IntStream.range(0, 10).forEach(System.out::println);
        //// rangeClosed方法允许我们创建一个包含上限值的stream。因此，下面的代码会产生一个从1到10的stream。
        IntStream.rangeClosed(0, 10).forEach(System.out::println);
        //// 通过在基本类型的stream上使用iterate方法来创建无限的stream：
        LongStream infiniteStream = LongStream.iterate(1, el -> el + 1);
        ////// 要从一个无限的stream中过滤出所有偶数，可以用如下代码来实现:
        infiniteStream.filter(el -> el % 2 == 0).limit(10000).forEach(System.out::println);

        // Example 9: 为数组创建stream
        String[] tags = {"java", "git", "lambda", "machine-learning"};
        Arrays.stream(tags).map(String::toUpperCase).forEach(System.out::println);
        //// 根据数组中特定起始下标和结束下标来创建stream。这里的起始下标包括在内，而结束下标不包含在内
        Arrays.stream(tags, 1, 3).map(String::toUpperCase).forEach(System.out::println);

        // Example 10: List转Map
        Map<String, Task> taskMap = tasks.stream().collect(Collectors.toMap(Task::getId, Function.identity()));
        System.out.println(taskMap);
        //// 转换成map的时候，可能出现key一样的情况，如果不指定一个覆盖规则，上面的代码是会报错的。转成map的时候，最好使用下面的方式：
        Map<String, Task> taskMap1 = tasks.stream().collect(Collectors.toMap(Task::getId, Function.identity(),
                (key1, key2) -> key2));
        System.out.println(taskMap1);
        //// 有时候，希望得到的map的值不是对象，而是对象的某个属性，那么可以用下面的方式：
        Map<String, String> taskMap3 = tasks.stream().collect(Collectors.toMap(Task::getId, Task::getTitle,
                (key1, key2) -> key2));
        System.out.println(taskMap3);

        System.out.println(Integer.MAX_VALUE);
        // 并行流  根据机器的物理核数分配线程数 当数据量不大时 不会分配很多线程
        Map<String, List<Integer>> numbersPerThread = IntStream.rangeClosed(1, 16000000)
                .parallel()
                .boxed()
                .collect(groupingBy(i -> Thread.currentThread().getName()));
        numbersPerThread.forEach((k, v) -> System.out.printf("%s >> %s%n", k, v));
        System.out.println(numbersPerThread.keySet().size());
    }

}
