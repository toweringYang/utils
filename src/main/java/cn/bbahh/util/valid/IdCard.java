package cn.bbahh.util.valid;

import cn.bbahh.util.valid.validation.IdCardValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 身份证识别验证
 * @author yangchongxiao
 * @date 2021/5/19 09:42
 */

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IdCardValidation.class)
public @interface IdCard {

    String message() default "IdCard error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
