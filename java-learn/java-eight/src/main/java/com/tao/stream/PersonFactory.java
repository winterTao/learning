package com.tao.stream;

/**
 * @author DongTao
 * @since 2018-08-27
 */
@FunctionalInterface
interface PersonFactory<P extends Person> {

  P create(String firstName, String lastName);
}