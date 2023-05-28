package com.checkotovsky.SimpleUserCRUD.Validation;

import com.checkotovsky.SimpleUserCRUD.Validation.PasswordConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidPassword {
    String message() default "Invalid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
