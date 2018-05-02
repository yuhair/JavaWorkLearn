package streamlearn.lambdabasics;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Unit1ExerciseSolutionJava8 {

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
        printConditionally(people, p -> true);


        //Step 3: Create a method that prints all people that have last name beginning with z
        printConditionally(people, p -> p.getLastName().startsWith("z"));

        printConditionally(people, p -> p.getLastName().startsWith("y"));
    }

    private static void printConditionally(List<Person> people, Predicate<Person> condition){
        for (Person p: people){
            if (condition.test(p)){
                System.out.println(p);
            }

        }
    }
}



