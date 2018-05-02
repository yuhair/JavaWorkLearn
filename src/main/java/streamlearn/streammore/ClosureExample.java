package streamlearn.streammore;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ClosureExample {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3);

        int factor = 2;
        Stream<Integer> strm = numbers.stream().map(e -> e * factor);
//        factor = 4;
        strm.forEach(System.out::println);


// bad closure, it avoids purity
// pure function:
//    1. no change anyting
//    2. do not depend on something that changes
// lazy evaluation :
//    line map is not executing untill line foreach

        int[] factor2 = new int[] {2};
        Stream<Integer> strm2 = numbers.stream().map(e -> e * factor2[0]);
        factor2[0] = 0;
        strm2.forEach(System.out::println);

//        you cannot have lazy evaluation without immutability
//        function composition numbers.stream.filter().map().sum()

        numbers.stream()
                .parallel()
                .mapToInt(e -> e*2)
                .sum();

        numbers.parallelStream()
                .mapToInt(e -> e*2)
                .sum();

    }
}
