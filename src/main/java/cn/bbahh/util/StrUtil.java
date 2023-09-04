package cn.bbahh.util;

public class StrUtil {

    public static boolean isEmpty(String str){
        return str==null || str.length()==0;
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static boolean containsChineseCharacters(String str) {
        if (str == null) {
            return false;
        }
        return str.chars().anyMatch(c -> isChineseCharacter((char) c));
    }
    public static boolean isChineseCharacter(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

}
