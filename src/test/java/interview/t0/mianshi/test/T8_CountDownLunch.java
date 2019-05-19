package interview.t0.mianshi.test;

import lombok.Getter;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class T8_CountDownLunch {


    @Test
    public void t1() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {

            new Thread(() -> {
                System.out.println("---->" + Thread.currentThread().getName());
                countDownLatch.countDown();
            }, Guo.getEle(i).getName()).start();

        }

        countDownLatch.await();
        System.out.println("--->秦");

    }

    @Test
    public void t2() {
        System.out.println(Guo.ONE.getName());
    }


}

enum Guo {

    ONE(1, "赵"),
    TWO(2, "齐"),
    THREE(3, "韩"),
    FOUR(4, "楚"),
    FIVE(5, "燕"),
    SIX(6, "鲁");
    @Getter
    private int code;
    @Getter
    private String name;

    Guo(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Guo getEle(int index) {

        Guo[] values = Guo.values();
        for (Guo g : values) {
            if (index == g.code) {
                return g;
            }
        }
        return null;
    }


}
