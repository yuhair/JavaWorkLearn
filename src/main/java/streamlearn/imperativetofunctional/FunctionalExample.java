package streamlearn.imperativetofunctional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class FunctionalExample {

    public static boolean isPrime(int n) {
        return n != 1 && IntStream.range(2, n).noneMatch(i -> n % i == 0);
    }

    public static void linesCount(){
        String searchWord ="localhost";
        String path = "/etc/hosts";

        try {
            long count = Files.lines(Paths.get(path))
                    .filter(line -> line.contains(searchWord))
                    .count();
            System.out.printf("The word %s occured %d times\n", searchWord, count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, List<String>> groupByScores(Map<String, Integer> scores){
        return scores.keySet().stream()
                .collect(groupingBy(scores::get));
    }



    public static void main(String[] args) {
        //exercise 1
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        List<Integer> res = numbers.stream()
                .filter(FunctionalExample::isPrime)
                .collect(toList());
        System.out.println(res.toString());

        //exercise 2
        linesCount();

        //exercise 3
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Jack", 12);
        scores.put("Jill", 15);
        scores.put("Tom", 11);
        scores.put("Darla", 15);
        scores.put("Nick", 15);
        scores.put("Nancy", 11);
        System.out.println(groupByScores(scores));

        //exercise 4
    }


}
