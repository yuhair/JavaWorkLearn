package streamlearn.parallel;

import streamlearn.streammore.Gender;
import streamlearn.streammore.OptionalExample;
import streamlearn.streammore.Person;

import java.util.List;


public class Sample3 {
    public static void main(String[] args) {
        List<Person> people = OptionalExample.createPeople();

        System.out.println("----------------------------------");

        for (int i = 0; i < 10; i++) {
            System.out.println(
                    people.stream()
                            .filter(p -> p.getGender() == Gender.FEMALE)
                            .filter(p -> p.getAge() > 30)
                            .map(Person::getName)
                            .map(String::toUpperCase)
                            .findFirst()
                            .orElse("no one")
            );
        }

        System.out.println("----------------------------------");

        for (int i = 0; i < 10; i++) {
            System.out.println(
                    people.stream()
                            .parallel()
                            .filter(p -> p.getGender() == Gender.FEMALE)
                            .filter(p -> p.getAge() > 30)
                            .map(Person::getName)
                            .map(String::toUpperCase)
                            .findFirst()
                            .orElse("no one")
            );
        }

        System.out.println("----------------------------------");

        for (int i = 0; i < 10; i++) {
            System.out.println(
                    people.stream()
                            .filter(p -> p.getGender() == Gender.FEMALE)
                            .filter(p -> p.getAge() > 30)
                            .map(Person::getName)
                            .map(String::toUpperCase)
                            .findAny()
                            .orElse("no one")
            );
        }

        System.out.println("----------------------------------");

        for (int i = 0; i < 10; i++) {
            System.out.println(
                    people.stream()
                            .parallel()
                            .filter(p -> p.getGender() == Gender.FEMALE)
                            .filter(p -> p.getAge() > 30)
                            .map(Person::getName)
                            .map(String::toUpperCase)
                            .findAny()
                            .orElse("no one")
            );
        }

    }
}
