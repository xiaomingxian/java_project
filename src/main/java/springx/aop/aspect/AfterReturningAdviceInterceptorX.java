package springx.aop.aspect;

import springx.aop.intercept.MethodInterceptorX;
import springx.aop.intercept.MethodInvocationX;

import java.lang.reflect.Method;

public class AfterReturningAdviceInterceptorX extends AbstractAspectAdviceX implements AdviceX, MethodInterceptorX {



    private JoinPointX joinPointX;



    public AfterReturningAdviceInterceptorX(Method aspectMethod, Object aspectTartget) {
        super(aspectMethod, aspectTartget);
    }

    @Override
    public Object invoke(MethodInvocationX methodInvocationX) throws Throwable {

        Object returnVal = methodInvocationX.proceed();
        this.joinPointX = methodInvocationX;
        this.afterReturning(returnVal, methodInvocationX.getMethod(),
                methodInvocationX.getArguments(), methodInvocationX.getThis());
        return returnVal;
    }


    private void afterReturning(Object retVal, Method method, Object[] arguments, Object thisx) throws Throwable {
        super.invokeAdviceMethod(joinPointX, retVal, null);
    }
}
