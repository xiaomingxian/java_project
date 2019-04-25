package java8.test;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class T4_ConstroctRef {
    /**
     * 无参
     */
    @Test
    public void noParm() {
        //供给型
        Supplier<String> p = String::new;

    }

    /**
     * 带参
     */
    @Test
    public void oneParm() {
        Function<String, StringBuffer> c = StringBuffer::new;
        Function<String, String> s = String::new;
        Function<String, Animal> a = Animal::new;

        BiFunction<String, Integer, Animal> b = Animal::new;
    }

    /**
     * 数组
     */
    @Test
    public void array() {
        Function<Integer, String[]> arr = String[]::new;
        String[] apply = arr.apply(7);
        System.out.println(apply.length);
    }


    class Animal {
        public Animal(String s) {
        }

        public Animal(String s, int i) {
        }
    }

}