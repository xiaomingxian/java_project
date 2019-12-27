package springx.aop.support;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import springx.aop.config.AopConfigX;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
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

        //
        Pattern pattern = Pattern.compile(pointCut);
        for (Method method : this.targetClass.getMethods()) {
            String m = method.toString();
            if (m.contains("throws")){
                m=m.substring(0,m.lastIndexOf("throws"));
            }
            Matcher matcher = pattern.matcher(m);
            if (matcher.matches()){
                //把每一个方法包装成 methodInterceptor
                //1 执行器链
                List<Object> advices = new LinkedList<>();
                //before
                if (StringUtils.isNotBlank(aopConfigX.getAspectBefore())){

                }

            }


        }


    }


    public List<Object> getInterceptorsAndDynamicMethodMatchers(){
        return null;
    }


    public boolean pointCutMatch() {

        return pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }
}
