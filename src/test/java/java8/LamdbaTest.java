package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LamdbaTest {

    public static void main(String[] args) {
        int initialCapacity = 10000000;
        List<Integer> originList = new ArrayList<>(initialCapacity);
        List<Integer> forList = new ArrayList<>(initialCapacity / 2);
        List<Integer> lambdaList = new ArrayList<>(initialCapacity / 2);

        for (int i = 0; i < initialCapacity; i++) {
            originList.add(i);
        }

        Long startTime = System.currentTimeMillis();

        for (Integer i : originList) {
            if (i.intValue() % 2 == 0) {
                forList.add(i);
            }
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("for 循环消耗时间=" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        lambdaList = originList.stream().filter(i -> i.intValue() % 2 == 0).collect(Collectors.toList());
        endTime = System.currentTimeMillis();
        System.out.println("lambda 循环消耗时间=" + (endTime - startTime));

    }
}
