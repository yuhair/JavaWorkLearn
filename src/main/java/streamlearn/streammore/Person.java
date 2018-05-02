package streamlearn.streammore;

public class Person {
    private String name;
    private Gender gender;
    private int age;

    public Person(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
//        System.out.println("getName for " + name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
//        System.out.println("getGender for " + name);
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
//        System.out.println("getAge for " + name);
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
