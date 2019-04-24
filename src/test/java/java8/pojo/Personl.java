package java8.pojo;

public class Personl {
    String name;
    int age;
    long monery;

    public Personl(String name, int age, long monery) {
        this.name = name;
        this.age = age;
        this.monery = monery;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getMonery() {
        return monery;
    }

    public void setMonery(long monery) {
        this.monery = monery;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", monery=" + monery +
                '}';
    }
}
