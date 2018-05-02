package sparklearn;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

import java.io.Serializable;
import java.util.Arrays;

class AvgCount implements Serializable {
    public AvgCount(int total, int num) {
        this.total = total;
        this.num = num;
    }
    public int total;
    public int num;
    public double avg() {
        return total / (double) num;
    }
}

public class AggregateExample {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("my app");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1,2,3,4,5));

        Function2<AvgCount, Integer, AvgCount> addAndCount =
                new Function2<AvgCount, Integer, AvgCount>() {
                    public AvgCount call(AvgCount a, Integer x) {
                        a.total += x;
                        a.num += 1;
                        return a;
                    }
                };
        Function2<AvgCount, AvgCount, AvgCount> combine =
                new Function2<AvgCount, AvgCount, AvgCount>() {
                    public AvgCount call(AvgCount a, AvgCount b) {
                        a.total += b.total;
                        a.num += b.num;
                        return a;
                    }
                };

        AvgCount initial = new AvgCount(0, 0);
        AvgCount result = rdd.aggregate(initial, addAndCount, combine);
        System.out.println(result.avg());
    }

}
