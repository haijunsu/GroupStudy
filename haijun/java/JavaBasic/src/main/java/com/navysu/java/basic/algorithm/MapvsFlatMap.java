package com.navysu.java.basic.algorithm;

import java.util.*;
import java.util.stream.*;

/**
 * This class is used to help understand how difference between map() and flatMap() are in Java
 */
public class MapvsFlatMap {

    public static void main(String args[]) {
        MapvsFlatMap mfm = new MapvsFlatMap();
        mfm.testOptional();
        mfm.testStream();
    }

    /**
     * Compare in Optional
     */
    public void testOptional() {
        Person person = new Person("HappyMan", 26);
        Optional<Person> personOptional = Optional.of(person);
        Optional<Optional<String>> mapReturn = personOptional.map(Person::getName);
        Optional<String> flatMapReturn = personOptional.flatMap(Person::getName);

        System.out.println(mapReturn); // Optional[Optional[HappyMan]]
        System.out.println(flatMapReturn); // Optional[HappyMan]
        System.out.println(mapReturn.get().equals(flatMapReturn.get())); // false
    }

    /**
     * Compare in Stream
     */
    public void testStream() {
        List<String> myList = Stream.of("a", "b")
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println(myList); // ["A", "B"]
        List<List<String>> list = Arrays.asList(
        Arrays.asList("a"),
        Arrays.asList("b"));
        System.out.println(list); // [["a"], ["b"]]
        myList = list.stream()
                    .flatMap(Collection::stream)
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
        System.out.println(myList); // ["A", "B"]
    }
}

/**
 * Entity of Person. Help to understand for testOptional method.
 */
class Person {
    private String name;
    private int age;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Person() {

    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
