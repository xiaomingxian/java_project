package springx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Target()
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParamX {
    String value() default "";
}
