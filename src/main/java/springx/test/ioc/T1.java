package springx.test.ioc;

import springx.annotation.AutowiredX;
import springx.annotation.ControllerX;


@ControllerX
public class T1 {
    /**
     * 循环依赖测试
     */
    @AutowiredX
    private T2 t2;

}
