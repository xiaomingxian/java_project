package springx.aop.support;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import springx.aop.aspect.AfterReturningAdviceInterceptorX;
import springx.aop.aspect.AfterThrowingInterceptorX;
import springx.aop.aspect.MethodBeforeAdviceInterceptorX;
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


    private AopConfigX aopConfigX;
    private Class<?> targetClass;
    private Pattern pointCutClassPattern;

    private Object target;
    /**
     * key:方法 val:拦截器链
     * transient:不做序列化
     */
    private transient Map<Method, List<Object>> methodCache;


    public AdvisedSupportX() {
    }

    public AdvisedSupportX(AopConfigX aopConfigX) {
        this.aopConfigX = aopConfigX;
    }

    public void  setTargetClass(Class<?> targetClass){
        this.targetClass=targetClass;
        parse();

    }

    /**
     * 解析切面
     * 构造执行器链
     */
    private void parse() {
        String pointCut = aopConfigX.getPointCut();
        pointCut.replaceAll("\\.","\\\\.")
                .replaceAll("\\\\.\\*",".*")
                .replaceAll("\\(","\\\\(")
                .replaceAll("\\)","\\\\)");
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        pointCutClassPattern = Pattern.compile("class" + pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") + 1));

        try {
            methodCache = new HashMap<>();
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
                        String aspectBefore = this.aopConfigX.getAspectBefore();
                        //拦截器链加入 前置通知方法
                        advices.add(new MethodBeforeAdviceInterceptorX(methods.get(aspectBefore), aspectClazz));

                    }
                    //after
                    if (StringUtils.isNotBlank(aopConfigX.getAspectAfter())) {
                        String aspectAfter = this.aopConfigX.getAspectAfter();
                        advices.add(new AfterReturningAdviceInterceptorX(methods.get(aspectAfter), aspectClazz));

                    }
                    //afterThrowing
                    if (StringUtils.isNotBlank(aopConfigX.getAspectAfterThrowing())) {
                        String aspectAfterThrowing = this.aopConfigX.getAspectAfterThrowing();


                        AfterThrowingInterceptorX afterThrowingInterceptorX =
                                new AfterThrowingInterceptorX(methods.get(aspectAfterThrowing), aspectClazz);


                        advices.add(afterThrowingInterceptorX);

                    }
                    methodCache.put(method, advices);

                }


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public List<Object> getInterceptorsAndDynamicMethodMatchers(Method method, Class<?> targetClass) throws Exception {

        List<Object> cached = methodCache.get(method);
        if (cached == null) {//方法没有存储对应的拦截器链
            //Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
            ////TODO 有问题把
            //methodCache.put(m, cached);

            //1 获取所有的拦截器链
            //2 再存入

        }
        return cached;
    }


    public boolean pointCutMatch() {

        return pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }
}
