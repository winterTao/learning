package com.tao.directed;

/**
 * @author DongTao
 * @since 2019-10-08
 */
public class Utils {
    /**
     * 打印信息
     * @param t
     */
    public static void log(Object t) {
        System.out.println(t);
    }

    /**
     * 打印信息
     * @param t
     */
    public static void log(String format, Object... args) {
        String str = String.format(format, args);
        System.out.println(str);
    }
}
