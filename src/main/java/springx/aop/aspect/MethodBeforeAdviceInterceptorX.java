package springx.aop.aspect;

import springx.aop.intercept.MethodInterceptorX;
import springx.aop.intercept.MethodInvocationX;

import java.lang.reflect.Method;

public class MethodBeforeAdviceInterceptorX extends AbstractAspectAdviceX implements MethodInterceptorX {


    private JoinPointX joinPointX;

    public MethodBeforeAdviceInterceptorX(Method aspectMethod, Object aspectTartget) {
        super(aspectMethod, aspectTartget);
    }

    private void before(Method method, Object[] args, Object target) throws Throwable {
        //method.invoke(target, args);
        super.invokeAdviceMethod(this.joinPointX,null,null);
    }


    @Override
    public Object invoke(MethodInvocationX methodInvocationX) throws Throwable {
        this.joinPointX = methodInvocationX;
        before(methodInvocationX.getMethod(),methodInvocationX.getArguments(),methodInvocationX.getThis());
        return methodInvocationX.proceed();
    }
}
