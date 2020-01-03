package springx.aspect;

import lombok.extern.slf4j.Slf4j;
import springx.aop.aspect.JoinPointX;

@Slf4j
public class LogAspect {

    public void before(JoinPointX joinPointX) {
        log.info("---->brfore:" + joinPointX.getMethod() + "," + joinPointX.getArguments());
    }

    public void after(JoinPointX joinPointX) {
        log.info("---->after:" + joinPointX.getMethod() + "," + joinPointX.getArguments());

    }

    public void afterThrowing(JoinPointX joinPointX, Throwable throwable) {
        log.info("---->afterThrowing:" + joinPointX.getMethod() + "," + joinPointX.getArguments() + ",ex:" + throwable.getMessage());
    }

}
