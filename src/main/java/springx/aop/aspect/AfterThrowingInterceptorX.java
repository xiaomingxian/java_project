package springx.aop.aspect;

import springx.aop.intercept.MethodInterceptorX;
import springx.aop.intercept.MethodInvocationX;

public class AfterThrowingInterceptorX  implements MethodInterceptorX {
    @Override
    public Object invoke(MethodInvocationX methodInvocationX) throws Throwable {
        return null;
    }
}
