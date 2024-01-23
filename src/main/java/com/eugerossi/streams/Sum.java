package com.eugerossi.streams;

import com.eugerossi.streams.entities.Customer;
import com.eugerossi.streams.entities.Pizza;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Sum {

    public static void main(String[] args) {
        List<Pizza> allPizzas = Initialization.initializePizzaList();
        List<Customer> allCustomers = Initialization.initializeCustomerList();
        List<Integer> numbers = Arrays.asList(1, 2, 4, 2, 6, 7, 2, 6);

        System.out.println("== Running concatenateStringsUsingReduce ==");
        Optional<String> reducedValue = concatenateStringsUsingReduce(allPizzas);
        reducedValue.ifPresent(val -> System.out.printf("Concat all values in 'allPizzas' list: %s%n", val));

        System.out.println("\n== Running concatenateStringsUsingReduceAndStringConcat ==");
        System.out.printf("Concat all values in 'allPizzas' list: %s%n", concatenateStringsUsingReduceAndStringConcat(allPizzas));

        System.out.println("\n== Running sumValuesUsingReduce ==");
        System.out.printf("Sum of all values in 'numbers' list: %d%n", sumValuesUsingReduce(numbers));

        System.out.println("\n== Running sumValuesUsingReduceAndIntegerSum ==");
        System.out.printf("Sum of all values in 'numbers' list: %d%n", sumValuesUsingReduceAndIntegerSum(numbers));

        System.out.println("\n== Running sumValuesUsingCollect ==");
        System.out.printf("Sum of all values in 'numbers' list: %d%n", sumValuesUsingCollect(numbers));

        System.out.println("\n== Running sumValuesUsingMapToInt ==");
        System.out.printf("Sum of all values in 'numbers' list: %d%n", sumValuesUsingMapToInt(numbers));

        System.out.println("\n== Running sumFieldValuesUsingReduce ==");
        System.out.printf("Sum of customers' age in 'allCustomers' list: %d%n", sumFieldValuesUsingReduce(allCustomers));
    }

    private static Optional<String> concatenateStringsUsingReduce(List<Pizza> list) {
        return list.stream().map(Pizza::getName).reduce((partialString, element) -> partialString + ", " + element);
    }

    private static String concatenateStringsUsingReduceAndStringConcat(List<Pizza> list) {
        return list.stream().map(Pizza::getName).reduce("", String::concat);
    }

    public static Integer sumValuesUsingReduce(List<Integer> integers) {
        return integers.stream().reduce(0, (subtotal, element) -> subtotal + element);

    }
    public static Integer sumValuesUsingReduceAndIntegerSum(List<Integer> integers) {
        return integers.stream().reduce(0, Integer::sum);

    }

    public static Integer sumValuesUsingCollect(List<Integer> integers) {
        return integers.stream().collect(Collectors.summingInt(Integer::intValue));
    }

    public static Integer sumValuesUsingMapToInt(List<Integer> integers) {
        return integers.stream().mapToInt(Integer::intValue).sum();
    }

    public static Integer sumFieldValuesUsingReduce(List<Customer> integers) {
        return integers.stream().reduce(0, (subtotal, customer) -> subtotal + customer.getAge(), Integer::sum);
    }

}
