package java8;

import data_structure_and_dalgorithm.pojo.TreeNode;
import org.junit.Test;

import java.util.HashMap;

public class MianShi {

    /**
     * 1。ip转Long
     *
     * @param ip
     */
    @Test
    public void ipCacu() {
        String ip = "192.68.0.1";
        Long i1 = Long.parseLong(ip.split("\\.")[0]);
        Long i2 = Long.parseLong(ip.split("\\.")[1]);
        Long i3 = Long.parseLong(ip.split("\\.")[2]);
        Long i4 = Long.parseLong(ip.split("\\.")[3]);

        Long res = i1 << 24 + i2 << 16 + i3 << 8 + i4;

        System.out.println(res);
    }

    /**
     * 2。树深度
     */
    @Test
    public void tree() {
        //创建树结构
        TreeNode root = new TreeNode(1);

        TreeNode l1 = new TreeNode(2);
        TreeNode ll2 = new TreeNode(4);
        TreeNode lr2 = new TreeNode(5);
        TreeNode lr2l = new TreeNode(6);
        TreeNode lr2lr = new TreeNode(13);

        TreeNode r1 = new TreeNode(3);
        TreeNode r2r = new TreeNode(7);
        TreeNode r2rl = new TreeNode(8);
        TreeNode r2rll = new TreeNode(9);
        TreeNode r2rlll = new TreeNode(10);
        TreeNode r2rlllr = new TreeNode(12);
        TreeNode r2rllr = new TreeNode(11);


        root.setLeftNode(l1);
        l1.setLeftNode(ll2);
        l1.setRightNode(lr2);
        lr2.setLeftNode(lr2l);
        lr2l.setRightNode(lr2lr);

        root.setRightNode(r1);
        r1.setRightNode(r2r);
        r2r.setLeftNode(r2rl);
        r2rl.setLeftNode(r2rll);
        r2rll.setLeftNode(r2rlll);
        r2rll.setRightNode(r2rllr);
        r2rlll.setRightNode(r2rlllr);

        //求深度-递归
        int i = treeDepth(root);
        System.out.println(i);


    }

    /**
     * 3。电梯
     * 假设每个电梯能坐20人
     * <p>
     * <p>
     * 思路：电梯载人的时候 判断三个电梯所在楼层及电梯内人数 ：选择距离此楼最近以及能容量最合适的电梯
     * 未完。。。。
     * 时间关系 未考虑完善
     * 此处实现：仅依据楼层关系进行判断[电梯载人无限]，容量最合适的电梯功能未做,高峰期未考虑
     *
     * @return
     */
    @Test
    public void dianTi() {
        //int fs = 12;
        //int dts = 3;
        //int gfs = 2;
        //int pass = 5;
        //int wait = 20;
        //int per = 100;

        int[] pers = new int[12];
        for (int i = 0; i < 12; i++) pers[i] = 100;

        //int[] ds = {0, 1, 2};
        HashMap<String, Integer[]> ds = new HashMap<>();
        Integer[] i1 = {0, 0};
        Integer[] i2 = {0, 0};
        Integer[] i3 = {0, 0};
        ds.put("1", i1);
        ds.put("2", i2);
        ds.put("3", i3);

        Long time = 0L;
        //循环楼层
        for (int i = 0; i < 12; i++) {


            //判断人数---判断电梯所在楼层[且电梯未满]
            int pnum = pers[i];
            //不停留
            if (pnum == 0) {
                continue;
            } else {
                //    dt wait-选择哪一个电梯-送到0楼
                Integer[] n1 = ds.get("1");
                Integer[] n2 = ds.get("2");
                Integer[] n3 = ds.get("3");
                //所在楼层
                int f1 = n1[0];
                int f2 = n2[0];
                int f3 = n3[0];
                //电梯内人数
                int ps1 = n1[1];
                int ps2 = n2[1];
                int ps3 = n3[1];
                //停留楼层
                Integer[] f = null;
                Math.abs(f1 - i);
                Math.abs(f2 - i);
                Math.abs(f3 - i);
                //最少等待时间
                int x = Math.abs(f1 - i) < Math.abs(f2 - i) ? (Math.abs(f1 - i) < Math.abs(f3 - i) ? Math.abs(f1 - i) : Math.abs(f3 - i)) :
                        (Math.abs(f2 - i) < Math.abs(f3 - i) ? Math.abs(f2 - i) : Math.abs(f3 - i));
                if (x == Math.abs(f1 - i)) f = n1;
                if (x == Math.abs(f2 - i)) f = n2;
                if (x == Math.abs(f3 - i)) f = n3;


                int peop = pers[i];

                //    计算楼层差值
                if (f[0] < i) {
                    //    上移+下移
                    time += (i - f[0]) * 5 + i * 5 + 20;
                    pers[i] = peop - 20;


                } else {
                    time += (f[0] - i) * 5 + 20 + i * 5;
                    pers[i] = peop - 20;

                }

            }

        }

        System.out.println("时间：" + time);


    }


    public static int treeDepth(TreeNode root) {
        if (root == null)
            return 0;
        else {
            int left = treeDepth(root.getLeftNode());
            int right = treeDepth(root.getRightNode());
            return 1 + Math.max(left, right);
        }
    }

}
