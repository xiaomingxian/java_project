package java_se_test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.excelpojo.Person;

import java.text.SimpleDateFormat;

public class T5_Return {

    /**
     * inally的语句确实执行了，而且肯定是在方法return之前执行的，
     * 而且，如果finally中有return语句的话，方法直接结束。
     * 这里需要注意的只有一点：在try中的return语句会将返回结果值压栈，
     * 然后转入到finally子过程，等到finally子过程执行完毕之后（没有return），再返回。
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * return  的如果是 引用类型那么压栈的是 变量的地址值[String类型除外，有常量池机制，压栈的是常量池中的内容]
     * 如果是基本类型 压栈的是具体数值
     */

    Logger logger = LoggerFactory.getLogger(T5_Return.class);


    @Test
    public void finallyTest() {
        //基本类型
        //String s = finallyReturn();
        //String s = refString();
        //System.out.println(s);


        //引用类型
        //自定义类型
        Person person = person();
        System.out.println(person);
        //StringBuffer测试
        StringBuffer stringBuffer = stringBuffer();
        System.out.println(stringBuffer);


    }

    /**
     * String return
     * 遇到return xxx; 会将结果压栈 "a"
     * 在finally中进行修改 也不会对已经压栈的 值进行修改
     *
     * @return
     */
    private String finallyReturn() {
        new SimpleDateFormat("HH:mm:ss");

        String s = "a";

        try {
            return s;
        } catch (Exception e) {
            s = "e";
        } finally {
            s = "b";
            logger.info("finally...");

        }
        return null;
    }

    private String refString() {
        String s;
        try {
            s = new String("xxx");
            return s;

        } catch (Exception e) {
        } finally {
            s = "ssss";

        }
        return null;
    }


    /**
     * 引用类型
     *
     * @return
     */
    private Person person() {

        Person person = null;
        try {
            person = new Person();
            return person;

        } catch (Exception e) {
        } finally {
            person.setName("jerry");

        }
        return person;
    }

    private StringBuffer stringBuffer() {
        StringBuffer stringBuffer = new StringBuffer("xxxxx");
        try {
            return stringBuffer;
        } catch (Exception e) {
        } finally {
            stringBuffer.append("  finally");
        }
        return null;
    }




    @Test
    public void test_1() {
        Person person = new Person();
        p(person);
        System.out.println(person);

        StringBuffer stringBuffer = new StringBuffer("");
        add(stringBuffer);
        System.out.println(stringBuffer.toString());

    }

    private void add(StringBuffer stringBuffer) {
        stringBuffer.append("00000");
    }

    public void p(Person p) {
        p.setName("aaaa");
    }
}
