package streamlearn.streammore;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class StreamsExample2 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //filter: 0 <= number of elements in the output <= num of input
        //parameter: Stream<T> filter takes Predicate<T> to return Stream<T>

        //map: number of output == number of input
        //parameter: Stream<T> map takes Function<T, R> to return Stream<R>

        //reduce

        //first parameter: T
        //second parameter: BiFunction<R, T, R> to return a result of R


        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);

//        wrong: mutability is ok, sharing is nice, shared mutability is bad
//        List<Integer> doubleOfEven = new ArrayList<>();
//        numbers2.stream()
//                .filter(e -> e % 2 ==0)
//                .map(e -> e * 2)
//                .forEach(e -> doubleOfEven.add(e));
//        System.out.println(doubleOfEven.toString());
//        wrong: avoid shared mutability

        // collect is a reduce operation as well
        // toList, toSet, toMap

        List<Integer> doubleOfEven2 =
                numbers2.stream()
                        .filter(e -> e % 2 == 0)
                        .map(e -> e * 2)
                        .collect(toList());

        Set<Integer> doubleOfEven3 =
                numbers2.stream()
                        .filter(e -> e % 2 == 0)
                        .map(e -> e * 2)
                        .collect(toSet());



    }
}
