package streamlearn.parallel;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Sample1 {

    public static int transform(int number) {
        System.out.println("transform: " + number + " " + Thread.currentThread());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return number;
    }

    public static void printIt(int number) {
        System.out.println(number + " " + Thread.currentThread());
    }

    public static boolean check(int number) {
        System.out.println("check: " + number + " " + Thread.currentThread());
        return true;
    }

    public static int add(int total, int e) {
        int result = total + e;
        System.out.println("total: " + total + "e: " + e + "result: " + result);
        return result;
    }

    private static void process(Stream<Integer> stream) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(50);
        pool.submit(() -> stream.forEach(e -> {}));
        pool.shutdown();
        pool.awaitTermination(30, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception{

//        computation intensive operatoins
//        # of Threads <= # of cores

//        |                x
//        |            x
//   time |                    x
//        |        x
//        |    x                   x
//        |-------------------------------------
//            1   2   3   4   5   6
//               # of Threads (cores == 4)


//        IO intensive operations
//                           # of cores
//        # of Threads =   -------------------
//                           blocking factor
//
//        0< blocking factor < 1
//        blocking factor is the amount of ratio of time that you are not going to use gpu

//        System.out.println(Runtime.getRuntime().availableProcessors());
//        System.out.println(ForkJoinPool.commonPool());


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20);


//        System.out.println("------------ foreach can be streamlearn.parallel ---------------");
//
//        numbers.parallelStream()
//                .forEach(System.out::println);

//        System.out.println("------------ map can be streamlearn.parallel ------------");
//
//        numbers.parallelStream()
//                .map(Sample1::transform)
//                .forEach(Sample1::printIt);

//        System.out.println("------------ we can control foreach order --------");
//
//        numbers.stream()
//                .map(Sample1::transform)
//                .forEachOrdered(Sample1::printIt)

//        System.out.println("------------- filter can be streamlearn.parallel ----------------");
//
//        numbers.parallelStream()
//                .filter(Sample1::check)
//                .forEachOrdered(Sample1::printIt);

//        System.out.println("----------- reduce can be streamlearn.parallel but use identity initial value ---------");
//
//        System.out.println(numbers.stream()
//                .reduce(0, Sample1::add));
//        System.out.println(numbers.parallelStream()
//                .reduce(0, Sample1::add));

//        System.out.println("----------- reduce can be streamlearn.parallel but if not use identity initial value the result is wrong ---------");
//
//        System.out.println(numbers.stream()
//                .reduce(1, Sample1::add));
//        System.out.println(numbers.parallelStream()
//                .reduce(1, Sample1::add));

//        System.out.println("------------- collect can be streamlearn.parallel ----------------");
//
//        System.out.println(
//                numbers.parallelStream()
//                        .filter(Sample1::check)
//                        .collect(toList())
//        );

//        System.out.println("------------- there are three batches to run 20/8 = 3 ----------------");
//        numbers.stream()
//                .streamlearn.parallel()
//                .map(Sample1::transform)
//                .forEach(e -> {});
//        System.out.println("DONE "+ Thread.currentThread());

//        System.out.println("------------- where you run the terminal operation decides what pool is used ----------------");
//
//        Stream<Integer> stream = numbers.stream()
//                                        .streamlearn.parallel()
//                                        .map(Sample1::transform);
//        process(stream);



    }

}
