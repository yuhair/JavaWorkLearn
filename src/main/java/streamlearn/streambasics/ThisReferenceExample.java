package streamlearn.streambasics;

public class ThisReferenceExample {

    public void doProcess(int i, Process p){
        p.process(i);
    }

    public void execute(){
        doProcess(10, i -> {
            System.out.println("Value of i is "+ i);
            System.out.println(this);
        });

    }

    public static void main(String[] args) {
        ThisReferenceExample thisReferenceExample = new ThisReferenceExample();
//        System.out.println(this);

        thisReferenceExample.doProcess(10, new Process() {
            @Override
            public void process(int i) {
                System.out.println("Value of i is "+ i);
                System.out.println(this);
            }

            public String toString(){
                return "This is the anonymous inner class";
            }
        });

        thisReferenceExample.doProcess(10, i -> {
            System.out.println("Value of i is "+ i);
//            System.out.println(this);
        });

        thisReferenceExample.execute();
    }

    public String toString(){
      return "This is the main ThisReferenceExample class instance";
    };
}
