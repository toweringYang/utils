package cn.bbahh.util;

import java.util.Random;

public class RandomUtils {
    public static  int randomInt(int min, int max){
        return (int) (Math.random()*(max-min)+min);
    }
    public static String getCharAndNumr(int length) {
        Random random = new Random();
        StringBuffer valSb = new StringBuffer();
        String charStr = "0123456789abcdefghijklmnopqrstuvwxyz";
        int charLength = charStr.length();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charLength);
            valSb.append(charStr.charAt(index));
        }
        return valSb.toString();
    }
}
