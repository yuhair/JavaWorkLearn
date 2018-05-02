package sparklearn;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.Optional;
import scala.Tuple2;

import java.util.Arrays;

public class PairRDDExample {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("my app");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaPairRDD<String, Integer> pairRDD = sc.parallelizePairs(Arrays.asList(
                new Tuple2<String, Integer>("panda", 0),
                new Tuple2<String, Integer>("pink", 3),
                new Tuple2<String, Integer>("pirate", 3),
                new Tuple2<String, Integer>("panda", 1),
                new Tuple2<String, Integer>("pink", 4)
                ));



        // mapValues
        // reduceByKey
        // Because datasets can have very large numbers of keys, reduceByKey() is not implemented as
        // an action that returns a value to the user program. Instead, it returns a new RDD
        // consisting of each key and the reduced value for that key.

        JavaPairRDD<String, Tuple2> res = pairRDD.mapValues(v -> new Tuple2(v, 1))
                                                 .reduceByKey((x, y) -> new Tuple2((Integer)x._1 + (Integer)y._1, (Integer)x._2 + (Integer)y._2));
        res.collect().forEach(x -> System.out.println(x._1 + " : " + x._2));

        System.out.println(res.partitions().size());

        JavaPairRDD<String, Tuple2> res2 = pairRDD.mapValues(v -> new Tuple2(v, 1))
                .reduceByKey((x, y) -> new Tuple2((Integer)x._1 + (Integer)y._1, (Integer)x._2 + (Integer)y._2), 4);
        res2.collect().forEach(x -> System.out.println(x._1 + " : " + x._2));

        System.out.println(res2.partitions().size());

        //groupByKey
        pairRDD.groupByKey().collectAsMap().forEach((k,v)->System.out.println(k + " : " + v));


        //Join
        JavaPairRDD<String, String> storeAddressRDD = sc.parallelizePairs(Arrays.asList(
                new Tuple2<String, String>("Ritual", "1026 Valencia St"),
                new Tuple2<String, String>("Philz", "748 Van Ness Ave"),
                new Tuple2<String, String>("Philz", "3101 24th St"),
                new Tuple2<String, String>("Starbucks", "Seattle")
        ));

        JavaPairRDD<String, Double> storeRatingRDD = sc.parallelizePairs(Arrays.asList(
                new Tuple2<String, Double>("Ritual", 4.9),
                new Tuple2<String, Double>("Philz", 4.8)
                ));

        //innerJoin
        JavaPairRDD<String, Tuple2<String, Double>> innerJoinRes = storeAddressRDD.join(storeRatingRDD);
        innerJoinRes.collect().forEach(t -> System.out.println(t._1 + " : " + t._2));

        //leftOuterJoin
        JavaPairRDD<String, Tuple2<String, Optional<Double>>> leftOuterJoinRes = storeAddressRDD.leftOuterJoin(storeRatingRDD);
        leftOuterJoinRes.collect().forEach(t -> System.out.println(t._1 + " : " + t._2));

        //rightOuterJoin
        JavaPairRDD<String, Tuple2<Optional<String>, Double>> rightOuterJoinRes = storeAddressRDD.rightOuterJoin(storeRatingRDD);
        rightOuterJoinRes.collect().forEach(t -> System.out.println(t._1 + " : " + t._2));


        //Sorting Data
        //Once we have sorted our data, any subsequent call on
        //the sorted data to collect() or save() will result in ordered data.

        //sortByKey
        JavaPairRDD<String, Integer> sortedRdd = pairRDD.sortByKey();
        sortedRdd.foreach(t -> System.out.println(t._1 + " : " + t._2));




        //Actions

        //countByKey
        pairRDD.countByKey().forEach((k,v)->System.out.println(k + " : " + v));

        //collectAsMap
        pairRDD.collectAsMap().forEach((k,v)->System.out.println(k + " : " + v));

        //lookup
        pairRDD.lookup("panda").forEach(System.out::println);


        //Partitioning
        //  if a given RDD is scanned only once, there is no point in partitioning it in
        // advance. It is useful only when a dataset is reused multiple times in key-oriented
        // operations such as joins.


        /*
        Note that partitionBy() is a transformation, so it always returns a new RDD—it
        does not change the original RDD in place. RDDs can never be modified once created.
        Therefore it is important to persist and save as userData the result of parti
        tionBy(), not the original sequenceFile(). Also, the 100 passed to partitionBy()
        represents the number of partitions, which will control how many parallel tasks perform
        further operations on the RDD (e.g., joins); in general, make this at least as large
        as the number of cores in your cluster.
         */

        /*
        In fact, many other Spark operations automatically result in an RDD with known
        partitioning information, and many operations other than join() will take advantage
        of this information.
        sortByKey --> range-partitioned RDDs
        groupByKey --> hash-partitioned RDDs
        map -> cause the new RDD to forget the parent's partitioning information
               because such operations could theoretically modify the key of each record
         */
    }
}
