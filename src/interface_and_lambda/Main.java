package interface_and_lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

// Exercise 1 (Predicate)
        Integer num1 = 1;
        Integer num2 = -1;

        System.out.println("Anonymous class:");
        Predicate<Integer> predicate1 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer>0;
            }
        };
        System.out.println(predicate1.test(num1));
        System.out.println(predicate1.test(num2));

        Predicate<Integer> predicate2 = x -> x > 0;
        System.out.println("Lambda:");
        System.out.println(predicate2.test(num1));
        System.out.println(predicate2.test(num2));

        System.out.println("_____________");

// Exercise 2 (Consumer)
        String name1 = "Alex";
        String name2 = "John";
        System.out.println("Anonymous class:");
        Consumer <String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Hello, my name is " + s);
            }
        };
        consumer1.accept(name1);
        consumer1.accept(name2);

        System.out.println("Lambda:");
        Consumer<String> consumer2 = s -> System.out.println("Hello, my name is " + s);
        consumer2.accept(name1);
        consumer2.accept(name2);

        System.out.println("__________");

// Exercise 3 (Function)

        Double d1 = 3.14;
        Double d2 = 9.75;

        System.out.println("Anonymous class:");
        Function <Double, Long> function1 = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return aDouble.longValue();
            }
        };
        System.out.println(function1.apply(d1));
        System.out.println(function1.apply(d2));

        System.out.println("Lambda:");
        Function<Double, Long> function2 = a -> a.longValue();
        System.out.println(function2.apply(d1));
        System.out.println(function2.apply(d2));
        System.out.println("__________");

        // Exercise 4 (Supplier)
        System.out.println("Anonymous class:");
        Supplier<Integer> supplier1 = new Supplier<Integer>() {
            @Override
            public Integer get() {
                Integer i = new Random().nextInt(100);
                return i;
            }
        };
        System.out.println(supplier1.get());

        System.out.println("Lambda:");
        Supplier <Integer> supplier2 = () -> {
            Integer i = new Random().nextInt(100);
            return i;
        };
        System.out.println(supplier2.get());
        System.out.println("__________");

// Exercise 5 (TernaryOperator)

        String s1 = "3.14";
        String s2 = "314";

        Predicate<String> condition = s -> s.contains(".");
        Function<String, Double> ifTrue = f -> Double.parseDouble(f);
        Function<String, Integer> ifFalse = f -> Integer.parseInt(f);

        Function<String, Object> ternaryOperator = ternaryOperator(condition, ifTrue, ifFalse);

        System.out.println(ternaryOperator.apply(s1).getClass().getSimpleName() + " - " + ternaryOperator.apply(s1));
        System.out.println(ternaryOperator.apply(s2).getClass().getSimpleName() + " - " + ternaryOperator.apply(s2));

        System.out.println(".....................");


// Homework on the topic "Stream"
// Exercise 1
        Integer [] num = new Integer[10];
        for (int i = 0; i < num.length; i++) {
            num[i] = i;
        }
        Stream<Integer> strInteger = Arrays.stream(num);
        Comparator <Integer> comparator = ((o1, o2) -> (o1 > o2) ? 1 : -1);
        BiConsumer <Integer, Integer> biConsumer = (i1, i2) -> System.out.println("First - " + i1 + "; Last - " + i2);
        findMinMax(strInteger, comparator,biConsumer);

// Exercise 2
        Stream <Integer> stream = Arrays.stream(num).filter(x -> x%2==0);
        System.out.println(stream.count());


// Задание 2
//Реализуйте метод, который принимает на вход список целых чисел и
// определяет количество четных и выводит их в консоль.
// Решать именно с применением Stream API.
    }

    public static <T, U> Function <T, U> ternaryOperator (
            Predicate <? super T> condition,
            Function <? super T, ? extends U> ifTrue,
            Function <? super T, ? extends U> ifFalse
    ) {
        return  t -> condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
    }

    public static <T> void findMinMax (
        Stream <? extends T> stream,
        Comparator <? super T> order,
        BiConsumer <? super T, ? super T> minMaxConsumer
    ){
        List <T> list = stream.collect(Collectors.toList());
        T min = null;
        T max = null;
        if (list.size()!=0){
            min = list.get(0);
            max = list.get(list.size()-1);
        }
        minMaxConsumer.accept(min, max);
    }
}