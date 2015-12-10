package com.zhugw.java8_study;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaDemo {

  public static void main(String[] args) {
    List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
    // Collections.sort(names, new Comparator<String>() {
    // @Override
    // public int compare(String o1, String o2) {
    // return o2.compareTo(o1);
    // }
    // });
    // System.out.println(names);

    Collections.sort(names, (a, b) -> b.compareTo(a));
    System.out.println(names);

  }

}
