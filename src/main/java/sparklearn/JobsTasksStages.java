package sparklearn;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class JobsTasksStages {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("my app");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> input = sc.textFile("/Users/yuhair/IdeaProjects/WorkLearn/data/spark/input.txt");

        System.out.println(input.toDebugString());

        JavaRDD<String[]> tokenized = input.map(line -> line.split(" "))
                                            .filter(words -> !words[0].isEmpty());


        JavaPairRDD<String, Integer> counts = tokenized.mapToPair(words -> new Tuple2<String, Integer>(words[0], 1))
                             .reduceByKey((a, b)-> a+b);

        System.out.println(counts.toDebugString());

        counts.cache();

        counts.collect().forEach(System.out::println);

        System.out.println(counts.toDebugString());

        counts.collect().forEach(System.out::println);

        /*
        A physical stage will launch tasks that each do the same thing but on specific partitions
        of data. Each task internally performs the same steps:
        1. Fetching its input, either from data storage (if the RDD is an input RDD), an
        existing RDD (if the stage is based on already cached data), or shuffle outputs.
        2. Performing the operation necessary to compute RDD(s) that it represents. For
        instance, executing filter() or map() functions on the input data, or performing
        grouping or reduction.
        3. Writing output to a shuffle, to external storage, or back to the driver (if it is the
        final RDD of an action such as count()).
         */


    }
}
