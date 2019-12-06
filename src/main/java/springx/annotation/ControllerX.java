package springx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Target({ElementType})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerX {

    String value() default "";

}
