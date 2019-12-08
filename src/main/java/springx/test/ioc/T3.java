package springx.test.ioc;

import springx.annotation.AutowiredX;
import springx.annotation.ControllerX;

@ControllerX
public class T3 {

    @AutowiredX
    private T1 t1;


    @Override
    public String toString() {
        System.out.println("--->>>>"+t1);
        return t1.toString();
    }
}
