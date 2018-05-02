package multithreadlearn.twoWaysCreateThread;

public class App3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for (int i=0; i<10; i++){
                System.out.println("Hello "+ i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10; i++){
                    System.out.println("Hello "+ i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t2.start();
    }
}
