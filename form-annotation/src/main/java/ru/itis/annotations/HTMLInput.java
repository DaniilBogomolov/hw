package ru.itis.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface HTMLInput {
    String type() default "text";
    String name() default "";
    String placeholder() default "";
}
