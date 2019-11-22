package gu_pao.p1_design_parrent.code.p4_proxy.myproxy;

import java.lang.reflect.Method;

public class MyProxy {


    private static final String ENTER = "\r\n";

    public static Object newProxyInstance(MyClassLoader classLoader, Class<?>[] interfaces, MyInvocationHandler myInvocationHandler) {


        //1 动态生成源代码
        String srcClass = generateSrc(interfaces);

        //2 类加载器加载

        //3
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
        sb.append("public final class $Proxy0 implements " + interfaces[0].getName() + " {").append(ENTER);
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
            sb.append("Method method=" + interfaces[0]).append(ENTER);
            sb.append("myInvocationHandler.invoke(this,);").append(ENTER);
            sb.append("} catch (Exception e) {").append(ENTER);
            sb.append(" e.printStackTrace();").append(ENTER);
            sb.append("}").append(ENTER);
            sb.append("}").append(ENTER);
        }

        sb.append("}").append(ENTER);

        return null;
    }
}
