package springx.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParamX {
    String value() default "";
}
