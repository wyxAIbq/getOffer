package com.Sip.annotations;

import com.Sip.fields.FieldPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Field {
    String tag() default "";
    int length() default 0;
    FieldPolicy policy() default FieldPolicy.DEFAULT;   
}
