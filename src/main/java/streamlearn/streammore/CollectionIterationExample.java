package streamlearn.streammore;

import streamlearn.lambdabasics.Person;

import java.util.Arrays;
import java.util.List;

public class CollectionIterationExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("hai", "yu", 25),
                new Person("shuyang", "zheng", 20),
                new Person("yang", "yu", 25),
                new Person("jun", "zheng", 50),
                new Person("rong", "shu", 45)
        );

        System.out.println("Using for loop");
        for(int i=0; i<people.size(); i++){
            System.out.println(people.get(i));
        }

        System.out.println("Using for in loop");
        for(Person p: people){
            System.out.println(p);
        }

        System.out.println("Using lambda for each loop");
        people.forEach(p->System.out.println(p));

        System.out.println("Using method reference for each loop");
        people.forEach(System.out::println);
    }
}
