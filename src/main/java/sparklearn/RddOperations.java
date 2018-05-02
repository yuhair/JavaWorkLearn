package sparklearn;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.storage.StorageLevel;

import java.util.Arrays;

public class RddOperations {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("my app");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //Transformations

        // map
        JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4));
        JavaRDD<Integer> result = rdd.map(x -> x * x);
//        System.out.println(StringUtils.join(result.collect(), ","));

        // flatmap
        JavaRDD<String> lines = sc.parallelize(Arrays.asList("hello world", "hi"));
        JavaRDD<String> words = lines.flatMap(l -> Arrays.asList(l.split(" ")).iterator());
//        System.out.println(words.first());

        // set operation
        // 1. often have duplicates
        // 2. need shuffle (much worse performance)
        //    intersection (no duplicates)
        //    distinct     (no duplicates)
        //    subtract     (contains duplicates)
        // 3. union        (contain duplicates)
        // 4. cartesian    very expensive for large RDDs

        JavaRDD<String> rdd1 = sc.parallelize(Arrays.asList("coffee", "coffee", "panda", "monkey", "tea", "tea"));
        JavaRDD<String> rdd2 = sc.parallelize(Arrays.asList("coffee", "monkey", "kitty"));

//        rdd1.distinct().collect().forEach(System.out::println);

//        rdd1.union(rdd2).collect().forEach(System.out::println);

//        rdd1.intersection(rdd2).collect().forEach(System.out::println);

//        rdd1.subtract(rdd2).collect().forEach(System.out::println);

//        rdd1.cartesian(rdd2).collect().forEach(System.out::println);

        // sample return JavaRDD<T>
//        rdd1.sample(false, 0.5, 12345).collect().forEach(System.out::println);




        // Actions

        JavaRDD<Integer> intRdd = sc.parallelize(Arrays.asList(2, 1, 3, 5, 4, 3, 4));

        // reduce
//        System.out.println(intRdd.reduce((x, y) -> x + y));

        // fold
//        System.out.println(intRdd.fold(0, (x, y) -> x + y));

        // collect
        // suffers from the restriction that all of your data must fit on a single machine, as it all
        // needs to be copied to the driver.
//        intRdd.collect().forEach(System.out::println);

        // take
        // It’s important to note that these operations do not return the elements in the order you might expect.
//        intRdd.take(3).forEach(System.out::println);

        // top
        // If there is an ordering defined on our data, we can also extract the top elements from
        // an RDD using top(). top() will use the default ordering on the data, but we can supply
        // our own comparison function to extract the top elements.
//        intRdd.top(3).forEach(System.out::println);

        // takesample (return List<T>)
//        intRdd.takeSample(false, 3, 12345).forEach(System.out::println);

        // foreach
//        intRdd.foreach( i -> System.out.println(i));
//        intRdd.collect().forEach(System.out::println);

        // countByValue
//        intRdd.countByValue().forEach((k,v) -> System.out.println(k + " : " + v));

        // count
//        System.out.println(intRdd.count());

        // reduce
//        System.out.println(intRdd.reduce((x,y) -> x+y));

        // fold
//        System.out.println(intRdd.fold(0, (x,y) -> x+y));

        // aggregate
        // refer to AggregateExample

        // countByValues
        intRdd.countByValue().forEach((k,v) -> System.out.println(k + " : " + v));




        // Converting between RDD Types
        JavaDoubleRDD res = intRdd.mapToDouble(x -> (double)(x * x));
//        System.out.println(res.mean());


        // Persistence
        // In Java, the default persit() will store the data in the JVM heap as unserialized objects
        // we called persist() before the first action. The persist() call on its own doesn’t force evaluation.
        // Finally, RDDs come with a method called unpersist() that lets you manually remove them from the cache.
        JavaRDD<Integer> squareRdd = intRdd.map(x -> x*x);
        squareRdd.persist(StorageLevel.MEMORY_ONLY());
        System.out.println(squareRdd.count());
        System.out.println(squareRdd.collect().toString());
    }
}
