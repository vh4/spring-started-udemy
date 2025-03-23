package com.mvc.crud.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueCustomValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCustom {
    String message() default "User id must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
