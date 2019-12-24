package springx.aop.intercept;

/**
 * 拦截器链的标准，必须实现此接口
 */
public interface MethodInterceptorX {

    Object invoke(MethodInvocationX methodInvocationX) throws Throwable;
}
