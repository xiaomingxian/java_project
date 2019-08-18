package interview.t0.mianshi.test;

import java.util.ArrayList;
import java.util.Objects;

public class T21_Clone implements Cloneable {

    private String name;
    private int age;
    private NeiBu neiBu;

    public T21_Clone() {
    }

    public T21_Clone(String name, Integer age, NeiBu neiBu) {
        this.name = name;
        this.age = age;
        this.neiBu = neiBu;
    }
    //test只能有无参构造    Test class can only have one constructor
    //@Test
    //public void t1(){
    //
    //
    //
    //}

    public static void main(String[] args) {
        T21_Clone t21_clone = new T21_Clone("tom", 10, new NeiBu());
        t21_clone.setAge(11);
        t21_clone.setName("Jerry");

        ArrayList l=null;
        try {

            T21_Clone clone = (T21_Clone) t21_clone.clone();
            //Clone是浅拷贝
            System.out.println("--->" + clone);
            System.out.println(t21_clone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        //Double d=10.0;
        //switch (d){}

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

    public NeiBu getNeiBu() {
        return neiBu;
    }

    public void setNeiBu(NeiBu neiBu) {
        this.neiBu = neiBu;
    }


    @Override
    public String toString() {
        return this.name + "  " + this.age+"  nclass:"+neiBu;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        T21_Clone t21_clone = (T21_Clone) super.clone();
        t21_clone.neiBu = (NeiBu) neiBu.clone();

        return t21_clone;
    }

    //静态内部类
    static class NeiBu implements Cloneable {
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }


    @Override
    public boolean equals(Object o) {
        //地址值
        if (this == o) return true;
        // null值 || 类型不同
        if (o == null || getClass() != o.getClass()) return false;
        T21_Clone t21_clone = (T21_Clone) o;
        //属性是否相同
        return age == t21_clone.age &&
                Objects.equals(name, t21_clone.name) &&
                Objects.equals(neiBu, t21_clone.neiBu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, neiBu);
    }
}


