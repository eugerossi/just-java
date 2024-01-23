package com.eugerossi.streams;

import com.eugerossi.streams.entities.Pizza;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Manipulation {

    public static void main(String[] args) {
        List<Pizza> allPizzas = Initialization.initializePizzaList();
        List<Integer> numbers = Arrays.asList(1, 2, 4, 2, 6, 7, 2, 6);

        System.out.println("== Running filterElements (retrieve a list of the pizzas name that contain Parmesan cheese) ==");
        List<Pizza> pizzasWithParmesan = filterElements(allPizzas);
        System.out.printf("List of pizzas with Parmesan cheese: %s%n",
                String.join(", ", mapElementsInListToADifferentObject(pizzasWithParmesan)));

        System.out.println("\n== Running checkAllElementsMeetACriteria ==");
        System.out.printf("Do all pizzas in 'allPizzas' list has Parmesan cheese? %b%n", checkAllElementsMatchCriteria(allPizzas));
        System.out.printf("Do all pizzas in 'pizzasWithParmesan' list has Parmesan cheese? %b%n", checkAllElementsMatchCriteria(pizzasWithParmesan));

        System.out.println("\n== Running checkAnyElementMatchCriteria ==");
        System.out.printf("Do any pizza in 'allPizzas' list has Basil? %b%n", checkAnyElementMatchCriteria(allPizzas));
        System.out.printf("Do any pizza in 'pizzasWithParmesan' list has Basil? %b%n", checkAnyElementMatchCriteria(pizzasWithParmesan));

        System.out.println("\n== Running checkNoneElementMatchCriteria ==");
        System.out.printf("Do all pizza in 'allPizzas' list has no Basil? %b%n", checkNoneElementMatchCriteria(allPizzas));
        System.out.printf("Do all pizza in 'pizzasWithParmesan' list has no Basil? %b%n", checkNoneElementMatchCriteria(pizzasWithParmesan));

        System.out.println("\n== Running getLastElementUsingReduce ==");
        System.out.printf("Last element in 'allPizzas' list: %s%n", getLastElementUsingReduce(allPizzas).getName());

        System.out.println("\n== Running getFirstElement ==");
        Optional<Pizza> firstElement = getFirstElement(allPizzas);
        firstElement.ifPresent(pizza -> System.out.printf("First element in 'allPizzas' list: %s%n", pizza.getName()));

        System.out.println("\n== Running getNestedListFromListElements (Gets the distinct ingredients for pizzas) ==");
        List<String> allIngredients = getNestedListFromListElements(allPizzas);
        System.out.printf("All ingredients for pizzas 'allPizzas' list: %s%n", String.join(", ", allIngredients));

        System.out.println("\n== Running countDistinctElements ==");
        System.out.printf("Number of different values in 'numbers' list: %d%n", countDistinctElements(numbers));
        pizzasWithParmesan.add(new Pizza("Cheese", Pizza.Category.CLASSIC, EnumSet.of(Pizza.Ingredient.MOZZARELLA, Pizza.Ingredient.TOMATO_SAUCE, Pizza.Ingredient.PARMESAN, Pizza.Ingredient.CHEDDAR)));
        System.out.printf("Number of different values in 'pizzasWithParmesan' list: %d%n", countDistinctElements(pizzasWithParmesan));

    }

    private static List<Pizza> filterElements(List<Pizza> list) {
        return list.stream().filter(p -> p.getIngredients().contains(Pizza.Ingredient.PARMESAN)).collect(Collectors.toList());
    }

    private static List<String> mapElementsInListToADifferentObject(List<Pizza> list) {
        return list.stream()
                .map(Pizza::getName).collect(Collectors.toList());
    }

    private static boolean checkAllElementsMatchCriteria(List<Pizza> list) {
        return list.stream().allMatch(p -> p.getIngredients().contains(Pizza.Ingredient.PARMESAN));
    }

    private static boolean checkAnyElementMatchCriteria(List<Pizza> list) {
        return list.stream().anyMatch(p -> p.getIngredients().contains(Pizza.Ingredient.BASIL));
    }

    private static boolean checkAnyElementsMatchCriteria2(List<Pizza> list) {
        return list.stream().anyMatch(p -> p.getName().equals("American"));
    }

    private static boolean checkNoneElementMatchCriteria(List<Pizza> list) {
        return list.stream().noneMatch(p -> p.getIngredients().contains(Pizza.Ingredient.BASIL));
    }

    public static Pizza getLastElementUsingReduce(List<Pizza> list) {
        Stream<Pizza> stream = list.stream();
        return stream.reduce((first, second) -> second).orElse(null);
    }

    private static Optional<Pizza> getFirstElement(List<Pizza> list) {
        return list.stream().findFirst();
    }

    private static List<String> getNestedListFromListElements(List<Pizza> list) {
        return list.stream().flatMap(p -> p.getIngredients().stream())
                .map(Pizza.Ingredient::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    private static <T> long countDistinctElements(List<T> list) {
        return list.stream().distinct().count();
    }
}
