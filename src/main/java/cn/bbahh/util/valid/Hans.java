package cn.bbahh.util.valid;

import cn.bbahh.util.valid.validation.hans.HansEnum;
import cn.bbahh.util.valid.validation.HansValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 全汉子汉字校验
 * @author yangchongxiao
 * @date 2021/5/31 11:08
 */

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = HansValidation.class)
public @interface Hans {

    HansEnum type() default HansEnum.ALL;

    String message() default "Hans error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
