package multithreadlearn.volativekeyword;

import java.util.Scanner;

class Processor extends Thread{

//    可见性，是指线程之间的可见性，一个线程修改的状态对另一个线程是可见的。也就是一个线程修改的结果。
//    另一个线程马上就能看到。比如：用volatile修饰的变量，就会具有可见性。
    private volatile boolean running = true;

    public void run(){
        while(running){
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void shutdown(){
        running = false;
    }
}

public class App {

    public static void main(String[] args) {
        Processor proc1= new Processor();
        proc1.start();

        System.out.println("Press return to stop ...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc1.shutdown();
    }
}
