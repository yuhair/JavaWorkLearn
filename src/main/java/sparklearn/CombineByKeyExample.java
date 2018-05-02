package sparklearn;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

class AvgCount2 implements Serializable {

    public int total_;
    public int num_;

    public AvgCount2(int total, int num) {
        total_ = total;
        num_ = num;
    }

    public float avg() {
        return total_ / (float) num_;
    }
}

public class CombineByKeyExample {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("my app");
        JavaSparkContext sc = new JavaSparkContext(conf);

        Function<Integer, AvgCount2> createAcc = new Function<Integer, AvgCount2>() {
            public AvgCount2 call(Integer x) {
                return new AvgCount2(x, 1);
            }
        };
        Function2<AvgCount2, Integer, AvgCount2> addAndCount =
                new Function2<AvgCount2, Integer, AvgCount2>() {
                    public AvgCount2 call(AvgCount2 a, Integer x) {
                        a.total_ += x;
                        a.num_ += 1;
                        return a;
                    }
                };
        Function2<AvgCount2, AvgCount2, AvgCount2> combine =
                new Function2<AvgCount2, AvgCount2, AvgCount2>() {
                    public AvgCount2 call(AvgCount2 a, AvgCount2 b) {
                        a.total_ += b.total_;
                        a.num_ += b.num_;
                        return a;
                    }
                };

        JavaPairRDD<String, Integer> pairRDD = sc.parallelizePairs(Arrays.asList(
                new Tuple2<String, Integer>("panda", 0),
                new Tuple2<String, Integer>("pink", 3),
                new Tuple2<String, Integer>("pirate", 3),
                new Tuple2<String, Integer>("panda", 1),
                new Tuple2<String, Integer>("pink", 4)
        ));

        JavaPairRDD<String, AvgCount2> AvgCount2s =
                pairRDD.combineByKey(createAcc, addAndCount, combine);

        Map<String, AvgCount2> countMap = AvgCount2s.collectAsMap();


        for (Map.Entry<String, AvgCount2> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().avg());
        }
    }

}
