package com.tao.function;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author DongTao
 * @since 2018-08-28
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

  private String name;
  private String password;
  private int age;
}
