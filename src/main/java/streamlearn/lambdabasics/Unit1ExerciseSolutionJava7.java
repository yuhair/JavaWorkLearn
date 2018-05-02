package streamlearn.lambdabasics;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Unit1ExerciseSolutionJava7 {

    public static void main(String[] args){

        List<Person> people = Arrays.asList(
                new Person("hai", "yu", 25),
                new Person("shuyang", "zheng", 20),
                new Person("yang", "yu", 25),
                new Person("jun", "zheng", 50),
                new Person("rong", "shu", 45)
        );

        //Step 1: Sort list by last name

        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });


        //Step 2: Create a method that prints all elements in the list
        printAll(people);


        //Step 3: Create a method that prints all people that have last name beginning with z
        printConditionally(people, new Condition() {
            @Override
            public boolean test(Person p) {
                return p.getLastName().startsWith("z");
            }
        });

        printConditionally(people, new Condition() {
            @Override
            public boolean test(Person p) {
                return p.getLastName().startsWith("y");
            }
        });
    }

    private static void printAll(List<Person> people){
        for (Person p: people){
            System.out.println(p);
        }
    }

    private static void printLastNameBeginningWithZ(List<Person> people){
        for (Person p: people){
            if (p.getLastName().startsWith("y")){
                System.out.println(p);
            }

        }
    }

    private static void printConditionally(List<Person> people, Condition condition){
        for (Person p: people){
            if (condition.test(p)){
                System.out.println(p);
            }

        }
    }
}

interface Condition{
    boolean test(Person p);
}



