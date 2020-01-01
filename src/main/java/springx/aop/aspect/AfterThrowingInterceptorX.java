package springx.aop.aspect;

import springx.aop.intercept.MethodInterceptorX;
import springx.aop.intercept.MethodInvocationX;

import java.lang.reflect.Method;

public class AfterThrowingInterceptorX  extends AbstractAspectAdviceX implements MethodInterceptorX {
    public AfterThrowingInterceptorX(Method aspectMethod, Object aspectTartget) {
        super(aspectMethod, aspectTartget);
    }

    @Override
    public Object invoke(MethodInvocationX methodInvocationX) throws Throwable {
        return null;
    }
}
