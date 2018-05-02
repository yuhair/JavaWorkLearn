package streamlearn.streammore;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StreamsExample4 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        //given an ordered list find the double of the first even number greater than 3

        int result = 0;
        for (int e : numbers) {
            if (e > 3 && e % 2 == 0) {
                result = e * 2;
                break;
            }
        }
        System.out.println(result);

        System.out.println(
                numbers.stream()
                        .filter(e -> e > 3)
                        .filter(e -> e % 2 == 0)
                        .map(e -> e * 2)
                        .findFirst()
        );

        System.out.println(
                numbers.stream()
                        .filter(StreamsExample4::isGT3)
                        .filter(StreamsExample4::isEven)
                        .map(StreamsExample4::doubleIt)
                        .findFirst()   //lazy evaluation
        );

        System.out.println(
                numbers.stream()
                        .filter(StreamsExample4::isGT3)
                        .filter(StreamsExample4::isEven)
                        .map(StreamsExample4::doubleIt)
                        .collect(toList())
        );

        System.out.println("lazy evaluation");
        numbers.stream()
                .filter(StreamsExample4::isGT3)
                .filter(StreamsExample4::isEven)
                .map(StreamsExample4::doubleIt);
        System.out.println("lazy evaluation");
    }

    public static boolean isGT3(int n){
        System.out.println("isGT3 " + n);
        return n>3;
    }
    public static boolean isEven(int n){
        System.out.println("isEven " + n);
        return n % 2 ==0;
    }
    public static int doubleIt(int n){
        System.out.println("doubleIt  " + n);
        return n * 2;
    }
}
