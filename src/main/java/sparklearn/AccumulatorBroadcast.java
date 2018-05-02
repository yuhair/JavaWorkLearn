package sparklearn;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.util.LongAccumulator;
import scala.tools.cmd.Spec;

import java.util.Arrays;
import java.util.List;

public class AccumulatorBroadcast {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("my app");
        JavaSparkContext sc = new JavaSparkContext(conf);

        /*
        The end result is that for accumulators used in actions, Spark applies each task’s update
        to each accumulator only once. Thus, if we want a reliable absolute value counter,
        regardless of failures or multiple evaluations, we must put it inside an action like foreach().

        For accumulators used in RDD transformations instead of actions, this guarantee does not exist.
        An accumulator update within a transformation can occur more than once. Within transformations,
        accumulators should, consequently, be used only for debugging purposes.

        While future versions of Spark may change this behavior to count the update only
        once, the current version (1.2.0) does have the multiple update behavior, so accumulators
        in transformations are recommended only for debugging purposes.

        An operation op is commutative if a op b = b op a for all values a, b.
        An operation op is associative if (a op b) op c = a op (b op c) for all values a, b, and c.
        For example, sum and max are commutative and associative operations that are commonly used in Spark accumulators.
         */

        JavaRDD<String> rdd = sc.textFile("/Users/yuhair/IdeaProjects/WorkLearn/data/spark/wordcountinput.txt");

        final LongAccumulator accum = sc.sc().longAccumulator();

        rdd.foreach(x -> accum.add(1));

        System.out.println("num of lines: " + accum.value());

        /*
        broadcast
        1. Create a Broadcast[T] by calling SparkContext.broadcast on an object of type
            T. Any type works as long as it is also Serializable.
        2. Access its value with the value property (or value() method in Java).
        3. The variable will be sent to each node only once, and should be treated as readonly
            (updates will not be propagated to other nodes).

        In particular, Java Serialization, the
        default serialization library used in Spark’s Scala and Java APIs, can be very inefficient
        out of the box for anything except arrays of primitive types. You can optimize
        serialization by selecting a different serialization library using the spark.serializer
        property (Chapter 8 will describe how to use Kryo, a faster serialization library), or by
        implementing your own serialization routines for your data types (e.g., using the
        java.io.Externalizable interface for Java Serialization, or using the reduce()
        method to define custom serialization for Python’s pickle library).
         */

        List<Integer> data = Arrays.asList(5, 1, 1, 4, 4, 2, 2);
        JavaRDD<Integer> javaRDD = sc.parallelize(data);
        final Broadcast<List> broadcast = sc.sc().broadcast(data, scala.reflect.ClassManifestFactory.fromClass(List.class));
        JavaRDD<Integer> result = javaRDD.map(new Function<Integer, Integer>() {
            List<Integer> iList = broadcast.value();
            @Override
            public Integer call(Integer v1) throws Exception {
                Integer isum = 0;
                for(Integer i : iList)
                    isum += i;
                return v1 + isum;
            }
        });
        System.out.println(result.collect());


        /*
        Working with data on a per-partition basis allows us to avoid redoing setup work for
        each data item. Operations like opening a database connection or creating a randomnumber
        generator are examples of setup steps that we wish to avoid doing for each
        element. Spark has per-partition versions of map and foreach to help reduce the cost
         */



    }
}
