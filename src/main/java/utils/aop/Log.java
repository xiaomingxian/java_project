package utils.aop;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Log {

    String function_id() default "";

    String operate_type() default "";

    String operate_content() default "";

}