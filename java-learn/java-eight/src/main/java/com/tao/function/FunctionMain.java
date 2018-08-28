package com.tao.function;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author DongTao
 * @since 2018-08-28
 */
public class FunctionMain {

  public static void main(String[] args) {

    Optional<User> userOptional = Optional.ofNullable(getUser());
    System.out.println(userOptional.orElse(new User()));
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    list.stream().map(x -> x + 2).forEach(System.out::println);

    System.out.println(userOptional
        .filter(x -> x.getPassword() == null)
        .orElse(new User("", "", 1)));

    // Predicate Predicate是一个布尔类型的函数，该函数只有一个输入参数。
    Predicate<String> predicate = String::isEmpty;
    Predicate<String> predicate2 = i -> Integer.valueOf(i) > 1;
    Predicate<Integer> predicate3 = integer -> integer > 100;
    System.out.println(predicate.test("12"));

    System.out.println(predicate2.test("123"));
    System.out.println(predicate.and(predicate2).test("123"));
    System.out.println(predicate3.test(123));

    // Supplier 接口接受一个泛型<T>, 接口方法是一个无参数的方法, 有一个类型为T的返回值.
    // Supplier接口产生一个给定类型的结果。与Function不同的是，Supplier没有输入参数。
    Supplier<String> supplier = () -> "Hello World !!! ";
    Supplier<User> userSupplier = () -> {
      User user = new User();
      user.setName("Dong");
//    user.setPassword("Tao");
      user.setAge(18);
      return user;
    };
    System.out.println(supplier.get());
    System.out.println(userSupplier.get());

    // Function Function接口接收一个参数，并返回单一的结果。默认方法可以将多个函数串在一起（compose, andThen）
    Function<String, Integer> toInteger = Integer::valueOf;
    Function<Integer, String> toString = String::valueOf;
    Function<String, String> toUp = s -> {
      System.out.println("to UP");
      return s.toUpperCase();
    };
    Function<String, String> toLow = s -> {
      System.out.println("to Low");
      return s.toLowerCase();
    };
    Function<String, String> backToString = toInteger.andThen(String::valueOf);
    System.out.println(toInteger.apply("123"));
    System.out.println(toInteger.apply("123").getClass());
    System.out.println(backToString.apply("123"));
    System.out.println(backToString.apply("123").getClass());
    System.out.println(toInteger.andThen(toString).apply("123"));
    System.out.println(toInteger.andThen(toString).apply("123").getClass());

    System.out.println(toUp.compose(toLow).apply("a"));
    System.out.println(toUp.compose(toLow).apply("a"));

    // Consumer Consumer代表了在一个输入参数上需要进行的操作
    Consumer<User> consumer = System.out::println;
    consumer.accept(new User("Dong", "12345", 18));

    // Comparators
    Comparator<User> comparator = Comparator.comparing(User::getName);
    User user = new User("A", "1", 1);
    User user2 = new User("B", "2", 2);

    System.out.println(comparator.compare(user, user2));
    System.out.println(comparator.reversed().compare(user, user2));

  }

  private static User getUser() {
    Random random = new Random();
    int i = random.nextInt(10);
    System.out.println("i = " + i);
    if (i > 5) {
      User user = new User();
      user.setName("Dong");
//    user.setPassword("Tao");
      user.setAge(18);
      return user;
    } else {
      return null;
    }
  }

}
