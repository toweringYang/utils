package cn.bbahh.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmptyUtils implements Serializable {

    /**
     * 字符串是否为空
     *
     * @param cs 字符串
     * @return 结果
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 判断对象是否为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        return object == null;
    }

    /**
     * 判断List是否为空
     *
     * @param list
     * @return
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() <= 0;
    }
    /**
     * 判断Set是否为空
     *
     * @param set
     * @return
     */
    public static <T> boolean isEmpty(Set<T> set) {
        return set == null || set.size() <= 0;
    }

    /**
     * 判断map是否为空
     *
     * @param map
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(Map map) {
        return map == null || map.size() < 0;
    }

    /**
     * 判断数据是否为空
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(T[] t) {
        return t == null || t.length <= 0;
    }

}
