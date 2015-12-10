package com.zhugw.java8_study;

import java.util.Comparator;

import com.zhugw.java8_study.ConstructorReferencesDemo.Person;

public class ComparatorDemo {

  public static void main(String[] args) {
    Comparator<Person> comp = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
    Person p1 = new Person("John", "Doe");
    Person p2 = new Person("Alice", "Wonderland");

    System.out.println(comp.compare(p1, p2));;
    System.out.println(comp.reversed().compare(p1, p2));;
  }

}
