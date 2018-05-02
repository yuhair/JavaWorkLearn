import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Learn {
    public static void main(String[] args) {
        List<String> dates= Arrays.asList("2016-03-05",
                "2016-04-05",
                "2016-03-09",
                "2017-03-05",
                "2016-04-05",
                null,
                null,
                "");

        Collections.sort(dates, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1 == null) {
                    return (o2 == null) ? 0 : -1;
                }
                if (o2 == null) {
                    return 1;
                }
                return o1.compareTo(o2);
            }
        }.reversed());

        dates.forEach(s -> {
            if(dates.size() > 3){
                System.out.println(s);
            }
        });

    }
}
