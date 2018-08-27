package com.tao.stream;

/**
 * @author DongTao
 * @since 2018-08-27
 */
public interface Formula {

  double calculate(int a);

  default double sqrt(int a) {
    return Math.sqrt(a);
  }
}
