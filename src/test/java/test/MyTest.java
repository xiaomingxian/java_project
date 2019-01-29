package test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Parent {

    private String name = "parent";

    void print() {

        System.out.println(this.name);
    }
}

class Son extends Parent {
    private String name = "son";

//    void print() {
//
//        System.out.println(this.name);
//    }

//    即使子类声明了与父类完全一样的成员变量，也不会覆盖掉父类的成员变量。
// 而是在子类实例化时，会同时定义两个成员变量，
// 子类也可以同时访问到这两个成员变量(this.i&super.i)，
// 但父类不能访问到子类的成员变量（父类不知道子类的存在）。
//
//    而具体在方法中使用成员变量时，
// 究竟使用的是父类还是子类的成员变量，
// 则由方法所在的类决定；即，方法在父类中定义和执行，
// 则访问的是父类的成员变量，
// 方法在子类中定义（包括覆盖父类方法）和执行，则访问的是子类的成员变量。


}

public class MyTest {
    /**
     * 解码 Unicode \\uXXXX
     * @param str
     * @return
     */
    public static String decodeUnicode(String str) {
        Charset set = Charset.forName("UTF-16");
        Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher m = p.matcher( str );
        int start = 0 ;
        int start2 = 0 ;
        StringBuffer sb = new StringBuffer();
        while( m.find( start ) ) {
            start2 = m.start() ;
            if( start2 > start ){
                String seg = str.substring(start, start2) ;
                sb.append( seg );
            }
            String code = m.group( 1 );
            int i = Integer.valueOf( code , 16 );
            byte[] bb = new byte[ 4 ] ;
            bb[ 0 ] = (byte) ((i >> 8) & 0xFF );
            bb[ 1 ] = (byte) ( i & 0xFF ) ;
            ByteBuffer b = ByteBuffer.wrap(bb);
            sb.append( String.valueOf( set.decode(b) ).trim() );
            start = m.end() ;
        }
        start2 = str.length() ;
        if( start2 > start ){
            String seg = str.substring(start, start2) ;
            sb.append( seg );
        }
        return sb.toString() ;
    }


    public static void main(String[] args) throws Exception {


        String s = new String("&#x5317;&#x4EAC".getBytes("unicode"), "utf-8");

        String decode = URLDecoder.decode("&#x5317;&#x4EAC", "unicode");
        System.out.println(decodeUnicode("&#x5317;&#x4EAC"));

        //return 机制  return拷贝

//        System.out.println(getNum());
//        System.out.println(getNum2());
//
//        new Son().print();

//
//        excTest();


    }

    private static void excTest() {
        //        try {
        //            int x = 1 / 0;
        //        } catch (Exception e) {
        //
        //            throw  new Exception("xxx");
        //        }
    }


    static void change(List list) {
        list = new ArrayList();
        //arrayList.add("1");
        list.add("vv");

    }


    static String getNum() {
        //return 机制

        String A = null;
        try {
            A = "try";
            return A;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            A = "finally";
            //return A;
        }
        return null;


    }

    static String getNum2() {
        //return 机制

        String A = null;
        try {
            A = "try";
            return A;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            A = "finally";
            return A;
            //return "FIN";
        }


    }
}
