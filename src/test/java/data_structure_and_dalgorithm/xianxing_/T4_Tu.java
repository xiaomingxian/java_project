package data_structure_and_dalgorithm.xianxing_;

import data_structure_and_dalgorithm.pojo.DingDian;
import data_structure_and_dalgorithm.pojo.Tu;
import org.junit.Test;

public class T4_Tu {

    /**
     * 无向图测试
     */
    @Test
    public void tuTest() {
        DingDian a = new DingDian("A");
        DingDian b = new DingDian("B");
        DingDian c = new DingDian("C");
        DingDian d = new DingDian("D");
        DingDian e = new DingDian("E");

        Tu tu = new Tu(5);

        tu.add(a);
        tu.add(b);
        tu.add(c);
        tu.add(d);
        tu.add(e);

        tu.addLine(a, c);
        tu.addLine(a, b);
        tu.addLine(b, c);
        tu.addLine(b, d);
        tu.addLine(b, e);

        //矩阵遍历--正确
        for (int i = 0; i < tu.jz.length; i++) {
            for (int j = 0; j < tu.jz[i].length; j++) {
                System.out.print(tu.jz[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println("----------------------");
        //    深度优先遍历---略
        tu.shenShow();

        System.out.println("----------------------");
        //    广度优先遍历--略
        guangShow(tu);

    }

    private void guangShow(Tu tu) {

    }

    private void shenShow(Tu tu) {
        // 使用栈


    }
}
