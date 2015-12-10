package com.zhugw.java8_study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;


public class CollectDemo {
  static Random rand = new Random();

  static class Person {
    private int id;
    String name;
    int age;
    private int gender;
    List<Integer> friends =
        Stream.of(rand.nextInt(5), rand.nextInt(10), rand.nextInt(15)).collect(Collectors.toList());

    Person(int id, String name, int age, int gender) {
      this.id = id;
      this.name = name;
      this.age = age;
      this.gender = gender;
    }

    public List<Integer> getFriends() {
      return friends;
    }

    public void setFriends(List<Integer> friends) {
      this.friends = friends;
    }

    @Override
    public String toString() {
      return "Person [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender
          + ", friends=" + friends + "]";
    }

  }

  public static void main(String[] args) {
    List emptyList = Lists.newArrayList().stream().collect(Collectors.toList());
    System.out.println(emptyList);
    List<Person> persons = Arrays.asList(new Person(1, "Max", 18, 0), new Person(2, "Peter", 23, 1),
        new Person(3, "Pamela", 23, 1), new Person(4, "David", 12, 0));
    System.out.println(persons.stream().map(p -> p.name).collect(Collectors.joining(",")));

    persons.sort((p1, p2) -> {
      if (p1.id != p2.id)
        return p2.id - p1.id;
      else
        return p2.age - p1.age;
    });

    System.out.println(persons);

    // List<Integer> idAndAgeList =
    // persons.stream().map(p->p.id).map(p->p.age).collect(Collectors.toList());

    List<Person> filtered =
        persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList());

    System.out.println(filtered); // [Peter, Pamela]

    Map<Integer, List<Person>> personsByAge =
        persons.stream().collect(Collectors.groupingBy(p -> p.age));

    personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

    Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.age));

    System.out.println(averageAge); // 19.0

    IntSummaryStatistics ageSummary =
        persons.stream().collect(Collectors.summarizingInt(p -> p.age));

    System.out.println(ageSummary);
    // IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}

    String phrase = persons.stream().filter(p -> p.age >= 18).map(p -> p.name)
        .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

    System.out.println(phrase);

    Map<Integer, String> map = persons.stream()
        .collect(Collectors.toMap(p -> p.age, p -> p.name, (name1, name2) -> name1 + ";" + name2));

    System.out.println(map);

    Map<Integer, List<String>> ageNameListMap = persons.stream().collect(
        Collectors.groupingBy(p -> p.age, Collectors.mapping(p -> p.name, Collectors.toList())));


    // ageNameListMap: {18=[Max], 23=[Pamela, Peter], 12=[David]}
    System.out.println("ageNameListMap: " + ageNameListMap);

    Map<Integer, Person> map2 = persons.stream().collect(Collectors.toMap(p -> p.id, p -> p));
    System.out.println(map2);

    Collector<Person, StringJoiner, String> personNameCollector =
        Collector.of(() -> new StringJoiner(" | "), // supplier
            (j, p) -> j.add(p.name.toUpperCase()), // accumulator
            (j1, j2) -> j1.merge(j2), // combiner
            StringJoiner::toString); // finisher

    String names = persons.stream().collect(personNameCollector);

    System.out.println(names); // MAX | PETER | PAMELA | DAVID

    System.out.println(persons.stream().map(p -> p.name.toUpperCase())
        .collect(Collectors.joining(" | ")).toString());
    //
    Map<Integer, Map<Integer, List<Person>>> personsByGenderAndAge =
        persons.stream().collect(Collectors.groupingBy(p -> {
          System.out.println("gender: " + p.gender);
          return p.gender;
        } , Collectors.groupingBy(p -> {
          System.out.println("age: " + p.age);
          return p.age;
        })));
    System.out.println(personsByGenderAndAge);

    personsByGenderAndAge = persons.stream()
        .collect(Collectors.groupingBy(p -> p.gender, Collectors.groupingBy(p -> p.age)));
    System.out.println(personsByGenderAndAge);



    Map<Integer, Map<Integer, List<Integer>>> genderAgeFriendsListMap = persons.stream()
        .collect(Collectors.groupingBy(p -> p.gender, Collectors.groupingBy(p -> p.age, Collectors
            .mapping(p -> p.friends, Collector.of(ArrayList::new, (a, b) -> a.addAll(b), (a, b) -> {
              a.addAll(b);
              return a;
            })))));

    System.out.println(genderAgeFriendsListMap);
    Map<Integer, Map<Integer, List<Integer>>> genderAgeFriendsListMap2 = persons.stream()
        .collect(Collectors.groupingBy(p -> p.gender, Collectors.groupingBy(p -> p.age,
            Collectors.mapping(p -> p.friends, Collectors.reducing(new ArrayList<>(), (a, b) -> {
              a.addAll(b);
              return a;
            })))));

    System.out.println(genderAgeFriendsListMap2);

    List<List<String>> list = new ArrayList<>();
    list.add(Lists.newArrayList("a", "b", "c"));
    list.add(Lists.newArrayList("d", "e", "f"));

    System.out.println(list.stream().reduce(new ArrayList<>(), (a, b) -> {
      a.addAll(b);
      return a;
    }));;

    System.out.println(
        persons.stream().collect(Collectors.groupingBy(p -> p.age, Collectors.counting())));
    System.out.println(persons.stream()
        .collect(Collectors.groupingBy(p -> p.age, Collectors.summingInt(p -> p.age))));
  }
}
