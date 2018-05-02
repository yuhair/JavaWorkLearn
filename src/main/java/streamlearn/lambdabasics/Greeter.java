package streamlearn.lambdabasics;

public class Greeter {

    public void greet(Greeting greeting){
        greeting.perform();
    }

    public static void main(String[] args){

        Greeting helloWorldGreeting = new HelloWorldGreeting();
        Greeting lambdaGreeting = () -> System.out.println("Hello world");
        Greeting innerClassGreeting = new Greeting() {
            @Override
            public void perform() {
                System.out.println("Hello world");
            }
        };

        helloWorldGreeting.perform();
        lambdaGreeting.perform();
        innerClassGreeting.perform();

        Greeter greeter = new Greeter();
        greeter.greet(helloWorldGreeting);
        greeter.greet(lambdaGreeting);
        greeter.greet(() -> System.out.println("Hello world"));
        greeter.greet(innerClassGreeting);
    }
}