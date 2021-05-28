package cn.bbahh.util.valid;

import cn.bbahh.util.valid.validation.VINValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 *
 * @author yangchongxiao
 * @date 2021/5/27 15:11
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = VINValidation.class)
public @interface VIN {
    String message() default "VIN error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
