package gu_pao.p1_design_parrent.code.p4_proxy.myproxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyProxy {


    private static final String ENTER = "\r\n";

    public static Object newProxyInstance(MyClassLoader classLoader, Class<?>[] interfaces, MyInvocationHandler myInvocationHandler) {

        try {


            //1 动态生成源代码
            String srcClass = generateSrc(interfaces);

            //2 输出java文件到磁盘
            //MyProxy所在文件的绝对路径(因为生成源代码的时候用的是这个路径)
            String pathAll = MyProxy.class.getResource("").getPath();
            String $Proxy0 = pathAll + "$Proxy0.java";
            //创建文件-写入内容
            FileWriter fileWriter = new FileWriter($Proxy0);
            fileWriter.write(srcClass);
            fileWriter.flush();
            fileWriter.close();

            //3 编译java文件为class文件
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects($Proxy0);
            JavaCompiler.CompilationTask task = javaCompiler.getTask(null, fileManager, null, null, null, javaFileObjects);
            task.call();
            fileManager.close();
            //4 加载编译生成的class文件到jvm
            Class<?> $Proxy01 = classLoader.findClass("$Proxy0");
            //构造的代理类需要传入 MyInvocationHandler 参数
            Constructor<?> constructor = $Proxy01.getConstructor(MyInvocationHandler.class);
            //删除虚拟代理类
            File file = new File($Proxy0);
            file.delete();

            return constructor.newInstance(myInvocationHandler);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        //*********************************************  导包
        //动态生成代理类 $Proxy0
        sb.append("package gu_pao.p1_design_parrent.code.p4_proxy.myproxy;").append(ENTER);
        //导入InvocationHandler用来增强方法
        sb.append("import gu_pao.p1_design_parrent.code.p4_proxy.myproxy.MyInvocationHandler;").append(ENTER);
        //导入Proxy类 用于产生代理类
        sb.append("import gu_pao.p1_design_parrent.code.p4_proxy.myproxy.MyProxy;").append(ENTER);
        //导入反射方法
        sb.append("import java.lang.reflect.Method;").append(ENTER);


        //*********************************************** 创建类
        //代理类 实现 接口
        sb.append("public  class $Proxy0 implements " + interfaces[0].getName() + " {").append(ENTER);
        //MyInvocationHandler用来做方法增强 [构造方法]
        sb.append("private MyInvocationHandler myInvocationHandler;").append(ENTER);
        sb.append("public $Proxy0(MyInvocationHandler myInvocationHandler){").append(ENTER);
        sb.append("this.myInvocationHandler=myInvocationHandler;").append(ENTER);
        sb.append("}").append(ENTER);


        //********************************************** 方法构造
        //普通方法
        Method[] methods = interfaces[0].getMethods();
        for (Method method : methods) {
            //假设是无参方法
            sb.append("public " + method.getReturnType().getName() + " " + method.getName() + "(){").append(ENTER);
            //捕获异常
            sb.append("try {").append(ENTER);
            //执行增强后的代码
            //以下代码类似与这种形式(此处简化，假设是无参方法) MyProxy.class.getMethod("xxx")
            sb.append("Method m=" + interfaces[0].getName() + ".class.getMethod(\"" + method.getName() + "\");").append(ENTER);
            sb.append("myInvocationHandler.invoke(this,m,null);").append(ENTER);
            sb.append("} catch (Throwable e) {").append(ENTER);
            sb.append(" e.printStackTrace();").append(ENTER);
            sb.append("}").append(ENTER);
            //此处简化处理了
            sb.append("return null;").append(ENTER);
            sb.append("}").append(ENTER);
        }


        sb.append("}").append(ENTER);


        System.out.println(sb.toString());

        return sb.toString();
    }
}
