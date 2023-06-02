package cn.bbahh.util.valid.validation;

import cn.bbahh.util.valid.Hans;
import cn.bbahh.util.valid.validation.hans.HansEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 汉字校验
 * @author yangchongxiao
 * @date 2021/5/31 11:10
 */
public class HansValidation implements ConstraintValidator<Hans, String> {

    HansEnum type;

    @Override
    public void initialize(Hans constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        type=constraintAnnotation.type();
    }

    @Override
    public boolean isValid(String obj, ConstraintValidatorContext context) {

        switch (type){
            case ALL:
                return checkname(obj);
            case HAS:
                Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
                Matcher m = p.matcher(obj);
                return m.find();
            case NOT_HAS:
                return !checkname(obj);
            default:
        }
        return false;
    }
    public boolean checkname(String name)
    {
        int n;
        for(int i = 0; i < name.length(); i++) {
            n = name.charAt(i);
            if(!(19968 <= n && n <40869)) {
                return false;
            }
        }
        return true;
    }
}
