package com.zhugw.java8_study;

import java.math.BigDecimal;
import java.util.Optional;

public class OptionalDemo {

  public static void main(String[] args) {
    Optional<String> optional = Optional.of("foo");
    System.out.println(optional.isPresent());
    System.out.println(optional.get());
    System.out.println(optional.orElse("bar"));

    optional.ifPresent(s -> System.out.println(s.charAt(0)));

    System.out.println(Optional.ofNullable(null).orElse("This is Null"));

    BigDecimal amount = Optional.<BigDecimal>ofNullable(null).orElse(BigDecimal.ZERO);
    System.out.println(amount);

  }

}
