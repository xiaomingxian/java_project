package design_parrent.test;

import design_parrent.pojo.p5_adeptor.Adeptee;
import design_parrent.pojo.p5_adeptor.Adepter;
import design_parrent.pojo.p5_adeptor.Adepter2;
import design_parrent.pojo.p5_adeptor.Client;
import org.junit.Test;

/**
 * 适配器
 * 结构性模型
 */
public class T5_ShiPeiQi {

    /**
     * 场景：
     * 用户用计算机打字    Client
     * 计算机接口为USB     Target
     * 键盘接口为ps/2       Adertee
     * 需要转接头            Apepter
     * 计算机直接用转接头 [适配]
     *
     */


    /**
     * 方案1：继承
     * 缺陷：不考虑引入其他类，只能对单一的类进行适配
     */
    @Test
    public void ext() {
        //客户端
        Client client = new Client();

        //适配器
        //继承被代理类----为了super.xxx() 方式进行调用 --- 具体方法的转换
        //实现 Target----为了进行多态形式进行调用 --- 适配调用
        Adepter adepter = new Adepter();

        client.write(adepter);//需要的是usb但是只有ps/2的键盘----需要传入转接类

    }

    /**
     * 方案2：对象组合，持有被适配类的引用
     */
    @Test
    public void ade2() {
        //客户端
        Client client = new Client();
        //被适配对象---为适配器提供引用
        Adeptee adeptee = new Adeptee();
        //适配器--传入被适配对象的引用--并在内部[实现了目标类-重写相应方法]进行调用
        Adepter2 adepter2 = new Adepter2(adeptee);

        client.write(adepter2);

    }

}
