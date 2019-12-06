package springx.annotation;


import java.lang.annotation.*;

@Target({ElementType.FIELD})//作用于字段上
@Retention(RetentionPolicy.RUNTIME)//在运行时生效
@Documented
public @interface AutowiredX {
    String value() default "";
}
