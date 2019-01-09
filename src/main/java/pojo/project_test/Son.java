package pojo.project_test;

public class Son extends Parent {

    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Son{" +
                "age='" + age + '\'' +
                "name='" + super.getName() + '\'' +
                '}';
    }
}
