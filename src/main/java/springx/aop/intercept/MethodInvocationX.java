package springx.aop.intercept;

import springx.aop.aspect.JoinPointX;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodInvocationX implements JoinPointX {


    private Object proxy;
    private Object target;
    private Method method;
    private Object[] arguments;
    private Class<?> targetClass;
    private List<Object> interceptorsAndDynamicMethodMatchers;

    //Map存储自定义属性
    private Map<String, Object> userAttribute;


    //定义一个索引，从-1开始，记录当前拦截器执行的位置
    private int currentInterceptorIndex = -1;

    public MethodInvocationX() {
    }

    public MethodInvocationX(Object proxy, Object target, Method method, Object[] arguments
            , Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {

        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.targetClass = targetClass;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    /**
     * 执行拦截器链
     */
    public Object proceed() throws Throwable {

        //如果interceptor执行完毕了，则执行joinpoint
        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
            //执行链的size-1 如果执行链没有内容size=0 减1就是-1
            return this.method.invoke(this.target, this.arguments);
        }
        //如果执行链有内容就一个个拿出来执行
        Object interceptorOrInterceptionAdvice = this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);

        //如果要动态匹配 joinPoint
        if (interceptorOrInterceptionAdvice instanceof MethodInterceptorX) {
            //抢转调用
            MethodInterceptorX methodInterceptorX = (MethodInterceptorX) interceptorOrInterceptionAdvice;
            return methodInterceptorX.invoke(this);
        } else {
            //不匹配当前-略过(调用下一个)
            return proceed();
        }
    }


    @Override
    public Object getThis() {
        return null;
    }

    @Override
    public Object[] getArguments() {
        return new Object[0];
    }

    @Override
    public Method getMethod() {
        return null;
    }


    public void setUserAttribute(String key, Object value) {
        if (value != null) {
            if (this.userAttribute == null) {
                this.userAttribute = new HashMap<String, Object>();
            }
            this.setUserAttribute(key, value);
        } else {
            if (this.userAttribute != null) {
                //????
                this.userAttribute.remove(key);
            }

        }

    }

    @Override
    public Object getUserAttribute(String key) {


        return (this.userAttribute == null ? this.userAttribute.get(key) : null);
    }
}
