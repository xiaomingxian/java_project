package springx.aop;

import springx.aop.intercept.MethodInvocationX;
import springx.aop.support.AdvisedSupportX;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class JdkDynamicAopProxy implements AopProxyX, InvocationHandler {


    AdvisedSupportX advisedSupportX;

    public JdkDynamicAopProxy(AdvisedSupportX advisedSupportX) {
        //获取被代理类
        this.advisedSupportX = advisedSupportX;
    }


    @Override
    public Object getProxy() {
        return getProxy(this.advisedSupportX.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {


        return Proxy.newProxyInstance(classLoader,
                this.advisedSupportX.getTargetClass().getInterfaces(),
                this);//本类实现类  InvocationHandler 到时候调用的是此类的 invoke方法

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        List<Object> interceptorsAndDynamicMethodMatchers =
                this.advisedSupportX.getInterceptorsAndDynamicMethodMatchers(method, proxy.getClass());


        MethodInvocationX methodInvocationX = new MethodInvocationX(proxy, this.advisedSupportX.getTarget(), method, args,
                this.advisedSupportX.getTargetClass(),
                interceptorsAndDynamicMethodMatchers);
        //每个方法都对应一个执行器链，方法是key,执行器链是val

        return methodInvocationX.proceed();
    }
}
