package com.zhugw.java8_study;

import java.util.function.Consumer;

import com.zhugw.java8_study.ConstructorReferencesDemo.Person;

public class ConsumersDemo {

  public static void main(String[] args) {
    Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
    greeter.accept(new Person("Luke", "Skywalker"));
  }

}
