package jvm.test.t14_classLoader;

import org.junit.Test;

public class T5_ClassLoader {
    @Test
    public void t1() throws Exception {
        Class<?> aClass = Class.forName("java.lang.String");
        ClassLoader classLoader = aClass.getClassLoader();
        System.out.println(classLoader);//null
        //由于BootStrap ClassLoader是用c++写的，所以在返回该ClassLoader时会返回null


        Class<?> aClass1 = Class.forName("jvm.test.t14_classLoader.T5_ClassLoader");
        System.out.println(aClass1.getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2

    }


    /**
     * 加载 不会导致初始化
     *
     * @throws Exception
     */
    @Test
    public void tt() throws Exception {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> aClass = systemClassLoader.loadClass("jvm.test.t14_classLoader.T1");
        System.out.println(aClass);
    }
}

class T1 {
    //检测初始化
    static {
        System.out.println("-------");
    }
}
