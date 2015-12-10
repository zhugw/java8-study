package com.zhugw.java8_study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class StreamsDemo {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("ddd2");
    list.add("aaa2");
    list.add("bbb1");
    list.add("aaa1");
    list.add("bbb3");
    list.add("ccc");
    list.add("bbb2");
    list.add("ddd1");



    list.stream().sorted().filter(s -> s.startsWith("a")).forEach(System.out::println);
    System.out.println(
        list.stream().sorted().filter(s -> s.startsWith("a")).collect(Collectors.toList()));
    System.out.println(list);

    list.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
        .forEach(System.out::println);

    System.out.println(list.stream().anyMatch(s -> s.startsWith("a")));
    System.out.println(list.stream().allMatch(s -> s.startsWith("a")));
    System.out.println(list.stream().noneMatch(s -> s.startsWith("z")));

    System.out.println(list.stream().filter(s -> s.startsWith("b")).count());
    System.out.println("dinstinct starts with a count: "
        + list.stream().filter(s -> s.startsWith("a")).distinct().count());

    Optional<String> reduced = list.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
    reduced.ifPresent(System.out::println);

    int max = 1000000;
    List<String> values = new ArrayList<>(max);
    for (int i = 0; i < max; i++) {
      UUID uuid = UUID.randomUUID();
      values.add(uuid.toString());
    }
    // Sequential sort
    long t0 = System.nanoTime();
    long count = values.parallelStream().sorted().count();
    System.out.println(count);
    long t1 = System.nanoTime();
    long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
    System.out.println(String.format("parallel sort took: %d ms", millis));
    // Parallel sort
    t0 = System.nanoTime();
    count = values.stream().sorted().count();
    System.out.println(count);
    t1 = System.nanoTime();
    millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
    System.out.println(String.format("sequential sort took: %d ms", millis));

    System.out.println();

    List<String> l = new ArrayList(Arrays.asList("one", "two"));
    Stream<String> sl = l.stream();
    l.add("three");
    String s = sl.collect(Collectors.joining(" "));
    System.out.println(s);

    IntStream.range(0, 50).forEach(i -> System.out.print("="));
    System.out.println();

    Map<Integer, Set<String>> testMap = Maps.newHashMap();
    testMap.put(1, Sets.newHashSet("aaa", "bbb"));
    testMap.put(2, Sets.newHashSet("ccc", "ddd"));
    testMap.put(3, Sets.newHashSet("eee"));

    System.out.println(testMap.values().stream().count());// 3
    System.out.println(testMap.values().stream().flatMap(e -> e.stream()).count());// 5


  }

}
