package pl.krzysiek.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=UserExistsImpl.class)

public @interface UserExists {
 String message() default "User already exists";
 Class[] groups() default {};
 Class[] payload() default {};
}
