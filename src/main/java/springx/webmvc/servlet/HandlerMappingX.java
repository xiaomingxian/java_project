package springx.webmvc.servlet;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * spring的标准是接口
 * 此处简化为直接实现类
 */

@Data
public class HandlerMappingX {
    private Object controller;
    private Method method;
    private Pattern pattern;//url的正则匹配

    public HandlerMappingX(){}

    public HandlerMappingX(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }
}
