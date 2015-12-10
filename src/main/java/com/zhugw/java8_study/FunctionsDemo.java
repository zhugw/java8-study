package com.zhugw.java8_study;

import java.util.function.Function;

public class FunctionsDemo {
  public static void main(String[] args) {
    Function<String, Integer> toInteger = Integer::valueOf;
    Function<String, String> backToString = toInteger.andThen(a -> a * 100 + "");

    System.out.println(backToString.apply("123")); // "123"
  }
}
