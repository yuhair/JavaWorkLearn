package streamlearn.streambasics;

import streamlearn.lambdabasics.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StandardFunctionalInterfacesExample {

    public static void main(String[] args){

        List<Person> people = Arrays.asList(
                new Person("hai", "yu", 25),
                new Person("shuyang", "zheng", 20),
                new Person("yang", "yu", 25),
                new Person("jun", "zheng", 50),
                new Person("rong", "shu", 45)
        );

        //Step 1: Sort list by last name

        Collections.sort(people, (o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));


        //Step 2: Create a method that prints all elements in the list
        performConditionally(people, p -> true, p -> System.out.println(p));


        //Step 3: Create a method that prints all people that have last name beginning with z
        performConditionally(people, p -> p.getLastName().startsWith("z"), p -> System.out.println(p.getFirstName()));

        performConditionally(people, p -> p.getLastName().startsWith("y"), p -> System.out.println(p));
    }

    private static void performConditionally(List<Person> people, Predicate<Person> condition, Consumer<Person> consumer){
        for (Person p: people){
            if (condition.test(p)){
                consumer.accept(p);
            }

        }
    }
}



