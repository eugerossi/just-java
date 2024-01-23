package com.eugerossi.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Loop {
    // Parallel examples: https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-streams-simple/src/test/java/com/baeldung/streams/parallel/ForkJoinUnitTest.java

    public static void main(String[] args) {
        List<String> places = Arrays.asList("Rome", "London", "Paris", "Belgrade", "Madrid", "Berlin");

        System.out.println("\n== Running iterateUsingStreamNoIndex ==");
        iterateUsingStreamNoIndex(places);

        System.out.println("\n== Running iterateUsingStreamWithIndex ==");
        iterateUsingStreamWithIndex(places);

        System.out.println("\n== Running iterateAndInterruptUsingTakeWhile (until the value 'Belgrade' is found) ==");
        iterateAndInterruptUsingTakeWhile(places);

        System.out.println("\n== Running iterateWithIfElse ==");
        iterateWithIfElse(places);

        System.out.println("\n== Running iterateWithStream ==");
        iterateWithStream(places);

        System.out.println("\n== Running iterateWithParallelStream ==");
        iterateWithParallelStream(places);
    }

    private static void iterateUsingStreamNoIndex(List<String> objectsToIterate) {
        objectsToIterate.forEach(value -> System.out.printf("<no-index>: Contains value '%s'%n", value));
    }

    private static void iterateUsingStreamWithIndex(List<String> objectsToIterate) {
        IntStream.range(0, objectsToIterate.size())
                .mapToObj(i -> String.format("%d: Contains value '%s'", i, objectsToIterate.get(i)))
                .forEach(System.out::println);
    }

    // Java 9
    private static void iterateAndInterruptUsingTakeWhile(List<String> objectsToIterate) {
        objectsToIterate.stream()
                .takeWhile(value -> !value.equals("Belgrade"))
                .forEach(System.out::println);
    }

    private static void iterateWithIfElse(List<String> objectsToIterate) {
        List<String> wordsWithI = new ArrayList<>();
        objectsToIterate.forEach(value -> {
            if (value.contains("i")) {
                wordsWithI.add(value);
                System.out.printf("Value '%s' contain character 'i'%n", value);
            } else
                System.out.printf("Value '%s' does not contain character 'i'%n", value);
        });
        System.out.printf("Values with 'i': %s%n", String.join(", ", wordsWithI));
    }

    private static void iterateWithStream(List<String> objectsToIterate) {
        objectsToIterate.forEach(Loop::printValue);
    }
    private static void iterateWithParallelStream(List<String> objectsToIterate) {
        objectsToIterate.parallelStream().forEach(Loop::printValue);
    }

    private static void printValue(String value) {
        System.out.printf("%s: value: %s%n", Thread.currentThread().getName(), value);
    }

    static AtomicInteger counter = new AtomicInteger();
    private static void concurrentPrintValue(String value) {
        int currentCounter = counter.incrementAndGet();
        System.out.printf("%d: value: %s%n", currentCounter, value);
    }
}
