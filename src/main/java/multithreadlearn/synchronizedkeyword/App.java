package multithreadlearn.synchronizedkeyword;

import java.util.concurrent.atomic.AtomicInteger;

public class App {

//    private int count = 0;

    private AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        App app = new App();
        app.doWordk();
    }

    public void doWordk(){
        Thread t1 = new Thread(()->{
           for(int i=0; i<10000;i++){
               count.getAndIncrement();
           }
        });

        Thread t2 = new Thread(()->{
            for(int i=0; i<10000;i++){
                count.getAndIncrement();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Count is: "+count);
    }

}
