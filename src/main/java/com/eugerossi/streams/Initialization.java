package com.eugerossi.streams;

import com.eugerossi.streams.entities.Customer;
import com.eugerossi.streams.entities.Pizza;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

public class Initialization {

    String[] arr = new String[]{"a", "b", "c"};
    Stream<String> stream1 = Arrays.stream(arr);

    Stream<String> stream2 = Stream.of("a", "b", "c");

    List<String> list = Arrays.asList("a", "b", "c");
    Stream<String> stream3 = list.stream();

    static List<Pizza> initializePizzaList() {
        return Arrays.asList(
                new Pizza("Margherita", Pizza.Category.CLASSIC, EnumSet.of(Pizza.Ingredient.MOZZARELLA, Pizza.Ingredient.TOMATO_SAUCE, Pizza.Ingredient.BASIL)),
                new Pizza("Forest", Pizza.Category.GOURMET, EnumSet.of(Pizza.Ingredient.MOZZARELLA, Pizza.Ingredient.TOMATO_SAUCE, Pizza.Ingredient.MUSHROOMS)),
                new Pizza("American", Pizza.Category.CLASSIC, EnumSet.of(Pizza.Ingredient.MOZZARELLA, Pizza.Ingredient.TOMATO_SAUCE, Pizza.Ingredient.BACON)),
                new Pizza("Shrimp", Pizza.Category.GOURMET, EnumSet.of(Pizza.Ingredient.MOZZARELLA, Pizza.Ingredient.SHRIMPS, Pizza.Ingredient.PARMESAN)),
                new Pizza("Cheese", Pizza.Category.CLASSIC, EnumSet.of(Pizza.Ingredient.MOZZARELLA, Pizza.Ingredient.TOMATO_SAUCE, Pizza.Ingredient.PARMESAN, Pizza.Ingredient.CHEDDAR))
        );
    }

    static List<Customer> initializeCustomerList() {
        return Arrays.asList(
                new Customer("John Doe", 45),
                new Customer("Kevin Smith", 20),
                new Customer("Dave Freeman", 36)
        );
    }
}
