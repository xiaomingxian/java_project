package springx.aop.aspect;

import java.lang.reflect.Method;

public class AbstractAspectAdviceX {

    private Method aspectMethod;
    private Object aspectTarget;

    public AbstractAspectAdviceX(Method aspectMethod, Object aspectTartget) {
        this.aspectMethod = aspectMethod;
        this.aspectTarget = aspectTartget;
    }
}
