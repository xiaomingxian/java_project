package data_structure_and_dalgorithm.pojo;

import java.util.ArrayList;

public class Tu {

    //    数组存放顶点
    public DingDian[] dd;
    int currentSize;

    //    矩阵记录 连线情况
    public int[][] jz;

    //初始化
    public Tu(int size) {
        dd = new DingDian[size];
        jz = new int[size][size];
        //对角线默认为连线状态
        for (int i = 0; i < jz.length; i++) {
            jz[i][i] = 1;
        }
    }

    //    添加顶点
    public void add(DingDian dingDian) {
        dd[currentSize++] = dingDian;
    }

    //    添加连线
    public void addLine(DingDian d1, DingDian d2) {
        //    找到d1坐标 找到d2坐标进行连线
        int index1 = 0;
        for (int i = 0; i < dd.length; i++) {
            if (d1 == dd[i]) {
                index1 = i;
                break;
            }
        }

        int index2 = 0;
        for (int i = 0; i < dd.length; i++) {
            if (d2 == dd[i]) {
                index2 = i;
                break;
            }
        }
        //    进行连线
        jz[index1][index2] = 1;
        jz[index2][index1] = 1;
    }

    /**
     * 深度优先遍历
     */
    public void shenShow() {
        //System.out.println("--->"+currentSize);
        //ArrayList zhan = new ArrayList();
        //dd[0].isFang = true;
        //System.out.print(dd[0] + ",");
        //zhan.add(dd[0]);
        //while (zhan.size() > 0) {
        //    for (int i = 0; i < dd.length-1; i++) {
        //        //检查下一个顶点是否与之连线--且未访问【避免重复遍历】
        //        if (jz[i][i+1] == 1 && dd[i+1].isFang == false) {
        //            zhan.add(dd[i+1]);
        //            dd[i+1].isFang = true;
        //        }
        //    }
        //}
    }

}
