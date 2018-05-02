package genericslearn;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;

public class Box<T> {

    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public <U> void inspect1 (U u){
        System.out.println(u.getClass());
    }

    public <U> String inspect2 (){
        return "str";
    }

    public <U extends Number> U inspect3 (U u){
        return u;
    }

    public <U> U inspect4 (List<U> numbers){
        return numbers.get(0);
    }

    public void inspect5 (List<?> numbers){
        System.out.println(numbers.get(0));
    }

    // ? generics of generics
    public void inspect6 (List<? extends Number> numbers){
        for(Number n: numbers){
            System.out.println(n);
        }
    }

    public void inspect7 (List<? super Integer> numbers){
        for(Object n: numbers){
            System.out.println(n);
        }
    }



}
