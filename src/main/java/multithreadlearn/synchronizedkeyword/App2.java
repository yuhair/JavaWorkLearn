package multithreadlearn.synchronizedkeyword;

public class App2 {

    private int count = 0;

    // don't need to add volatile, because if you are running somthing in a synchronized block
    // then it is guaranteed that the variable is visible to all threads
    public synchronized void increment(){
        count++;
    }

    public static void main(String[] args) {
        App app = new App();
        app.doWordk();
    }

    public void doWordk(){
        Thread t1 = new Thread(()->{
            for(int i=0; i<10000;i++){
//                count++;
                increment();
            }
        });

        Thread t2 = new Thread(()->{
            for(int i=0; i<10000;i++){
//                count++;
                increment();
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
