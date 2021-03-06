package cn.bbahh.util.valid;

import cn.bbahh.util.valid.validation.PhoneNumberValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 手机号码验证
 * @author yangchongxiao
 * @date 2021/5/28 13:05
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PhoneNumberValidation.class)
public @interface PhoneNumber {

    String message() default "IdCard error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
