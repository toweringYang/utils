package cn.bbahh.util.valid.validation;

import cn.bbahh.util.valid.IdCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author yangchongxiao
 * @date 2021/5/27 15:25
 */
public class IdCardValidation implements ConstraintValidator<IdCard, String> {
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