package springx.aop.aspect;

import java.lang.reflect.Method;

public class AbstractAspectAdviceX implements AdviceX {

    private Method aspectMethod;
    private Object aspectTarget;

    public AbstractAspectAdviceX(){}

    public AbstractAspectAdviceX(Method aspectMethod, Object aspectTartget) {
        this.aspectMethod = aspectMethod;
        this.aspectTarget = aspectTartget;
    }

    protected Object invokeAdviceMethod(JoinPointX joinPointX, Object returnVal, Throwable exception) throws Throwable {
        Class<?>[] parameterTypes = this.aspectMethod.getParameterTypes();
        if (null == parameterTypes || parameterTypes.length == 0) {
            return this.aspectMethod.invoke(aspectTarget);
        } else {
            Object[] args = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i] == JoinPointX.class) {
                    args[i] = joinPointX;
                }
                if (parameterTypes[i] == Throwable.class) {
                    args[i] = exception;
                }
                if (parameterTypes[i] == Object.class) {
                    args[i] = returnVal;
                }
            }
            return this.aspectMethod.invoke(aspectTarget, args);
        }


    }
}
