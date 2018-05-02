package streamlearn.streammore;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsExample5 {

    public static int compute(int k, int n){
        int result = 0;
        int index = k;
        int count =0;
        while(count <n){
            if(index%2 == 0 && Math.sqrt(index)>20){
                result += index*2;
                count++;
            }
            index++;
        }
        return result;
    }

    public static int compute2(int k, int n){
        return Stream.iterate(k, e->e+1)          //unbounded, lazy, return a stream
                .filter(e-> e%2 ==0)              //unbounded, lazy, return a stream
                .filter(e-> Math.sqrt(e) > 20)    //unbounded, lazy, return a stream
                .mapToInt(e -> e*2)               //unbounded, lazy, return a stream
                .limit(n)                         //sized, lazy, return a stream
                .sum();                           //terminal function: return non-stream
    }


    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,1,2,3,4,5);

//      characteristics of a stream: sized, ordered, distinct, sorted

        numbers.stream()
                .filter(e -> e%2==0)
                .forEach(System.out::println);
        //sized, ordered, non-distinct, non-sorted

        numbers.stream()
                .filter(e -> e%2==0)
                .sorted()
                .distinct()
                .forEach(System.out::println);
        //sized, ordered, distinct, sorted



        //start with 100, create a series 100, 101, 102 ...
        // infinite streams
        System.out.println(Stream.iterate(100, e-> e+1));


        //Given a number k and a count n,
        //find the total of the double of n even numbers
        //starting with k, where sqrt of each number is > 20

        int k=121;
        int n= 51;

        System.out.println(compute(k, n));

        System.out.println(compute2(k, n));



    }
}
