package utils.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * author:
 */
@Aspect
@Component("LogAdvice")
public class LogAdvice {

    private static final Logger log = LoggerFactory.getLogger(LogAdvice.class);


    /**
     * 配置切点
     * value/pointcut都可以
     * 注解/切点表达式都可以
     */
    @Pointcut("@annotation(utils.aop.Log)")
    public void logPointCut() {
    }

    /**
     * @param joinPoint
     */
    @Before(argNames = "joinPoint", value = "logPointCut()")
    private void doBefore(JoinPoint joinPoint) {

        System.out.println("------------------->aop日志记录  doBefore");
    }

    /**
     * @param joinPoint
     * @return
     */
    @Around(argNames = "joinPoint", value = "logPointCut()")
    private Object doAround(ProceedingJoinPoint joinPoint) {
        System.out.println("------------------->aop日志记录  doAround");

        Object retVal = null;
        try {
            retVal = joinPoint.proceed();
            System.out.println("--------->doAround 返回对象：" + retVal);
        } catch (Exception e) {
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return retVal;

    }


    /**
     * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
     *
     * @param joinPoint
     */
    @AfterReturning(pointcut = "logPointCut()")
    private void doAfter(JoinPoint joinPoint) {
        System.out.println("-----------------> AfterReturning");
    }

    /**
     * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
     *
     * @param joinPoint
     */
    @AfterReturning(value = "logPointCut()")
    private void doReturn(JoinPoint joinPoint) {
    }

    /**
     * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "logPointCut()", throwing = "ex")
    private void doThrowing(JoinPoint joinPoint, Throwable ex) {
    }


}
