package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")
public class RedisTest {


    @Autowired
    private RedisTemplate template;


    //redisTemplate.opsForValue();//操作字符串
    //redisTemplate.opsForHash();//操作hash
    //redisTemplate.opsForList();//操作list
    //redisTemplate.opsForSet();//操作set
    //redisTemplate.opsForZSet();//操作有序set
    @Test
    public void stringTest() {

        template.opsForValue().set("java_str", "java_value");
        System.out.println(template.opsForValue().get("java_str"));


    }


    @Test
    public void pushTest() {

        //----------------hash  ----------
        template.boundHashOps("learn_hash").put("k1", "v1");
        System.out.println(template.boundHashOps("learn_hash").get("k?"));

    }


}


//package RedisTest;
//
//        import org.junit.Test;
//        import org.junit.runner.RunWith;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.data.redis.core.RedisTemplate;
//        import org.springframework.test.context.ContextConfiguration;
//        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:applicationContext-redis.xml")
//public class RedisTest {
//
//
//    //hash类型的缓存要注意 ---小key的类型--不会自动转换
//
//    @Autowired
//    private RedisTemplate tem;
//
//
//    @Test
////    public void pushTest() {
////
////        System.out.println(tem.boundHashOps("ad").get("1"));
////    }
////
//    @Test
//    public void StringTest(){
//        tem.boundValueOps("str").set("a");
//        tem.boundValueOps("str").set("c");
//        //覆盖前面的内容
//
//        System.out.println(tem.boundValueOps("str").get());
//
//        tem.delete("str");
//
//        System.out.println(tem.boundValueOps("str").get());
//
//
//
//    }
//    @Test
//    public void ListTest(){
//        tem.boundListOps("list").leftPush("a");
//        tem.boundListOps("list").leftPush("b");
//        tem.boundListOps("list").leftPush("c");
//        tem.boundListOps("list").leftPush("d");
//
//        System.out.println(tem.boundListOps("list").index(1));
//        System.out.println(tem.boundListOps("list").range(0, -1));
//
//
//        tem.boundListOps("list").remove(4, "a");
//        tem.boundListOps("list").remove(4, "c");
//        tem.boundListOps("list").remove(4, "b");
//
//        System.out.println("左弹栈："+tem.boundListOps("list").leftPop());
//        System.out.println(tem.boundListOps("list").range(0, -1));
//        tem.boundListOps("list").trim(0, 1);//保留0-1
//        System.out.println(tem.boundListOps("list").range(0, -1));
//
//
//    }
//
//    @Test
//    public void setTest(){
//        //存储无序不重复
//        tem.boundSetOps("set").add("1");
//        tem.boundSetOps("set").add("1");
//        tem.boundSetOps("set").add("2");
//        tem.boundSetOps("set").add("3");
//
//        tem.boundSetOps("set").remove("2");
//
//        System.out.println(tem.boundSetOps("set").members());
//
//
//    }
//
//
//
//}
//
