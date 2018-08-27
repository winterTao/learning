package com.tao.stream;

/**
 * @author DongTao
 * @since 2018-08-27
 */
public class Main {

  public static void main(String[] args) {
    Formula formula = new Formula() {
      @Override
      public double calculate(int a) {
        return sqrt(a * 100);
      }
    };

    System.out.println(formula.calculate(100));
    System.out.println(formula.sqrt(16));

    System.out.println("**************************");

    Converter<String, Integer> converter = Integer::valueOf;
    Integer converted = converter.convert("123");
    System.out.println(converted);

    System.out.println("***************************");

    Something something = new Something();
    Converter<String, String> converter1 = something::startWith;
    String convert = converter1.convert("JAVA");
    System.out.println(convert);

    System.out.println("****************************");

    PersonFactory<Person> personFactory = Person::new;
    Person person = personFactory.create("Peter", "Parker");

    System.out.println(person);
  }
}
