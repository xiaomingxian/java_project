package spring_source_code.pojo;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class Advice {


    @Pointcut("execution(public * spring_source_code.pojo.Math.*(..))")//两个点号表示匹配任意个参数，包括0个
    public void pointCut() {
    }

    @Before(value = "pointCut()", argNames = "joinPoint")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("--------->前置通知：" + joinPoint.getSignature().getName() + ",参数：" + Arrays.asList(args));
    }

    @Around(value = "pointCut()", argNames = "joinPoint")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        System.out.println("--------->环绕通知");
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }

    @After("pointCut()")
    public void after() {
        System.out.println("------------->方法结束");
    }

    @AfterReturning(value = "pointCut()", returning = "object")
    public void afterRet(Object object) {
        System.out.println("---------->正常返回:" + object);
    }

    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThr(Exception ex) {
        System.out.println("---------->捕获到异常:" + ex);
    }


}
