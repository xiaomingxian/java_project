package springx.aop.aspect;

import java.lang.reflect.Method;

public interface JoinPointX {

    Object getThis();

    Object[] getArguments();

    Method getMethod();

    /**
     * 干啥用的
     */
    Object getUserAttribute(String key);

}
