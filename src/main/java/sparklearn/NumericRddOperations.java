package sparklearn;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.util.StatCounter;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class NumericRddOperations {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("my app");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Double> testData = IntStream.range(1, 11)
                .mapToDouble(d -> d)
                .boxed()
                .collect(toList());

        JavaDoubleRDD nums = sc.parallelizeDoubles(testData);

        StatCounter stats= nums.stats();
        System.out.println(stats.count());
        System.out.println(stats.mean());
        System.out.println(stats.sum());
        System.out.println(stats.max());
        System.out.println(stats.min());
        System.out.println(stats.variance());
        System.out.println(stats.sampleVariance());
        System.out.println(stats.stdev());
        System.out.println(stats.sampleStdev());


    }
}
