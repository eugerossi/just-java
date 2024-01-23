package com.eugerossi.streams;

import com.eugerossi.streams.entities.Pizza;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Collect {
    // https://www.baeldung.com/java-8-collectors
    // Grouping examples: https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-streams-simple/src/test/java/com/baeldung/streams/groupingby/JavaGroupingByCollectorUnitTest.java#L11


    public static void main(String[] args) {
        List<Pizza> allPizzas = Initialization.initializePizzaList();
        List<Integer> numbers = Arrays.asList(1, 2, 4, 2, 6, 7, 2, 6);

        System.out.println("== Running filterElements (using Collectors to retrieve a list of Objects) ==");
        List<Pizza> pizzasWithParmesan = filterElements(allPizzas);
        System.out.printf("List of pizzas with Parmesan cheese: %s%n",
                String.join(", ", mapElementsInListToADifferentObject(pizzasWithParmesan)));

        System.out.println("\n== Running convertStreamToString (using Collectors to join Objects value) ==");
        System.out.printf("List of all pizzas with their ingredients: %n%s%n", convertStreamToString(allPizzas));

        System.out.println("\n== Running getUnmodifiableList (and trying to add a new element) ==");
        List<Pizza> unmodifialbeList = getUnmodifiableList(allPizzas);
        try {
            unmodifialbeList.add(new Pizza("Bacon", Pizza.Category.CLASSIC, EnumSet.of(Pizza.Ingredient.MOZZARELLA, Pizza.Ingredient.TOMATO_SAUCE, Pizza.Ingredient.BACON)));
        } catch (Exception e) {
            // java.lang.UnsupportedOperationException
            System.out.printf("Error trying to add a new element: %s%n", e);
        }

        System.out.println("\n== Running convertListToSet (removing duplicated values) ==");
        Set<Integer> noDuplicateNumbers = convertListToSetOfString(numbers);
        System.out.printf("Original list: %s -> resulting list: %s%n",
                numbers.stream().map(Object::toString).collect(Collectors.joining(", ")),
                noDuplicateNumbers.stream().map(Object::toString).collect(Collectors.joining(", ")));

        pizzasWithParmesan.add(new Pizza("Shrimp", Pizza.Category.GOURMET, EnumSet.of(Pizza.Ingredient.MOZZARELLA, Pizza.Ingredient.SHRIMPS, Pizza.Ingredient.PARMESAN)));
        Set<Pizza> noDuplicatePizzas = convertListToSetOfString(pizzasWithParmesan);
        System.out.printf("Original list: %s -> resulting list: %s%n",
                pizzasWithParmesan.stream().map(Pizza::getName).collect(Collectors.joining(", ")),
                noDuplicatePizzas.stream().map(Pizza::getName).collect(Collectors.joining(", ")));

        System.out.println("\n== Running groupElements ==");
        Map<Pizza.Category, Set<Pizza>> groupedPizzas = groupElements(allPizzas);
        for (Entry<Pizza.Category, Set<Pizza>> entry : groupedPizzas.entrySet()) {
            System.out.printf("%s pizzas: %s%n",
                    entry.getKey().getName(),
                    entry.getValue().stream().map(Pizza::getName).collect(Collectors.joining(", " , "[", "]")));
        }

        System.out.println("\n== Running groupElementsToEnumMap ==");
        EnumMap<Pizza.Category, Set<Pizza>> groupedPizzasToEnumMap = groupElementsToEnumMap(allPizzas);
        for (Entry<Pizza.Category, Set<Pizza>> entry : groupedPizzasToEnumMap.entrySet()) {
            System.out.printf("%s pizzas: %s%n",
                    entry.getKey().getName(),
                    entry.getValue().stream().map(Pizza::getName).collect(Collectors.joining(", " , "[", "]")));
        }

        System.out.println("\n== Running groupElementsAndConcatValues ==");
        Map<Pizza.Category, String> groupedPizzasAndConcatNames = groupElementsAndConcatValues(allPizzas);
        for (Entry<Pizza.Category, String> entry : groupedPizzasAndConcatNames.entrySet()) {
            System.out.printf("%s pizzas: %s%n", entry.getKey().getName(), entry.getValue());
        }

        System.out.println("\n== Running averageValues (get average age) ==");
        System.out.printf("Average: %f%n", averageValues(numbers));
    }

    private static List<Pizza> filterElements(List<Pizza> list) {
        return list.stream().filter(p -> p.getIngredients().contains(Pizza.Ingredient.PARMESAN)).collect(java.util.stream.Collectors.toList());
    }

    private static List<String> mapElementsInListToADifferentObject(List<Pizza> list) {
        return list.stream()
                .map(Pizza::getName).collect(Collectors.toList());
    }

    private static String convertStreamToString(List<Pizza> list) {
        return list.stream()
                .map(p -> p.getName() + ": " + p.getIngredients().toString())
                .collect(Collectors.joining(", ", "{", "}"));
    }

    private static List<Pizza> getUnmodifiableList(List<Pizza> list) {
        return list.stream().collect(Collectors.toUnmodifiableList());
    }

    private static <T> Set<T> convertListToSetOfString(List<T> list) {
        return list.stream().collect(Collectors.toSet()); // Simpler way: new HashSet<>(list)
    }

    private static Map<Pizza.Category, Set<Pizza>> groupElements(List<Pizza> list) {
        return list.stream().collect(Collectors.groupingBy(Pizza::getCategory, Collectors.toSet()));
    }

    private static EnumMap<Pizza.Category, Set<Pizza>> groupElementsToEnumMap(List<Pizza> list) {
        return list.stream().collect(Collectors.groupingBy(Pizza::getCategory, () -> new EnumMap<>(Pizza.Category.class),
                Collectors.toSet()));
    }

    private static Map<Pizza.Category, String> groupElementsAndConcatValues(List<Pizza> list) {
        return list.stream().collect(Collectors.groupingBy(Pizza::getCategory,
                Collectors.mapping(Pizza::getName, Collectors.joining(", ", "[", "]"))));
    }

    public static Double averageValues(List<Integer> integers) {
        return integers.stream().collect(Collectors.averagingInt(Integer::intValue));

    }
}
