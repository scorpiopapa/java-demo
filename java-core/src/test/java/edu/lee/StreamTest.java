package edu.lee;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {

    @Test void testReduce(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Integer sumReduce = integers.stream().reduce(10, Integer::sum);
        System.out.println("sum reduce -> " + sumReduce);

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Integer minReduce = integerStream.reduce(Integer.MAX_VALUE, Integer::min);
        System.out.println("min reduce -> " + minReduce);
    }
}
