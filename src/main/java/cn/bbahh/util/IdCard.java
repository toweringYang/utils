package cn.bbahh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
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
class IdCardValidation implements ConstraintValidator<IdCard, String> {
    int[] weight = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 }; // 十七位数字本体码权重
    char[] validate = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' }; // mod11,对应校验码字符值

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.length()!=18) {
            return false;
        }
        String id17 = s.substring(0,17);
        char id1 = s.charAt(17);
        if(getCheckoutCode(id17) != id1) {
            return false;
        }
        String birthday = s.substring(6,14);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            sdf.parse(birthday);
        } catch (ParseException e) {
           return false;
        }
        return true;
    }
    public char getCheckoutCode(String Idnumber17) {
        int sum = 0; // wi*Ai和
        int mod = 0; // 进行模11运算
        try {
            for (int i = 0; i < 17; i++) {
                sum += Integer.parseInt(String.valueOf(Idnumber17.charAt(i)))
                        * weight[i];
            }
        } catch (Exception e) {
            return 'a';
        }
        mod = sum % 11;// 进行模11运算
        return validate[mod];// 返回校验码
    }
}