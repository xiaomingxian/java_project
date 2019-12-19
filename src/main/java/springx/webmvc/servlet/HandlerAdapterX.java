package springx.webmvc.servlet;

import org.springframework.cglib.core.Local;
import springx.annotation.RequestParamX;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * HandlerAdapter
 */
public class HandlerAdapterX {
    public final boolean support() {
        return true;
    }

    /**
     * @param request
     * @param response
     * @param hadler   其实就是方法(被HandlerMapping包装的方法)
     * @return
     * @throws Exception
     */

    ModelAndViewX handle(HttpServletRequest request, HttpServletResponse response, Object hadler)  {//throws Exception


        HandlerMappingX handlerMappingX = (HandlerMappingX) hadler;
        //把方法的形参列表和request的参数列表所在顺序进行一一对应
        Map<String, Integer> paramIndexMapping = new HashMap<>();

        //1 提取方法中加了注解的参数[二维数组：一个方法有多个参数(一维数组), 注解数组(二维：一个参数可以有多个注解)]
        Annotation[][] parameterAnnotations = handlerMappingX.getMethod().getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotation = parameterAnnotations[i];
            for (Annotation annotation1 : annotation) {

                if (annotation1 instanceof RequestParamX) {
                    String paramName = ((RequestParamX) annotation1).value();
                    if (!"".equals(paramName.trim())) {
                        paramIndexMapping.put(paramName, i);
                    }
                }
            }
        }


        //2 提取方法中的request和response参数
        Class<?>[] parameterTypes = handlerMappingX.getMethod().getParameterTypes();//拿到类型用于值解析转换
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> type = parameterTypes[i];

            if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                paramIndexMapping.put(type.getName(), i);
            }
        }

        //找到参数值
        Map<String, String[]> params= request.getParameterMap();

        //实参列表
        Object[] paramVals = new Object[parameterTypes.length];

        for (Map.Entry<String, String[]> param : params.entrySet()) {

            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "")
                    .replaceAll("\\s", ",");

            //提取出的参数中不包含
            if (!paramIndexMapping.containsKey(param.getKey())) continue;

            Integer index = paramIndexMapping.get(param.getKey());
            //参数值列表
            paramVals[index] = caseStringValue(value,parameterTypes[index]);


        }


        return null;

    }

    private Object caseStringValue(String value, Class<?> clazz) {


        if (null==value)return null;
        if (clazz==Integer.class)return Integer.valueOf(value);
        if (clazz==Double.class)return Double.valueOf(value);
        if (clazz== Long.class)return Long.valueOf(value);



        return  value;
    }



}
