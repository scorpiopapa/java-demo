package edu.lee;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FunctionTest {

    @Test void testSupplier(){
        Supplier<String> supplier1 = String::new;
        Supplier<String> supplier2 = String::new;

        assertNotSame(supplier1.get(), supplier2.get());
    }

    @Test void testConsumer(){
        Consumer<Integer> c1 = x -> System.out.println(x * 2);
        Consumer<Integer> c2 = x -> System.out.println(x * 5);

        c1.andThen(c2).accept(10);
    }

    @Test void testPredicate(){
        //无限流:1,2,3,4,5...
        Stream<Integer> stream = Stream.iterate(1, i -> i + 1);

        Predicate<Integer> p1 = i -> i > 4;         //大于4
        Predicate<Integer> p2 = i -> i < 8;         //小于8
        Predicate<Integer> p3 = i -> i %2 == 0;     //偶数

        //取无限流前10条数据，大于4，并且小于8，并且非偶数，或者等于1
        stream.limit(10).filter(p1.and(p2).and(p3.negate()).or(Predicate.isEqual(1)))
                .forEach(System.out::println);
    }

    @Test void testFunction(){
        Function<Integer, Integer> f1 = i -> i + i;
        Function<Integer, Integer> f2 = i -> i * i;

        assertEquals(18, f1.compose(f2).apply(3));
        assertEquals(36, f1.andThen(f2).apply(3));
    }
}
