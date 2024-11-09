package com.navysu.java.basic.collections;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class SpliteratorExamples {

    public static void main(String[] args) {
        // Create a list of integers
        int[] numbers = { 1, 2, 3, 4, 5 };

        // Create a Spliterator for the list
        Spliterator<Integer> spliterator = Arrays.stream(numbers).spliterator();

        // Print the elements of the Spliterator using a while loop
        while (spliterator.tryAdvance(i -> System.out.println(i))) {
            // Do something with the element
        }
        sortedSetSpliteratorExample();
        sortedMapSpliteratorExample();
    }

    public static void sortedSetSpliteratorExample() {

        // Create a SortedSet of integers
        SortedSet<Integer> sortedSet = new TreeSet<>();
        sortedSet.add(1);
        sortedSet.add(2);
        sortedSet.add(3);
        sortedSet.add(4);
        sortedSet.add(5);

        // Create a Spliterator for the SortedSet
        Spliterator<Integer> sortedSetSpliterator = sortedSet.spliterator();

        System.out.println("Characterisitics: " + sortedSetSpliterator.characteristics());
        System.out.println("Estimate Size: " + sortedSetSpliterator.estimateSize());

        // Print the elements of the SortedSet in order using a while loop
        System.out.println("Printing elements of SortedSet in order:");
        while (sortedSetSpliterator.tryAdvance(i -> System.out.println(i))) {
            // Do something with the element
        }

    }

    public static void sortedMapSpliteratorExample() {
        SortedMap<String, Integer> sortedMap = new TreeMap<>();
        sortedMap.put("apple", 1);
        sortedMap.put("banana", 2);
        sortedMap.put("cherry", 3);
        sortedMap.put("date", 4);
        sortedMap.put("elderberry", 5);

        Spliterator<Entry<String, Integer>> spliterator = sortedMap.entrySet().spliterator();
        System.out.println("Characteristics: " + spliterator.characteristics());
        System.out.println("Estimate size: " + spliterator.estimateSize());

        spliterator.forEachRemaining(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

    }
}