package jvm.test.T13_ThreadPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class T7_ForkJoin {
    /**
     * 分而治之
     *
     * @param args
     */


    public static void main(String[] args) {
        //也是精灵线程
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(new MyTask());

    }

    static class MyTask extends RecursiveAction {//        RecursiveTask---有返回值
        int i = 100;

        public MyTask() {

        }


        public MyTask(int i) {
            this.i = i;
        }

        @Override
        protected void compute() {
            System.out.println("----------");//打印了四次
            if (i < 1) {

            } else {
                i -= 20;
                MyTask myTask = new MyTask(i);//也是递归
                //子任务---开辟了新线程
                myTask.fork();

            }


        }
    }
}
