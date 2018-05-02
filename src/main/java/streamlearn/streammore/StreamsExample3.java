package streamlearn.streammore;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class StreamsExample3 {

    public static List<Person> createPeople() {
        return Arrays.asList(
                new Person("Sara", Gender.FEMALE, 20),
                new Person("Sara", Gender.FEMALE, 22),
                new Person("Bob", Gender.MALE, 20),
                new Person("Paula", Gender.FEMALE, 32),
                new Person("Paul", Gender.MALE, 32),
                new Person("Jack", Gender.MALE, 2),
                new Person("Jack", Gender.MALE, 72),
                new Person("Jill", Gender.FEMALE, 12)
        );
    }

    public static void main(String[] args) {
        List<Person> people = createPeople();

        //create a Map with name and age as key and the person as value
        //tomap:
        //first parameter Function<T, K>
        //second parameter Functoin<T, U>
        Map<String, Person> map1 = people.stream()
                .collect(toMap(
                        p -> p.getName() + "-" + p.getAge(),
                        p -> p));

        //create a map where their name is the key and value is all the people with that name
        //groupingBy
        //paramter Function<T, K>
        Map<String, List<Person>> map2 = people.stream()
                .collect(groupingBy(Person::getName));

        //create a map where their name is the key and value is all the ages with that name
        Map<String, List<Integer>> map3 = people.stream()
                .collect(groupingBy(Person::getName, mapping(Person::getAge, toList())));


    }
}
