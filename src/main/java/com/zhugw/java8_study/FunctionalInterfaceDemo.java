package com.zhugw.java8_study;

public class FunctionalInterfaceDemo {
  // @FunctionalInterface
  interface Converter<F, T> {
    T convert(F from);
  }

  static class Something {
    String startsWith(String s) {
      return "" + s.charAt(0);
    }
  }

  // static Converter<String, Integer> integerConverter = (from) -> Integer.valueOf(from);
  static Converter<String, Integer> integerConverter = Integer::valueOf;


  // static Converter<String, Double> doubleConverter = (from) -> Double.valueOf(from);
  static Converter<String, Double> doubleConverter = Double::valueOf;

  static Converter<String, String> firstCharConvert = new Something()::startsWith;

  public static void main(String[] args) {
    Integer converted = integerConverter.convert("123");
    System.out.println(converted); // 123

    System.out.println(doubleConverter.convert("1.23"));

    System.out.println(firstCharConvert.convert("Java"));

    int num = 1;
    Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);

    System.out.println(stringConverter.convert(2)); // 3


  }
}
