package multithreadlearn.synchronizedblock;

public class App {
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.main();
        Worker2 worker2 = new Worker2();
        worker2.main();
        Worker3 worker3 = new Worker3();
        worker3.main();
    }
}
