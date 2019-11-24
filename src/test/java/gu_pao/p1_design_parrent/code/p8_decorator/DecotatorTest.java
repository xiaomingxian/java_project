package gu_pao.p1_design_parrent.code.p8_decorator;

import gu_pao.p1_design_parrent.code.p8_decorator.pojo.BaseCake;
import gu_pao.p1_design_parrent.code.p8_decorator.pojo.EggCakeDecorator;
import gu_pao.p1_design_parrent.code.p8_decorator.pojo.ShanDongCake;
import gu_pao.p1_design_parrent.code.p8_decorator.pojo.XiangChangCakeDecorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class DecotatorTest {

    public static void main(String[] args) {

        //1 煎饼案例
        //cakeDemo();


        //2 ioDemo
        ioDemo();

    }

    private static void ioDemo() {
        try {

            //InputStream 是顶层接口 FileInputStream是基本的实现类(被装饰者)
            // 这样的被装饰者还有 ByteInputStream,FilterInputStream #读取数据源的方式
            InputStream fileInputStream = new FileInputStream("");

            //2 进行装饰
            //这样的装饰者还有 DataInputStream,PushbackInputStream
            InputStream bufferedInputStream = new BufferedInputStream(fileInputStream);


            //3 具体操作
            byte[] by = new byte[1024];
            int len = 0;
            while ((len = bufferedInputStream.read(by)) != -1) {
                System.out.println(new String(by, 0, len));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void cakeDemo() {


        BaseCake shanDongCake = new ShanDongCake();

        //一层增强
        shanDongCake = new EggCakeDecorator(shanDongCake);
        //2层
        shanDongCake = new XiangChangCakeDecorator(shanDongCake);
        //3层
        shanDongCake = new XiangChangCakeDecorator(shanDongCake);
        //4层
        shanDongCake = new XiangChangCakeDecorator(shanDongCake);
        //n层...

        System.out.println("描述：" + shanDongCake.getMsg() + " \n总价： " + shanDongCake.getPrice());

    }
}
