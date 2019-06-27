package com.Sip.annotations;

import com.Sip.fields.FieldPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PositionedField {
    FieldPolicy policy() default FieldPolicy.DEFAULT;
    int start();
    int end();
}
