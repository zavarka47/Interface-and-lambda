package interface_and_lambda;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

        Function<String, Object> test = ternaryOperator(condition, ifTrue, ifFalse);

        System.out.println(test.apply(s1).getClass().getSimpleName() + " - " + test.apply(s1));
        System.out.println(test.apply(s2).getClass().getSimpleName() + " - " + test.apply(s2));

    }

    public static <T, U> Function <T, U> ternaryOperator (
            Predicate <? super T> condition,
            Function <? super T, ? extends U> ifTrue,
            Function <? super T, ? extends U> ifFalse
    ) {
        return  t -> condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
    }

}