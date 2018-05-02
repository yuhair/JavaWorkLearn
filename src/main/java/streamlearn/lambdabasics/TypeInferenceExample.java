package streamlearn.lambdabasics;

public class TypeInferenceExample {

    public static void main(String[] args){
        StringLengthLambda myLambda = s -> s.length();
        printLambda(myLambda);
        printLambda(s -> s.length());
    }

    public static void printLambda(StringLengthLambda l){
        System.out.println(l.getLength("Hello Lambda"));
    }

    interface StringLengthLambda{
        int getLength(String s);
    }

}
