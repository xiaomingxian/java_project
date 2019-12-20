package springx.controller;

import springx.annotation.ControllerX;
import springx.annotation.RequestMappingX;

@ControllerX
public class MyControllerTest {

    @RequestMappingX(value = "xxx")
    public String xxx() {
        return "400";
    }


}
