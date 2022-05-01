**目录**

[Streams](https://glory.blog.csdn.net/article/details/70224131#t0)

[为什么我们需要一种新的数据处理抽象概念?](https://glory.blog.csdn.net/article/details/70224131#t1)

[Java 8之前的数据处理](https://glory.blog.csdn.net/article/details/70224131#t2)

[Java8中的数据处理](https://glory.blog.csdn.net/article/details/70224131#t3)

[为什么说Java8更好](https://glory.blog.csdn.net/article/details/70224131#t4)

[Stream是什么](https://glory.blog.csdn.net/article/details/70224131#t5)

[Stream特点](https://glory.blog.csdn.net/article/details/70224131#t6)

[Collection vs Stream](https://glory.blog.csdn.net/article/details/70224131#t7)

[外迭代(External iteration) vs (内迭代)internal iterationvs](https://glory.blog.csdn.net/article/details/70224131#t8)

[Lazy evaluation懒加载](https://glory.blog.csdn.net/article/details/70224131#t9)

[使用Stream API](https://glory.blog.csdn.net/article/details/70224131#t10)

[源操作](https://glory.blog.csdn.net/article/details/70224131#t11)

[1\. Collection-将集合类对象转换为管道流](https://glory.blog.csdn.net/article/details/70224131#t12)

[2.数组-将数组转换为管道流](https://glory.blog.csdn.net/article/details/70224131#t13)

[3.使用 BufferedReader.lines() 方法，将每行内容转成流](https://glory.blog.csdn.net/article/details/70224131#t14)

[4.使用 Pattern.splitAsStream() 方法，将字符串分隔成流](https://glory.blog.csdn.net/article/details/70224131#t15)

[中间操作（intermediate operation）](https://glory.blog.csdn.net/article/details/70224131#t16)

[中间操作：有状态与无状态](https://glory.blog.csdn.net/article/details/70224131#t17)

[终端操作（terminal operation）](https://glory.blog.csdn.net/article/details/70224131#t18)

[示例类](https://glory.blog.csdn.net/article/details/70224131#t19)

[Example 1: 找出所有READING Task的标题，并按照它们的创建时间排序。](https://glory.blog.csdn.net/article/details/70224131#t20)

[Example 2: 去除重复的tasks](https://glory.blog.csdn.net/article/details/70224131#t21)

[Example 3: 根据创建时间排序，找出前5个处于reading状态的task](https://glory.blog.csdn.net/article/details/70224131#t22)

[Example 4:统计状态为reading的task的数量](https://glory.blog.csdn.net/article/details/70224131#t23)

[Example 5: 非重复的列出所有task中的全部标签](https://glory.blog.csdn.net/article/details/70224131#t24)

[Example 6: 检查是否所有reading的task都有book标签](https://glory.blog.csdn.net/article/details/70224131#t25)

[Example 7: 创建一个所有title的总览](https://glory.blog.csdn.net/article/details/70224131#t26)

[Example 8: 基本类型stream的操作](https://glory.blog.csdn.net/article/details/70224131#t27)

[Example 9: 为数组创建stream](https://glory.blog.csdn.net/article/details/70224131#t28)

[Example 10: List转Map](https://glory.blog.csdn.net/article/details/70224131#t29)

[Example 11: 利用stream流查出符合某个条件的list，并且进行赋值](https://glory.blog.csdn.net/article/details/70224131#t30)

[Example 12: 分页](https://glory.blog.csdn.net/article/details/70224131#t31)

[Example 13: 排序](https://glory.blog.csdn.net/article/details/70224131#t32)

[Parallel Streams并发的stream](https://glory.blog.csdn.net/article/details/70224131#t33)

___

## **Streams**

>        lambda表达式允许我们在不创建新类的情况下传递行为，从而帮助我们写出干净简洁的代码。lambda表达式是一种简单的语法结构，它通过**使用函数式接口来帮助开发者简单明了的传递意图**。
> 
>        **Stream是java8引入的一个重度使用lambda表达式的API**。**Stream使用一种类似用SQL语句从数据库查询数据的直观方式来提供一种对Java集合运算和表达的高阶抽象。**直观意味着开发者在写代码时**只需关注他们想要的结果是什么而无需关注实现结果的具体方式**。这一章节中，我们将介绍为什么我们需要一种新的数据处理API、Collection和Stream的不同之处以及如何将StreamAPI应用到我们的编码中。

## **为什么我们需要一种新的数据处理抽象概念?**

在我看来，主要有两点：

> - `Collection API 不能提供**更高阶的结构来查询数据**，因而开发者不得不为实现大多数琐碎的任务而写一大堆**样板代码**。`
> - `对**集合数据的并行处理**有一定的限制，如何**使用****Java****语言的并发结构、如何高效的处理数据以及如何高效的并发**都需要由程序员自己来思考和实现。`

## **Java 8之前的数据处理**

阅读下面这一段代码，猜猜看它是拿来做什么的。

```java
public class Example1_Java7 {
    public static void main(String[] args) {
        List<Task> tasks = getTasks();
        List<Task> readingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getType() == TaskType.READING) {
                readingTasks.add(task);
            }
        }
        Collections.sort(readingTasks, new Comparator<Task>() {
            @Overridepublic int compare(Task t1, Task t2) {
                return t1.getTitle().length() - t2.getTitle().length();
            }
        });
        for (Task readingTask : readingTasks) {
            System.out.println(readingTask.getTitle());
        }
    }
}
```

       上面这段代码是用来**按照字符串长度的排序打印所有READING类型的task的title**。所有Java开发者每天都会写这样的代码，为了写出这样一个简单的程序，我们不得不写下15行Java代码。然而上面这段代码最大的问题不在于其代码长度，而在于不能清晰传达开发者的意图：**过滤出所有READING的task、按照字符串的长度排序然后生成一个String类型的List**。

## **Java8中的数据处理**

可以像下面这段代码这样，使用java8中的Stream API来实现与上面代码同等的效果。

```java
public class Example1_Stream {
    public static void main(String[] args) {
        List<Task> tasks = getTasks();
        List<String> readingTasks = tasks.stream()
                .filter(task -> task.getType() == TaskType.READING)
                .sorted((t1, t2) -> t1.getTitle().length() - t2.getTitle().length())
                .map(Task::getTitle)
                .collect(Collectors.toList());
        readingTasks.forEach(System.out::println);
    }
}
```

上面这段代码中，形成了一个由多个stream操作组成的管道。

元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。

```
+--------------------+       +------+   +------+   +---+   +-------+
| stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
+--------------------+       +------+   +------+   +---+   +-------+
```

以上的流程转换为 Java 代码为：

> 1. `**stream()** - 通过在类似上面tasks List<Task>的集合源上调用 stream()方法来**创建一个****stream****的管道**。`
> 2. `**filter(Predicate<T>)** - 这个操作用来提取stream中**匹配****predicate****定义规则的元素**。如果你有一个stream，你可以在它上面调用零次或者多次间断的操作。lambda表达式task -> task.getType() == TaskType.READING定义了一个用来过滤出所有READING的task的规则。`
> 3. `**sorted(Comparator<T>):** This operation returns a stream consisting of all the stream elements sorted by the Comparator defined by lambda expression i.e. in the example shown above.此操作返回一个stream，此stream由所有按照lambda表达式定义的Comparator来排序后的stream元素组成,在上面代码中排序的表达式是(t1, t2) -> t1.getTitle().length() - t2.getTitle().length().`
> 4. `**map(Function<T,R>):** 此操作**返回一个****stream**,该stream的每个元素来自原stream的每个元素通过Function<T,R>处理后得到的结果。`
> 5. `**collect(toList())** -此操作把上面对stream进行各种操作后的结果装进一个list中。`

### **为什么说Java8更好**

In my opinion Java 8 code is better because of following reasons:  
在我看来，Java8的代码更好主要有以下几点原因：

> 1. `Java8代码能够清晰地表达开发者**对数据过滤、排序**等操作的意图。`
> 2. `通过**使用****Stream API****格式的更高抽象**，开发者表达他们所想要的是什么而不是怎么去得到这些结果。`
> 3. `**Stream API****为数据处理提供一种统一的语**言，使得开发者在谈论数据处理时有共同的词汇。当两个开发者讨论filter函数时，你都会明白他们都是在进行一个数据过滤操作。`
> 4. `开发者不再需要为实现数据处理而写的各种样板代码，也**不再需要为****loop****代码或者临时集合来储存数据的冗余代码**，Stream API会处理这一切。`
> 5. `**Stream****不会修改潜在的集合，它是非交换的**。`

## **Stream是什么**

       Stream是一个在某些数据上的抽象视图。比如，Stream可以是一个list或者文件中的几行或者其他任意的一个元素序列的视图。Stream API提供可以**顺序表现**或者**并行表现**的操作总和。**_开发者需要明白一点，Stream是一种更高阶的抽象概念，而不是一种数据结构。Stream不会储存数据 ;_** Stream天生就**很懒**，只有在被使用到时才会执行计算。它允许我们产生无限的数据流(stream of data)。在Java8中，你可以像下面这样，非常轻松的写出一个**无限制生成特定标识符的代码**：

```java
public static void main(String[] args) {
    Stream<String> uuidStream = Stream.generate(() -> UUID.randomUUID().toString());
}
```

       在Stream接口中有诸如**`of`、`generate`、`iterate`等多种静态工厂方法可以用来创建stream实例**。上面提到的`generate`方法带有一个`Supplier`，**`Supplier`是一个可以用来描述一个不需要任何输入且会产生一个值的函数的函数式接口**，我们向`generate`方法中传递一个supplier，当它被调用时会生成一个特定标识符。

```java
Supplier<String> uuids = () -> UUID.randomUUID().toString()
```

运行上面这段代码，什么都不会发生，因为**Stream是懒加载的，直到被使用时才会执行**。如果我们改成如下这段代码，我们就会在控制台看到打印出来的UUID。这段程序会一直执行下去。

```java
public static void main(String[] args) {
    Stream<String> uuidStream = Stream.generate(() -> UUID.randomUUID().toString());
    uuidStream.forEach(System.out::println);
}
```

Java8运行开发者通过在一个Collection上调用`stream`方法来创建Stream。Stream支持数据处理操作，从而开发者可以使用更高阶的数据处理结构来表达运算。

## **Stream**特点

1.不是数据结构，不会保存数据

```java
int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
IntStream intStream = Arrays.stream(arr).filter(x -> x > 3);
```

2.不会修改原来的数据源，它会将操作后的数据保存到另外一个对象中（引用类型 基本类型）

```java
int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
Arrays.stream(arr).filter(x -> x > 3).forEach(System.out::println);

//[1, 2, 3, 4, 5, 6, 7, 8, 9]
System.out.println(Arrays.toString(arr));
```

3.惰性求值，流在中间处理过程中，只是对操作进行了记录，并不会立即执行，需要等到执行终止操作的时候才会进行实际的计算。

```java
int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
// 不执行
Arrays.stream(arr).peek(System.out::println);
```

## **Collection vs Stream**

`下面这张表阐述了Collection和Stream的不同之处`

![](https://img-blog.csdn.net/20180731110648602?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ZseTkxMDkwNQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

Collection和Stream的不同之处

下面我们来探讨**内迭代(internal iteration)和外迭代(external iteration)的区别，以及懒赋值**的概念。

### **外迭代(External iteration) vs (内迭代)internal iterationvs**

   上面谈到的**Java8 Stream API代码和Collection API代码的区别**在于**由谁来控制迭代，是迭代器本身还是开发者**。Stream API仅仅提供他们想要实现的操作，然后迭代器把这些操作应用到潜在Collection的每个元素中去。当对潜在的Collection进行的迭代操作是由迭代器本身控制时，就叫着**`内迭代`**；反之，当迭代操作是由开发者控制时，就叫着**`外迭代`**。Collection API中`for-each`结构的使用就是一个`外迭代`的例子。

   有人会说，在Collection API中我们也不需要对潜在的迭代器进行操作，因为`for-each`结构已经替我们处理得很好了，但是`for-each`结构其实不过是一种iterator API的语法糖罢了。

```
for-each尽管很简单，但是它有一些缺点 

1)只有固有顺序 
2)容易写出生硬的命令式代码（imperative code） 
3)难以并行。
```

### **Lazy evaluation懒加载**

       stream表达式在被终极操作方法调用之前不会被赋值计算。Stream API中的大多数操作会返回一个Stream。这些操作不会做任何的执行操作，它们只会构建这个管道。看着下面这段代码，预测一下它的输出会是什么。

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

Stream<Integer> stream = numbers.stream().map(n -> n / 0).filter(n -> n % 2 == 0);
```

       上面这段代码中，我们将stream元素中的数字除以0，我们也许**会认为这段代码在运行时会抛出`ArithmeticExceptin`异常**，而**事实上不会**。因为stream表达式只有在有终极操作被调用时才会被执行运算。**如果我们为上面的stream加上终极操作，stream就会被执行并抛出异常。**

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
Stream<Integer> stream = numbers.stream().map(n -> n / 0).filter(n -> n % 2 == 0);
stream.collect(toList());
```

我们会得到如下的stack trace:

```java
Exception in thread "main" java.lang.ArithmeticException: / by zero
    at org._7dayswithx.java8.day2.EagerEvaluationExample.lambda$main$0(EagerEvaluationExample.java:13)
    at org._7dayswithx.java8.day2.EagerEvaluationExample$$Lambda$1/1915318863.apply(Unknown Source)
    at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:193)
    at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
    at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:512)
    at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:502)
    at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)
    at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:499)
```

## **使用Stream API**

![](https://img-blog.csdnimg.cn/img_convert/51ecd30ed6218f89e78933e7cc9a401f.png)

Java Stream API

> `Stream API提供了一大堆开发者可以用来从集合中查询数据的操作，这些操作分为两种--**过渡操作和终极操作**。`
> 
> - 源操作：可以将数组、集合类、行文本文件转换成管道流Stream进行数据处理
> - 中间操作（intermediate operation）：对Stream流中的数据进行处理，比如：过滤、数据转换等等`。从已存在的stream上产生另一个新的stream的函数，比如filter,map, sorted,等。`
> - 终端操作（terminal operation）：作用就是将Stream管道流转换为其他的数据类型。这部分我们还没有讲，我们后面章节再介绍。

## 源操作

> [Stream](https://so.csdn.net/so/search?q=Stream&spm=1001.2101.3001.7020)流对象获取的方式

### 1\. Collection-将[集合](https://so.csdn.net/so/search?q=%E9%9B%86%E5%90%88&spm=1001.2101.3001.7020)类对象转换为管道流

> Stream stream()  
> 调用stream()方法,返回的是Stream类型的对象
> 
>  Stream parallelStream()  
> 调用并发流

### 2.数组-将数组转换为管道流

> Stream<Integer> stream = Arrays.stream(数组);     
> Stream.of(数组),返回Stream类型的对象      
> 
> Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(6);  
> Stream<Double> stream3 = Stream.generate(Math::random).limit(2);

### 3.使用 BufferedReader.lines() 方法，将每行内容转成流

> BufferedReader reader = new BufferedReader(new FileReader("F:\\\\test\_stream.txt"));  
> Stream<String> lineStream = reader.lines();

### 4.使用 Pattern.splitAsStream() 方法，将[字符串](https://so.csdn.net/so/search?q=%E5%AD%97%E7%AC%A6%E4%B8%B2&spm=1001.2101.3001.7020)分隔成流

> Pattern pattern = Pattern.compile(",");  
> Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");

![](https://img-blog.csdnimg.cn/20210413103220222.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ZseTkxMDkwNQ==,size_16,color_FFFFFF,t_70)

中间操作（intermediate operation）

| 流方法      | 含义                                                                                                                                                                                                                                                       | 集合                                                                                      | 示例                                                                                                         |
| -------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| filter   | 用于通过设置的条件过滤出元素                                                                                                                                                                                                                                           | List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");         | List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList()); |
| distinct | 返回一个元素各异（根据流所生成元素的hashCode和equals方法实现）的流。                                                                                                                                                                                                                | List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);                             | numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);                          |
| limit    | 会返回一个不超过给定长度的流。                                                                                                                                                                                                                                          | List<String> strings = Arrays.asList("abc", "abc", "bc", "efg", "abcd","jkl", "jkl");   | List<String> limited = strings.stream().limit(3).collect(Collectors.toList());                             |
| skip     | 返回一个扔掉了前n个元素的流。                                                                                                                                                                                                                                          | List<String> strings = Arrays.asList("abc", "abc", "bc", "efg", "abcd","jkl", "jkl");   | List<String> skiped = strings.stream().skip(3).collect(Collectors.toList());                               |
| map      | 接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素（使用映射一词，是因为它和转换类似，但其中的细微差别在于它是“创建一个新版本”而不是去“修改”）。Returns a stream consisting of the results of applying the given function to the elements of this stream.返回由新元素组成的Stream。<br><br>- mapToInt、mapToLong、mapToDouble | List<String> strings = Arrays.asList("abc", "abc", "bc", "efg", "abcd","jkl", "jkl");   | List<String> mapped = strings.stream().map(str->str+"-itcast").collect(Collectors.toList());               |
| flatMap  | 使用flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。所有使用map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流。简单的说，就是一个或多个流合并成一个新流。<br><br>- flatMapToInt、flatMapToLong、flatMapToDouble                                                                                        | List<String> strings = Arrays.asList("abc", "abc", "bc", "efg", "abcd","jkl", "jkl");   | Stream<Character> flatMap = strings.stream().flatMap(Java8StreamTest::getCharacterByString);               |
| sorted   | 返回排序后的流                                                                                                                                                                                                                                                  | List<String> strings1 = Arrays.asList("abc", "abd", "aba", "efg", "abcd","jkl", "jkl"); | List<String> sorted1 = strings1.stream().sorted().collect(Collectors.toList());                            |

### 中间操作：有状态与无状态

其实在程序员编程中，经常会接触到“有状态”，“无状态”，绝大部分的人都比较蒙。而且在不同的场景下，“状态”这个词的含义似乎有所不同。但是“万变不离其宗”，理解“状态”这个词在编程领域的含义，笔者教给大家几个关键点：

- 状态通常代表公用数据，有状态就是有“公用数据”
- 因为有公用的数据，状态通常需要额外的存储。
- 状态通常被多人、多用户、多线程、多次操作，这就涉及到状态的管理及变更操作。

回到我们的Stream管道流

- filter与map操作，不需要管道流的前面后面元素相关，所以不需要额外的记录元素之间的关系。输入一个元素，获得一个结果。
- sorted是排序操作、distinct是去重操作。像这种操作都是和别的元素相关的操作，我自己无法完成整体操作。就像班级点名就是无状态的，喊到你你就答到就可以了。如果是班级同学按大小个排序，那就不是你自己的事了，你得和周围的同学比一下身高并记住，你记住的这个身高比较结果就是一种“状态”。所以这种操作就是有状态操作。

![](https://img-blog.csdnimg.cn/20210413110611580.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ZseTkxMDkwNQ==,size_16,color_FFFFFF,t_70)

中间操作：有状态与无状态

> - 无状态：指元素的处理不受之前元素的影响；
> - 有状态：指该操作只有拿到所有元素之后才能继续下去。
> - 非短路操作：指必须处理所有元素才能得到最终结果；
> - 短路操作：指遇到某些符合条件的元素就可以得到最终结果，如 A || B，只要A为true，则无需判断B的结果。

## 终端操作（terminal operation）

> 终止操作符(终止操作符只能使用一次)

![](https://img-blog.csdnimg.cn/2021041310340124.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ZseTkxMDkwNQ==,size_16,color_FFFFFF,t_70)

终端操作（terminal operation）

| 流方法         | 含义                      | 集合                                                                                     | 示例                                                                                                                                                                                                                                                      |
| ----------- | ----------------------- | -------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| anyMatch    | 检查是否至少匹配一个元素，返回boolean。 | List<String> strings = Arrays.asList("abc", "abd", "aba", "efg", "abcd","jkl", "jkl"); | boolean b = strings.stream().anyMatch(s -> s == "abc");                                                                                                                                                                                                 |
| allMatch    | 检查是否匹配所有元素，返回boolean。   | List<String> strings = Arrays.asList("abc", "abd", "aba", "efg", "abcd","jkl", "jkl"); | boolean b = strings.stream().allMatch(s -> s == "abc");                                                                                                                                                                                                 |
| noneMatch   | 检查是否没有匹配所有元素，返回boolean。 | List<String> strings = Arrays.asList("abc", "abd", "aba", "efg", "abcd","jkl", "jkl"); | boolean b = strings.stream().noneMatch(s -> s == "abc");                                                                                                                                                                                                |
| findAny     | 将返回当前流中的任意元素。           | List<String> strings = Arrays.asList("cv", "abd", "aba", "efg", "abcd","jkl", "jkl");  | Optional<String> any = strings.stream().findAny();                                                                                                                                                                                                      |
| findFirst   | 返回第一个元素                 | List<String> strings = Arrays.asList("cv", "abd", "aba", "efg", "abcd","jkl", "jkl");  | Optional<String> first = strings.stream().findFirst();                                                                                                                                                                                                  |
| forEach     | 遍历流                     | List<String> strings = Arrays.asList("cv", "abd", "aba", "efg", "abcd","jkl", "jkl");  | strings.stream().forEach(s -> out.println(s));                                                                                                                                                                                                          |
| **collect** | 收集器，将流转换为其他形式。          | List<String> strings = Arrays.asList("cv", "abd", "aba", "efg", "abcd","jkl", "jkl");  | Set<String> set = strings.stream().collect(Collectors.toSet());List<String> list = strings.stream().collect(Collectors.toList());Map<String, String> map = strings.stream().collect(Collectors.toMap(v ->v.concat("_name"), v1 -> v1, (v1, v2) -> v1)); |
| reduce      | 可以将流中元素反复结合起来，得到一个值。    | List<String> strings = Arrays.asList("cv", "abd", "aba", "efg", "abcd","jkl", "jkl");  | Optional<String> reduce = strings.stream().reduce((acc,item) -> {return acc+item;}); if(reduce.isPresent())System.out.println(reduce.get());                                                                                                            |
| count       | 返回流中元素总数。               | List<String> strings = Arrays.asList("cv", "abd", "aba", "efg", "abcd","jkl", "jkl");  | long count = strings.stream().count();                                                                                                                                                                                                                  |

下面是Stream API的部分函数列表：

![](https://img-blog.csdn.net/20180731111308411?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ZseTkxMDkwNQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

## **示例类**

       在本教程中，我们将会用Task管理类来解释这些概念。例子中，有一个叫Task的类，它是一个由用户来表现的类，其定义如下：

```java
import java.time.LocalDate;
import java.util.*;
public class Task {
    private final String id;
    private final String title;
    private final TaskType type;
    private final LocalDate createdOn;
    private boolean done = false;
    private Set<String> tags = new HashSet<>();
    private LocalDate dueOn;
    // removed constructor, getter, and setter for brevity
}
```

例子中的数据集如下，在整个Stream API例子中我们都会用到它。

```java
Task task1 = new Task("Read Version Control with Git book", TaskType.READING, LocalDate.of(2015, Month.JULY, 1)).addTag("git").addTag("reading").addTag("books");
Task task2 = new Task("Read Java 8 Lambdas book", TaskType.READING, LocalDate.of(2015, Month.JULY, 2)).addTag("java8").addTag("reading").addTag("books");
Task task3 = new Task("Write a mobile application to store my tasks", TaskType.CODING, LocalDate.of(2015, Month.JULY, 3)).addTag("coding").addTag("mobile");
Task task4 = new Task("Write a blog on Java 8 Streams", TaskType.WRITING, LocalDate.of(2015, Month.JULY, 4)).addTag("blogging").addTag("writing").addTag("streams");
Task task5 = new Task("Read Domain Driven Design book", TaskType.READING, LocalDate.of(2015, Month.JULY, 5)).addTag("ddd").addTag("books").addTag("reading");
List<Task> tasks = Arrays.asList(task1, task2, task3, task4, task5);
```

### **Example 1: 找出所有READING Task的标题，并按照它们的创建时间排序。**

第一个例子我们将要实现的是，从Task列表中找出所有正在阅读的任务的标题，并根据它们的创建时间排序。我们要做的操作如下：

1. `过滤出所有TaskType为READING的Task。`
2. `按照创建时间对task进行排序。`
3. `获取每个task的title。`
4. `将得到的这些title装进一个List中。`

上面的四个操作步骤可以非常简单的翻译成下面这段代码：

```java
private static List<String> allReadingTasks(List<Task> tasks) {
        List<String> readingTaskTitles = tasks.stream().
                filter(task -> task.getType() == TaskType.READING).
                sorted((t1, t2) -> t1.getCreatedOn().compareTo(t2.getCreatedOn())).
                map(task -> task.getTitle()).
                collect(Collectors.toList());
        return readingTaskTitles;
}
```

在上面的代码中，我们使用了Stream API中如下的一些方法：

- **filter**:允许开发者定义一个判断规则来从潜在的stream中提取符合此规则的部分元素。规则**task -> task.getType() == TaskType.READING**意为从stream中选取所有TaskType 为READING的元素。

- **sorted**: 允许开发者定义一个比较器来排序stream。上例中，我们根据创建时间来排序，其中的[lambda](https://so.csdn.net/so/search?q=lambda&spm=1001.2101.3001.7020)表达式**(t1, t2) -> t1.getCreatedOn().compareTo(t2.getCreatedOn())**就对[函数式接口](https://so.csdn.net/so/search?q=%E5%87%BD%E6%95%B0%E5%BC%8F%E6%8E%A5%E5%8F%A3&spm=1001.2101.3001.7020)Comparator中的`compare`函数进行了实现。

- **map**: **需要一个实现了能够将一个stream转换成另一个stream的`Function<? super T, ? extends R>`的lambda表达式作为参数**，Function<? super T, ? extends R>接口能够将一个stream转换为另一个stream。lambda表达式**task -> task.getTitle()**将一个task转化为标题。

- **collect(toList())** 这是一个终极操作，它将所有READING的Task的标题的装进一个list中。

我们可以通过**使用`Comparator`接口的`comparing`方法和方法引用**来将上面的代码简化成如下代码：

```java
public List<String> allReadingTasks(List<Task> tasks) {
    return tasks.stream().
            filter(task -> task.getType() == TaskType.READING).
            sorted(Comparator.comparing(Task::getCreatedOn)).
            map(Task::getTitle).
            collect(Collectors.toList());
}
```

- `从Java8开始，接口可以含有通过**静态和默认方法**来实现方法。`
- `方法引用Task::getCreatedOn是由Function<Task,LocalDate>而来的。`

       上面代码中，我们使用了**`Comparator`接口中的静态帮助方法`comparing`**，此方法需要接收一个用来提取`Comparable`的`Function`作为参数，返回一个通过key进行比较的`Comparator`。方法引用`Task::getCreatedOn` 是由 `Function<Task, LocalDate>`而来的.

       我们可以像如下代码这样，使用函数组合，**通过在Comparator上调用`reversed()`方法**，来非常轻松的**颠倒排序**。

```java
public List<String> allReadingTasksSortedByCreatedOnDesc(List<Task> tasks) {
    return tasks.stream().
            filter(task -> task.getType() == TaskType.READING).
            sorted(Comparator.comparing(Task::getCreatedOn).reversed()).
            map(Task::getTitle).
            collect(Collectors.toList());
}
```

### **Example 2: 去除重复的tasks**

       假设我们有一个有很多重复task的数据集，可以像如下代码这样通过**调用`distinct`方法**来轻松的去除stream中的重复的元素：

```java
public List<Task> allDistinctTasks(List<Task> tasks) {
    return tasks.stream().distinct().collect(Collectors.toList());
}
```

  `distinct()`方法把一个stream转换成一个不含重复元素的stream,**它通过对象的`equals`方法来判断对象是否相等**。根据对象相等方法的判定，如果两个对象相等就意味着有重复，它就会从结果stream中移除。

### **Example 3: 根据创建时间排序，找出前5个处于reading状态的task**

`limit`方法可以用来把结果集限定在一个给定的数字。`limit`是一个短路操作，意味着它不会为了得到结果而去运算所有元素。

```java
public List<String> topN(List<Task> tasks, int n){
    return tasks.stream().
            filter(task -> task.getType() == TaskType.READING).
            sorted(comparing(Task::getCreatedOn)).
            map(Task::getTitle).
            limit(n).
            collect(toList());
}
```

可以像如下代码这样，同时使用`skip`方法和`limit`方法来创建某一页。

```java
// page starts from 0. So to view a second page `page` will be 1 and n will be 5.//page从0开始，所以要查看第二页的话,`page`应该为1，n应该为5
List<String> readingTaskTitles = tasks.stream().
                filter(task -> task.getType() == TaskType.READING).
                sorted(comparing(Task::getCreatedOn).reversed()).
                map(Task::getTitle).
                skip(page * n).
                limit(n).
                collect(toList());
```

### **Example 4:统计状态为reading的task的数量**

       要得到所有正处于reading的task的数量，我们可以**在stream中使用`count`方法来获得**，这个方法是一个终极方法。

```java
public long countAllReadingTasks(List<Task> tasks) {
    return tasks.stream().
            filter(task -> task.getType() == TaskType.READING).
            count();
}
```

### **Example 5: 非重复的列出所有task中的全部标签**

要找出不重复的标签，我们需要下面几个步骤

1. 获取每个task中的标签。
2. 把所有的标签放到一个stream中。
3. 删除重复的标签。
4. 把最终结果装进一个列表中。

       第一步和第二步可以通过在`stream`上调用`flatMap`来得到。**`flatMap`操作把通过调用`task.getTags().stream`得到的各个stream合成到一个stream**。一旦我们把所有的tag放到一个stream中，我们就可以通过调用`distinct`方法来得到非重复的tag。

```java
private static List<String> allDistinctTags(List<Task> tasks) {
        return tasks.stream().flatMap(task -> task.getTags().stream()).distinct().collect(toList());
}
```

### **Example 6: 检查是否所有reading的task都有`book`标签**

       Stream API有一些可以用来检测数据集中是否含有某个给定属性的方法，`allMatch`,`anyMatch`,`noneMatch`,`findFirst`,`findAny`。要判断是否**所有状态为reading的task的title中都包含**`**books**`**标签**，可以用如下代码来实现:

```java
public boolean isAllReadingTasksWithTagBooks(List<Task> tasks) {
    return tasks.stream().
            filter(task -> task.getType() == TaskType.READING).
            allMatch(task -> task.getTags().contains("books"));
}
```

要判断所有reading的task中**是否存在一个task包含`java8`标签**，可以通过`anyMatch`来实现，代码如下：

```java
public boolean isAnyReadingTasksWithTagJava8(List<Task> tasks) {
    return tasks.stream().
            filter(task -> task.getType() == TaskType.READING).
            anyMatch(task -> task.getTags().contains("java8"));
}
```

### **Example 7: 创建一个所有title的总览**

       当你想要**创建一个所有title的总览时就可以使用`reduce`操作**，`reduce`能够把stream变成成一个值。`reduce`函数接受一个可以用来连接stream中所有元素的lambda表达式。

```java
public String joinAllTaskTitles(List<Task> tasks) {
    return tasks.stream().
            map(Task::getTitle).
            reduce((first, second) -> first + " *** " + second).
            get();
}
```

### **Example 8: 基本类型stream的操作**

除了常见的基于对象的stream，Java8对诸如int,long,double等基本类型也提供了特定的stream。下面一起来看一些基本类型的stream的例子。

**要创建一个值区间，可以调用`range`方法**。`range`方法创建一个值为0到9的stream,不包含10。

```java
IntStream.range(0, 10).forEach(System.out::println);
```

**`rangeClosed`方法允许我们创建一个包含上限值的stream**。因此，下面的代码会产生一个从1到10的stream。

```java
IntStream.rangeClosed(1, 10).forEach(System.out::println);
```

还可以像下面这样，通过在基本类型的stream上使用`iterate`方法来创建无限的stream：

```java
LongStream infiniteStream = LongStream.iterate(1, el -> el + 1);
```

要从一个无限的stream中过滤出所有偶数，可以用如下代码来实现:

```java
infiniteStream.filter(el -> el % 2 == 0).forEach(System.out::println);
```

可以通过**使用`limit`操作来现在结果stream的个数**，代码如下：  
We can limit the resulting stream by using the `limit` operation as shown below.

```java
infiniteStream.filter(el -> el % 2 == 0).limit(100).forEach(System.out::println);
```

### **Example 9: 为数组创建stream**

可以像如下代码这样，通过调用`Arrays`类的静态方法`stream`来把为数组建立stream：

```java
String[] tags = {"java", "git", "lambdas", "machine-learning"};
Arrays.stream(tags).map(String::toUpperCase).forEach(System.out::println);
```

还可以像如下这样，**根据数组中特定起始下标和结束下标来创建stream**。这里的起始下标包括在内，而结束下标不包含在内。

```java
Arrays.stream(tags, 1, 3).map(String::toUpperCase).forEach(System.out::println);
```

### **Example 10:** List转Map

```java
Map<Long, User> maps = userList.stream().collect(Collectors.toMap(User::getId,Function.identity()));
```

看来还是使用JDK 1.8方便一些。另外，**转换成map的时候，可能出现key一样的情况**，如果不指定一个覆盖规则，上面的代码是会报错的。转成map的时候，最好使用下面的方式：

```java
Map<Long, User> maps = userList.stream().collect(Collectors.toMap(User::getId, Function.identity(), (key1, key2) -> key2));
```

有时候，希望得到的map的值不是对象，而是对象的某个属性，那么可以用下面的方式：

```java
Map<Long, String> maps = userList.stream().collect(Collectors.toMap(User::getId, User::getAge, (key1, key2) -> key2));
```

### **Example 11:** 利用stream流查出符合某个条件的list，并且进行赋值

```java
List<WorkHandoverModel>workHandoverList2=workHandoverList.stream().filter(item->item.getIsDelete().equals(0)).collect(Collectors.toList());//获取list中符合没有删除的工作交接的list

List<WorkHandoverModel>workHandoverList1=workHandoverList.stream().filter(item->item.getIsDelete().equals(1)).collect(Collectors.toList());

workHandoverList1.stream().forEach(item->item.setDispathStatus(null));//对查询出来的list统一赋值

workHandoverList1.stream().forEach(item->item.setShowDispathStatus(null));

//workHandoverList1.stream().forEach(item->item.setDispathId(null));

workHandoverList2.addAll(workHandoverList1);
```

## **Example 12:** 分页

> skip：跳过n个元素，limit裁剪大小，currentPage当前页，pageSize当前页大小。

```java
list.stream().skip((currentPage-1)*pageSize).limit(pageSize).collect(Collectors.toList());
```

## **Example 13:** 排序

```java
//升序
list.stream().sorted((v1,v2)->v1.getId().compareTo(
    v2.getId()
)).collect(Collectors.toList());

//降序
list.stream().sorted((v1,v2)->v2getId().compareTo(
    v1.getId()
)).collect(Collectors.toList());

//根据子对象id，升序排序，Student对象中还有一个Boy的对象属性
list.stream().sorted((v1,v2)->v1.getBoy().getbId().compareTo(
    v2.getBoy().getbId()
    )).collect(Collectors.toList());
```

## **Parallel Streams并发的stream**

       使用Stream有一个优势在于，由于stream采用内部迭代，所以java库能够有效的管理处理并发**。可以在一个stream上调用`parallel`方法来使一个stream处于并行。**`parallel`方法的底层实现基于JDK7中引入的`fork-join`API。**默认情况下，它会产生与机器CPU数量相等的线程**。下面的代码中，我们根据处理它们的线程来对将数字分组。在第4节中将学习`collect`和`groupingBy`函数，现在暂时理解为它可以根据一个key来对元素进行分组。

```java
public class ParallelStreamExample {
    public static void main(String[] args) {
        Map<String, List<Integer>> numbersPerThread = IntStream.rangeClosed(1, 160)
                .parallel()
                .boxed()
                .collect(groupingBy(i -> Thread.currentThread().getName()));
        numbersPerThread.forEach((k, v) -> System.out.println(String.format("%s >> %s", k, v)));
    }
}
```

在我的机器上，打印的结果如下：

```
ForkJoinPool.commonPool-worker-7 >> [46, 47, 48, 49, 50]
ForkJoinPool.commonPool-worker-1 >> [41, 42, 43, 44, 45, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130]
ForkJoinPool.commonPool-worker-2 >> [146, 147, 148, 149, 150]
main >> [106, 107, 108, 109, 110]
ForkJoinPool.commonPool-worker-5 >> [71, 72, 73, 74, 75]
ForkJoinPool.commonPool-worker-6 >> [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160]
ForkJoinPool.commonPool-worker-3 >> [21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 76, 77, 78, 79, 80]
ForkJoinPool.commonPool-worker-4 >> [91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145]
```

并不是每个工作的线程都处理相等数量的数字，可以通**过更改系统属性来控制fork-join线程池的数量**

```java
System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2")。
```

另外一个会用到`parallel`操作的例子是，当你像下面这样要处理一个URL的列表时：

```java
String[] urls = {"https://www.google.co.in/", "https://twitter.com/", "http://www.facebook.com/"};
Arrays.stream(urls).parallel().map(url -> getUrlContent(url)).forEach(System.out::println);
```

如果你想更好的掌握什么时候应该使用并发的stream,推荐你阅读由Doug Lea和其他几位Java大牛写的文章[http://gee.cs.oswego.edu/dl/html/StreamParallelGuidance.html](http://gee.cs.oswego.edu/dl/html/StreamParallelGuidance.html)。