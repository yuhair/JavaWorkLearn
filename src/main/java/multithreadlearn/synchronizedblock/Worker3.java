package multithreadlearn.synchronizedblock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker3 {

    private Random random = new Random();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stageOne(){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));

    }

    public void stageTwo(){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
    }

    public void process(){
        for (int i = 0; i < 2000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main(){
        System.out.println("Starting Worker2 ...");
        long start = System.currentTimeMillis();

        process();

        long end = System.currentTimeMillis();
        System.out.println("Time take: " + (end-start));
        System.out.println("List1: "+ list1.size() + " List2: "+list2.size());
    }
}
