package streamlearn.streammore;

import streamlearn.lambdabasics.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MethodReferenceExample2 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("hai", "yu", 25),
                new Person("shuyang", "zheng", 20),
                new Person("yang", "yu", 25),
                new Person("jun", "zheng", 50),
                new Person("rong", "shu", 45)
        );

        // Create a method that prints all elements in the list
        performConditionally(people, p -> true, p -> System.out.println(p));

        performConditionally(people, p -> true, System.out::println);


        people.stream().map(Person::getLastName);

    }

    private static void performConditionally(List<Person> people, Predicate<Person> condition, Consumer<Person> consumer){
        for (Person p: people){
            if (condition.test(p)){
                consumer.accept(p);
            }

        }
    }
}
