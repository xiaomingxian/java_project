package design_parrent.test;

import design_parrent.pojo.p7_composite.AbstractFile;
import design_parrent.pojo.p7_composite.Folder;
import design_parrent.pojo.p7_composite.ImgFile;
import design_parrent.pojo.p7_composite.TextFile;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import org.junit.Test;

import java.util.Objects;

/**
 * 组合模式
 * 处理树形结构---对比数据结构中的树结构
 * <p>
 * Component 抽象构件
 * Leaf      叶子构件  叶子节点
 * Composite 容器构件  父节点
 */
public class T7_Composite {

    /**
     * 简易功能
     * 删除[简易模式]--->递归删除--忽略
     */
    @Test
    public void t1() {

        AbstractFile root = new Folder("根目录");
        AbstractFile f1 = new Folder("一级目录");
        AbstractFile f2 = new Folder("二级目录");
        AbstractFile f3 = new Folder("3级目录");

        AbstractFile javaImg = new ImgFile("java.jpg");
        AbstractFile pythonImg = new ImgFile("python.jpg");
        AbstractFile goImg = new ImgFile("go.jpg");

        AbstractFile t1 = new TextFile("水浒传.txt");
        AbstractFile t2 = new TextFile("三国.txt");
        AbstractFile t3 = new TextFile("go.txt");

        root.add(f1);
        root.add(f3);

        f1.add(javaImg);
        f1.add(pythonImg);
        f3.add(t1);
        f2.add(goImg);
        f2.add(t2);
        f3.add(t3);

        f1.add(f2);

        root.killDu();

        System.out.println("目录结构：" + root);

        root.del(f3);

        root.killDu();
        System.out.println("~~~~~~子目录杀毒");
        f1.killDu();
        System.out.println("~~~~~~文件杀毒");
        javaImg.killDu();


    }

    /**
     * 单元 测试是 组合模式//未验证
     */
    public static void main(String[] args) {
        //Test

        //TestSuite testSuite = new TestSuite();//为啥不能多态形式

        //TestCase testCase = new TestCase();
    }

}