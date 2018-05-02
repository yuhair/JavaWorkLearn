package sparklearn;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.Arrays;

class Contains implements Function<String, Boolean> {
    private String query;

    public Contains(String query) {
        this.query = query;
    }

    public Boolean call(String x) {
        return x.contains(query);
    }
}

public class RddBasics {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("my app");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> inputRDD = sc.parallelize(
                Arrays.asList(
                        "pandas",
                        "i like pandas",
                        "dogs",
                        "i like dogs",
                        "cats",
                        "i like cats"));

        // transformations
        JavaRDD<String> pandasRDD = inputRDD.filter(s -> s.contains("pandas"));

        JavaRDD<String> catsRDD = inputRDD.filter(new Contains("dog"));

        JavaRDD<String> unionRDD = pandasRDD.union(catsRDD);


        //actions
        //It is important to note that each time we call a new action, the entire RDD must be
        //computed “from scratch.” To avoid this inefficiency, users can persist intermediate results
        System.out.println(unionRDD.count());

        unionRDD.take(2).forEach(System.out::println);

        //In most cases RDDs can’t just be collect()ed to the driver because they are too
        //large. In these cases, it’s common to write data out to a distributed storage system
        //such as HDFS or Amazon S3.
        unionRDD.collect().forEach(System.out::println);


        //Lazy evaluation
        // Both Loading data into an RDD and transformations on RDD are lazily evaluated
    }
}
