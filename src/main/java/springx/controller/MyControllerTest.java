package springx.controller;

import springx.annotation.AutowiredX;
import springx.annotation.ControllerX;
import springx.annotation.RequestMappingX;
import springx.annotation.RequestParamX;
import springx.service.AopService;
import springx.webmvc.servlet.ModelAndViewX;

import java.util.HashMap;

@ControllerX
public  class MyControllerTest {

    @AutowiredX
    private AopService aopService;


    @RequestMappingX(value = "xxx")
    public String xxx() {
        return "404";
    }

    @RequestMappingX(value = "havaParam")
    public ModelAndViewX haveParam(@RequestParamX("v1") String v1, @RequestParamX("v2") String v2) {

        ModelAndViewX modelAndViewX = new ModelAndViewX();

        HashMap<String, Object> map = new HashMap<>();
        map.put("v1", v1);
        map.put("v2", v2);
        modelAndViewX.setModel(map);
        modelAndViewX.setViewName("testPage");

        return modelAndViewX;
    }


    @RequestMappingX(value = "aop")
    public ModelAndViewX aopTest() throws Exception {
        ModelAndViewX modelAndViewX = new ModelAndViewX();
        modelAndViewX.setViewName("testPage");
        aopService.test();
        aopService.exceptionTest();
        aopService.exceptionThrow();
        return modelAndViewX;
    }


}
