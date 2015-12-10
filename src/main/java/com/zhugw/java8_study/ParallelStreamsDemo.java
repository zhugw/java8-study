package com.zhugw.java8_study;

import java.util.Arrays;

public class ParallelStreamsDemo {

  public static void main(String[] args) {
    Arrays.asList("a1", "a2", "b1", "c2", "c1").parallelStream().filter(s -> {
      System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
      return true;
    }).map(s -> {
      System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
      return s.toUpperCase();
    }).forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));

    System.out.println();


    Arrays.asList("a1", "a2", "b1", "c2", "c1").parallelStream().filter(s -> {
      System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
      return true;
    }).map(s -> {
      System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
      return s.toUpperCase();
    }).sorted((s1, s2) -> {
      System.out.format("sort: %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
      return s1.compareTo(s2);
    }).forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
  }

}
