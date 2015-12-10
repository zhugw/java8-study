package com.zhugw.java8_study;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import com.zhugw.java8_study.CollectDemo.Person;

public class ReduceDemo {

  public static void main(String[] args) {
    List<Person> persons = Arrays.asList(new Person(1, "Max", 18, 0), new Person(2, "Peter", 23, 1),
        new Person(3, "Pamela", 23, 1), new Person(4, "David", 12, 0));
    persons.stream().reduce((p1, p2) -> p1.age > p2.age ? p1 : p2).ifPresent(System.out::println); // Pamela

    Person result = persons.stream().reduce(new Person(0, "", 0, 0), (p1, p2) -> {
      p1.age += p2.age;
      p1.name += p2.name;
      return p1;
    });

    System.out.format("name=%s; age=%s%n", result.name, result.age);

    Integer ageSum =
        persons.stream().reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);

    System.out.println(ageSum); // 76

    ageSum = persons.parallelStream().reduce(0, (sum, p) -> {
      System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
      return sum += p.age;
    } , (sum1, sum2) -> {
      System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
      return sum1 + sum2;
    });

    ForkJoinPool commonPool = ForkJoinPool.commonPool();
    System.out.println(commonPool.getParallelism()); // 3

    System.out.println();
    persons.parallelStream().reduce(0, (sum, p) -> {
      System.out.format("accumulator: sum=%s; person=%s [%s]\n", sum, p,
          Thread.currentThread().getName());
      return sum += p.age;
    } , (sum1, sum2) -> {
      System.out.format("combiner: sum1=%s; sum2=%s [%s]\n", sum1, sum2,
          Thread.currentThread().getName());
      return sum1 + sum2;
    });

  }

}
