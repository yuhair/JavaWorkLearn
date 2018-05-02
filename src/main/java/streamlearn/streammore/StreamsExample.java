package streamlearn.streammore;

import streamlearn.lambdabasics.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("hai", "yu", 25),
                new Person("shuyang", "zheng", 20),
                new Person("yang", "yu", 25),
                new Person("jun", "zheng", 50),
                new Person("rong", "shu", 45)
        );

        people.stream()
                .filter(p->p.getLastName().startsWith("z"))
                .forEach(p->System.out.println(p.getFirstName()));

        Stream<Person> stream = people.stream();
        stream.filter(p->p.getLastName().startsWith("z"))
                .count();

        long count = people.stream()
                .filter(p->p.getLastName().startsWith("z"))
                .count();

        System.out.println(count);

    }
}
