package streamlearn.parallel;

public class Sample2 {
    static int MAX = 1500;

    public static double compute(double number) {
        double result = 0;
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                result += Math.sqrt(i * j);
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
//        Timeit.code(() -> {
//            double result1 =
//                    IntStream.range(0, MAX)
//                            .mapToDouble((Sample2::compute))
//                            .sum();
//            System.out.println(result1);
//        });

//        Timeit.code(() -> {
//            double result2 =
//                    IntStream.range(0, MAX)
//                            .streamlearn.parallel()
//                            .mapToDouble((Sample2::compute))
//                            .sum();
//            System.out.println(result2);
//        });

//        ForkJoinPool pool = new ForkJoinPool(5000);
//        pool.submit(() -> {
//            Timeit.code(() -> {
//                double result2 =
//                        IntStream.range(0, MAX)
//                                .streamlearn.parallel()
//                                .mapToDouble((Sample2::compute))
//                                .sum();
//                System.out.println(result2);
//            });
//        });
//        pool.shutdown();
//        pool.awaitTermination(30, TimeUnit.SECONDS);
    }
}
