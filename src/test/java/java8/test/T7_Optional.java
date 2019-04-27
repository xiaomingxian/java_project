package java8.test;

import org.junit.Test;

import java.util.Optional;

public class T7_Optional {
    /**
     * optional容器
     */
    @Test
    public void t1() {
        //1
        //Optional<Person> o = Optional.of(null);//java.lang.NullPointerException
        //o.get();

        //2
        //Optional<Person> empty = Optional.empty();//java.util.NoSuchElementException: No value present
        //empty.get();

        //3
        //Optional<Object> o = Optional.ofNullable(null);//综合了以上两种创建方式
        //o.get();
        /**
         public static <T> Optional<T> ofNullable(T value) {
         return value == null ? empty() : of(value);
         }
         */

        //4 是否包含值
        //Optional<Object> o = Optional.ofNullable(null);//综合了以上两种创建方式
        //boolean present = o.isPresent();
        //System.out.println(present);

        //5.orElse  空值替代
        //Optional<Object> o1 = Optional.ofNullable(null);
        //Object xxx = o1.orElse("xxx");
        //System.out.println(xxx);

        //6.orElseGet  -->函数式接口自定义处理
        //Optional<Object> o = Optional.ofNullable(null);
        //Object o1 = o.orElseGet(() -> new Person());
        //System.out.println(o1);


        //7.map(...)如果有值-对返回的值做处理，如果没有返回Optional.empty()
        //Optional<Object> o = Optional.ofNullable(null);
        Optional<Object> o = Optional.ofNullable("xx");
        Optional<String> s = o.map(Object::toString);
        System.out.println(s.get());


        //8.flatMap  与map类似，但是返回值必须包装到Optional中
        //Optional<Object> o1 = Optional.ofNullable(null);
        Optional<Object> o1 = Optional.ofNullable("xx-xx");
        Optional<Object> o2 = o1.flatMap((x) -> Optional.ofNullable(x.toString()
        ));
        System.out.println(o2.get());


    }
}
