package streamlearn.streammore;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {
        List<Person> people = createPeople();
        System.out.println(personByName(people, "Bob"));

        System.out.println(personByName(people, "hai"));

    }

    public static List<Person> createPeople() {
        return Arrays.asList(
                new Person("Sara", Gender.FEMALE, 20),
                new Person("Sara", Gender.FEMALE, 22),
                new Person("Bob", Gender.MALE, 20),
                new Person("Paula", Gender.FEMALE, 32),
                new Person("Paul", Gender.MALE, 32),
                new Person("Jack", Gender.MALE, 2),
                new Person("Jack", Gender.MALE, 72),
                new Person("Jill", Gender.FEMALE, 42)
        );
    }

    public static String personByName(List<Person> people, String name) {
        Optional<Person> opt = people.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();

//        return opt.isPresent()? opt.get().getName() : "UNKNOWN";
//        return opt.orElse(new Person("Unknown", Gender.MALE, 0)).getName();
//        return opt.orElseGet(OptionalExample::getPerson).getName();
//        return opt.orElseThrow(OptionalExample::throwException).getName();
//          return opt.map(Person::getName).orElse("UNKNOWN");

        return people.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .map(Person::getName)
                .orElse("UNKNOWN");
    }

    public static Person getPerson(){
        return new Person("Unknown", Gender.MALE, 0);
    }

    public static Exception throwException(){
        return new Exception("no match");
    }
}
