package com.tao.stream;

/**
 * @author DongTao
 * @since 2018-08-27
 */
@FunctionalInterface
interface Converter<F, T> {

  T convert(F from);
}
