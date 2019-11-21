package gu_pao.p1_design_parrent.code.p2_single;

import gu_pao.p1_design_parrent.code.p2_single.pojo.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

public class SingleTest {

    public static void main(String[] args) {
        //1 线程不安全方式
        //threadUnSafe();

        //2 线程安全方式
        //threadSafe();

        //3 单层检查 线程不安全
        //lazyDoubleCheckThreadUnSafe();

        //4 双重检查 线程安全
        //lazyDoubleCheckThreadSafe();

        //5 静态内部类
        //staticInnerClass();

        //6 反射突破--及解决
        //fanShe();

        //7 序列化突破--及解决-----点进方法查看分析
        //serializable();

        //注册式单例：将每一个实例都缓存到统一的容器中，使用唯一的标示获取实例
        //8.1枚举(最安全->从jdk底层优化)根据 类名+枚举名 去jvm中去拿[注册式](jvm只会创建一次这个实例)
        //enumTest();

        //8.2容器式(Spring的ioc容器)
        //container();


        //9 ThreadLocal实现(伪线程安全-只是线程内的安全，跨线程是不安全的[非单例]),注册式单例
        threadLocal();

    }

    /**
     * return threadLocal.get();
     * <p>
     * ThreadLocalMap map = getMap(t);
     * 注册式单例类似于ioc
     * ThreadLocalMap的key是线程名称,value是自己存入的值
     * <p>
     * 场景：使用threadlocal实现数据源动态切换
     */
    private static void threadLocal() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread() + ":" + ThreadLocalSingleton.getInstance());
        }

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread() + ":" + ThreadLocalSingleton.getInstance());

                }
            }).start();
        }

    }

    private static void container() {

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Object bean = ContainerSingle.getBean("gu_pao.p1_design_parrent.code.p2_single.SingleTest");
                    System.out.println(+System.currentTimeMillis() + ":" + bean);
                }
            }).start();
        }

    }

    private static void enumTest() {
        //jad查看反编译
        //没有无参构造
        //实例创建是在静态代码(懒汉模式)(静态)线程安全

        //序列化测试
        //serializableEnum();

        //反射测试
        fanSheEnum();


    }

    /**
     * 序列化读取：objectIn.readObject();//最终是从jvm中根据类名+枚举名获取
     * case TC_ENUM:
     * return checkResolve(readEnum(unshared));
     * readEnum(unshared)
     * Enum<?> en = Enum.valueOf((Class)cl, name);  //根据类名+枚举名去jvm中获取
     * result = en;
     */
    private static void serializableEnum() {
        try {
            {
                {
                    EnumSingleton instance = EnumSingleton.getInstance();
                    //写对象
                    FileOutputStream out = new FileOutputStream("fileObjEnum.txt");
                    ObjectOutputStream objOut = new ObjectOutputStream(out);
                    objOut.writeObject(instance);
                    objOut.flush();
                    objOut.close();

                    //读对象
                    FileInputStream inputStream = new FileInputStream("fileObjEnum.txt");
                    ObjectInputStream objectIn = new ObjectInputStream(inputStream);
                    Object o = objectIn.readObject();//
                    System.out.println("--------- 序列化读取 -----------");
                    System.out.println(o);
                    System.out.println(instance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 反编译查看：没有无参构造方法；只有带参构造(String s,int i)
     * <p>
     * java.lang.IllegalArgumentException: Cannot reflectively create enum objects
     * <p>
     * Object o = con.newInstance("tom", 123);
     * <p>判断如果是枚举-就不能反射  clazz.getModifiers() 返回这个类的接口或修饰符
     * if ((clazz.getModifiers() & Modifier.ENUM) != 0)
     * throw new IllegalArgumentException("Cannot reflectively create enum objects");
     */
    private static void fanSheEnum() {
        try {

            EnumSingleton e1 = EnumSingleton.getInstance();
            Class clazz = EnumSingleton.class;
            //Constructor con = clazz.getDeclaredConstructor(null);
            Constructor con = clazz.getDeclaredConstructor(String.class, int.class);
            con.setAccessible(true);
            Object o = con.newInstance("tom", 123);
            System.out.println(e1 == o);
            System.out.println(e1);
            System.out.println(o);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * jdk提供readResolve方法就是考虑到了 单例被破坏的情况
     * ////
     * ////
     * 底层判读(即使初始化过，还会看有没有readResover方法,如果有会通过反射调用)
     * readResolve,只不过是覆盖了反序列化出来的对象，还是创建了两次，发生在jvm层面，相对来说比较安全，之前反序列化出来的对象会被gc回收
     * SerObj o1 = (SerObj)objectIn1.readObject(); 查看源码
     * Object obj = readObject0(false);
     * case TC_OBJECT:
     * return checkResolve(readOrdinaryObject(unshared));
     * readOrdinaryObject 创建实例
     * 会先创建一个实例--然后在判断有没有 readResolve方法，有就会通过反射创建一个对象-覆盖原来的对象 desc.hasReadResolveMethod()
     * if (obj != null &&
     * handles.lookupException(passHandle) == null &&
     * desc.hasReadResolveMethod())
     * {
     * <p>
     * 判断有没有  readResolve 方法  desc.hasReadResolveMethod()
     * readResolveMethod = getInheritableMethod(
     * cl, "readResolve", null, Object.class);
     */
    private static void serializable() {
        StaticInnerClass instance = StaticInnerClass.getInstance();
        try {
            //写对象
            FileOutputStream out = new FileOutputStream("fileObj.txt");
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(instance);
            objOut.flush();
            objOut.close();

            //读对象
            FileInputStream inputStream = new FileInputStream("fileObj.txt");
            ObjectInputStream objectIn = new ObjectInputStream(inputStream);
            Object o = objectIn.readObject();
            System.out.println("--------- 序列化读取 -----------");
            System.out.println(o);
            System.out.println(instance);


            //解决
            System.out.println("--------- 序列化创建对象方式解决 ----------");
            SerObj instance1 = SerObj.getInstance();
            //写对象
            FileOutputStream out1 = new FileOutputStream("fileObj3.txt");
            ObjectOutputStream objOut1 = new ObjectOutputStream(out1);
            objOut1.writeObject(instance1);
            objOut1.flush();
            objOut1.close();

            //读对象
            FileInputStream inputStream1 = new FileInputStream("fileObj3.txt");
            ObjectInputStream objectIn1 = new ObjectInputStream(inputStream1);
            SerObj o1 = (SerObj) objectIn1.readObject();//
            System.out.println(o1);
            System.out.println(instance1);


        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }

    private static void fanShe() {
        try {
            Class<?> clazz = StaticInnerClass.class;

            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(null);
            declaredConstructor.setAccessible(true);//访问私有
            Object o = declaredConstructor.newInstance();

            StaticInnerClass instance = StaticInnerClass.getInstance();
            System.out.println("------->>>>>>>>>>>>");
            System.out.println(o + "\n" + instance);


            System.out.println("----------------- 反射漏洞解决 ------------------");
            Class<?> clazz2 = FanShe.class;
            Constructor<?> constructor = clazz2.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            Object o1 = constructor.newInstance();
            FanShe instance1 = FanShe.getInstance();
            System.out.println(o1);
            System.out.println(instance1);

            System.out.println("-------------  readResover -------------");
            Lazy l = Lazy.getInstance();
            Class<?> c = Lazy.class;
            Constructor<?> con = c.getDeclaredConstructor(null);
            con.setAccessible(true);
            Object o2 = con.newInstance();
            System.out.println(l == o2);


        } catch (Exception e) {
            //java.lang.reflect.InvocationTargetException
            System.out.println("捕获到异常：" + e.toString());
        }


    }


    private static void staticInnerClass() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(StaticInnerClass.getInstance());
                }
            }).start();
        }


    }


    private static void lazyDoubleCheckThreadUnSafe() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(LazyDoubleCheck.getInstanceThreadUnSafeOneCheck());
                }
            }).start();
        }
    }

    private static void lazyDoubleCheckThreadSafe() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(LazyDoubleCheck.getInstanceThreadSafe());
                }
            }).start();
        }
    }

    private static void threadSafe() {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Lazy.getInstanceThreadSafe());
                }
            }).start();
        }


    }

    private static void threadUnSafe() {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Lazy.getInstance());
                }
            }).start();
        }
    }
}
