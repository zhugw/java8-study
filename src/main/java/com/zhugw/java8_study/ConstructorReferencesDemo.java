package com.zhugw.java8_study;

import java.util.function.Supplier;

public class ConstructorReferencesDemo {
  static class Person {
    String firstName;
    String lastName;

    public Person() {}

    public Person(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    @Override
    public String toString() {
      return firstName + " " + lastName;
    }
  }
  interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
  }

  public static void main(String[] args) {
    PersonFactory<Person> factory = Person::new;
    Person p = factory.create("hello", "world");
    System.out.println(p);

    Supplier<Person> personSupplier = Person::new;
    personSupplier.get(); // new Person
  }
}
