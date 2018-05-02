package sparklearn;

import org.apache.spark.HashPartitioner;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;

import java.util.Arrays;

public class RddPartitioner {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("my app");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaPairRDD<Integer, Integer> pairs = sc.parallelizePairs(Arrays.asList(
                new Tuple2<Integer, Integer>(1, 1),
                new Tuple2<Integer, Integer>(2, 2),
                new Tuple2<Integer, Integer>(3, 3)
        ));

        System.out.println(pairs.partitioner());

        JavaPairRDD<Integer, Integer> partitionedPairs = pairs.partitionBy(new HashPartitioner(2)).persist(StorageLevel.MEMORY_ONLY());

        System.out.println(partitionedPairs.partitioner());

        // All Spark’s operations involving shuffling data by key across the network will benefit from partitioning.
        // cogroup(), groupWith(), join(), leftOuterJoin(), rightOuter
        // Join(), groupByKey(), reduceByKey(), combineByKey(), and lookup().

        // A. All Spark’s operations that result in a partitioner being set on the output RDD
        // cogroup(), groupWith(), join(), leftOuterJoin(), rightOuter Join(),
        // groupByKey(), reduceByKey(), combineByKey(), partitionBy(), sort(),

        // B. All Spark’s operations that result in a partitioner being set on the output RDD if parent RDD has a partitioner
        // mapValues(), flatMapValues(), filter()

        // C. All other operations will produce a result with no partitioner

        // D. for binary operations
        //    1. the default hash partitioner is set on the output with the number of partitions set to the level of parallelism of the operation
        //    2. if one of the parents has a partitioner set, it will be that partitioner
        //    3. if both parents have a partitioner set, it will be the partitioner of the first parent

    }
}
