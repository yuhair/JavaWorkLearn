package streamlearn.streammore;

import java.util.Arrays;
import java.util.List;

public class MethodReferenceExample3 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 67, 8, 9, 10);

//        numbers.stream()
//                .reduce(0, (total, e) -> Integer.sum(total, e));
//
//        numbers.stream()
//                .reduce(0, Integer::sum);
//
//        numbers.stream()
//                .map(String::valueOf)
//                .reduce("", (carry, str) -> carry.concat(str));
//
//        numbers.stream()
//                .map(String::valueOf)
//                .reduce("", String::concat);

//        System.out.println(
//                numbers.stream()
//                        .filter(e -> e % 2 == 0)
//                        .mapToInt(e -> e * 2)
//                        .sum()
//        );

//        Timeit.code(() ->
//                System.out.println(
//                        numbers.stream()
//                                .filter(e -> e % 2 == 0)
//                                .mapToInt(MethodReferenceExample3::compute)
//                                .sum()
//                )
//        );

        Timeit.code(() ->
                System.out.println(
                        numbers.parallelStream()
                                .filter(e -> e % 2 == 0)
                                .mapToInt(MethodReferenceExample3::compute)
                                .sum()
                )
        );
    }

    public static int compute(int number) {
        try {
            Thread.sleep(1000);
        }
        catch (Exception e){

        }
        return number * 2;
    }
}
