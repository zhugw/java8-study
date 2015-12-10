package com.zhugw.java8_study;

public class Lambda4 {
  interface Converter<F, T> {
    T convert(F from);
  }

  static int outerStaticNum;
  int outerNum;

  void testScopes() {
    Converter<Integer, String> stringConverter1 = (from) -> {
      outerNum = 23;
      return String.valueOf(from);
    };

    Converter<Integer, String> stringConverter2 = (from) -> {
      outerStaticNum = 72;
      return String.valueOf(from);
    };
  }
}
