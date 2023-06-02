package cn.bbahh.util.bean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangchongxiao
 */
public class BeanUtils {
    public static Map<String,Object> beanToMap(Object object) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>(1);
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }

    public static <T> T mapToBean(Map<String,Object> source,Class<T> target) throws Exception {
        Field[] fields = target.getDeclaredFields();
        T o = target.newInstance();
        for(Field field:fields){
            Object val;
            if((val=source.get(field.getName()))!=null){
                field.setAccessible(true);
                field.set(o,val);
            }
        }
        return o;
    }

}
