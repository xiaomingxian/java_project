package java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StreamTest {


    public static void main(String[] args) {

        Person p1 = new Person("tom-1", 60, 1);
        Person p2 = new Person("tom-2", 50, 1);
        Person p3 = new Person("tom-3", 40, 1);
        Person p4 = new Person("tom-4", 30, 2);
        Person p5 = new Person("tom-5", 20, 2);
        Person p6 = new Person("tom-6", 10, 3);

        ArrayList<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);


//        HashMap<String, List<Person>> map = new HashMap<>();


    }
}


class Person {


    private String name;
    private Integer age;
    private Integer fk;

    public Integer getFk() {
        return fk;
    }

    public void setFk(Integer fk) {
        this.fk = fk;
    }

    public Person(String name, Integer age, Integer fk) {
        this.name = name;
        this.age = age;
        this.fk = fk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
