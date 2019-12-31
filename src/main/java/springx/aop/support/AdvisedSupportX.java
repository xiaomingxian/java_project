package springx.aop.support;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import springx.aop.config.AopConfigX;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
public class AdvisedSupportX {


    AopConfigX aopConfigX;
    private Class<?> targetClass;
    private Pattern pointCutClassPattern;

    private Object target;


    public AdvisedSupportX() {
    }

    public AdvisedSupportX(AopConfigX aopConfigX) {
        this.aopConfigX = aopConfigX;
    }

    public void  setTargetClass(Class<?> targetClass){
        this.targetClass=targetClass;
        parse();

    }

    private void parse() {
        String pointCut = aopConfigX.getPointCut();
        pointCut.replaceAll("\\.","\\\\.")
                .replaceAll("\\\\.\\*",".*")
                .replaceAll("\\(","\\\\(")
                .replaceAll("\\)","\\\\)");
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        pointCutClassPattern = Pattern.compile("class" + pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") + 1));

        try {
            //
            Pattern pattern = Pattern.compile(pointCut);
            //得到切面所在的类，并记录其方法
            String aspectClass = this.aopConfigX.getAspectClass();
            Class<?> aspectClazz = Class.forName(aspectClass);
            Map<String, Method> methods = new HashMap<>();
            for (Method method : aspectClazz.getMethods()) {
                methods.put(method.getName(), method);
            }


            for (Method method : this.targetClass.getMethods()) {
                String m = method.toString();
                if (m.contains("throws")) {
                    m = m.substring(0, m.lastIndexOf("throws"));
                }
                Matcher matcher = pattern.matcher(m);
                if (matcher.matches()) {
                    //把每一个方法包装成 methodInterceptor
                    //1 执行器链(有序：Linked)
                    List<Object> advices = new LinkedList<>();
                    //before
                    if (StringUtils.isNotBlank(aopConfigX.getAspectBefore())) {
                        //MethodBeforeAdviceInterceptor
                        //AfterReturningAdviceInterceptor
                        //AfterThrowingInterceptor
                    }
                    //after
                    if (StringUtils.isNotBlank(aopConfigX.getAspectAfter())) {
                        //MethodBeforeAdviceInterceptor
                        //AfterReturningAdviceInterceptor
                        //AfterThrowingInterceptor
                    }
                    //afterThrowing
                    if (StringUtils.isNotBlank(aopConfigX.getAspectAfterThrowing())) {
                        //MethodBeforeAdviceInterceptor
                        //AfterReturningAdviceInterceptor
                        //AfterThrowingInterceptor
                    }

                }


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public List<Object> getInterceptorsAndDynamicMethodMatchers(){
        return null;
    }


    public boolean pointCutMatch() {

        return pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }
}
