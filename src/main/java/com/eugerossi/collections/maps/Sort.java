package com.eugerossi.collections.maps;

import com.eugerossi.collections.maps.entities.Product;

import java.util.*;
import java.util.stream.Collectors;

public class Sort {

    public static void main(String[] args) {
        Map<String, Product> map = new HashMap<>();
        Product product1 = new Product(1, "Table");
        map.put(product1.getName(), product1);
        Product product2 = new Product(2, "Chair");
        map.put(product2.getName(), product2);
        Product product3 = new Product(7, "Desk");
        map.put(product3.getName(), product3);
        Product product4 = new Product(4, "Shelf");
        map.put(product4.getName(), product4);

        System.out.println("== Running sortMapByKeyUsingStream ==");
        sortMapByKeyUsingStream(map);

        System.out.println("\n== Running sortMapByKeyUsingStreamReversedOrder ==");
        sortMapByKeyUsingStreamReversedOrder(map);

        System.out.println("\n== Running sortMapByValuePropertyUsingStream ==");
        sortMapByValuePropertyUsingStream(map);

        System.out.println("\n== Running convertToTreeMapAndSortByKey ==");
        convertToTreeMapAndSortByKey(map);

        System.out.println("\n== Running convertTorArrayListAndSortByValue ==");
        convertTorArrayListAndSortByValue(map);

        System.out.println("\n== Running convertToArrayListAndSortByKey ==");
        convertToArrayListAndSortByKey(map);

        addDuplicates(map);

        System.out.println("\n== Running convertToTreeSetSortedByValue ==");
        convertToTreeSetSortedByValue(map);

        System.out.println("\n== Running convertToTreeSetSortedByKey ==");
        convertToTreeSetSortedByKey(map);
    }

    private static void sortMapByKeyUsingStream(Map<String, Product> map) {
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Product>comparingByKey())
                .forEach(System.out::println);
    }

    private static void sortMapByKeyUsingStreamReversedOrder(Map<String, Product> map) {
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Product>comparingByKey().reversed())
                .forEach(System.out::println);
    }

    private static void sortMapByValuePropertyUsingStream(Map<String, Product> map) {
        Map<String, Product> result = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

//        result.entrySet().forEach(System.out::println);
        result.forEach((key, value) -> System.out.printf("%d) %s=%s%n", value.getCode(), key, value));
    }

    private static void convertToTreeMapAndSortByKey(Map<String, Product> map) {
        TreeMap<String, Product> sorted = new TreeMap<>(map);
        sorted.putAll(map);

        sorted.entrySet().forEach(System.out::println);
    }

    private static void convertToTreeSetSortedByValue(Map<String, Product> map) {
        SortedSet<Product> values = new TreeSet<>(map.values());
        System.out.println(values);
    }

    private static void convertToTreeSetSortedByKey(Map<String, Product> map) {
        SortedSet<String> keysSet = new TreeSet<>(map.keySet());
        System.out.println(keysSet);
    }

    private static void convertTorArrayListAndSortByValue(Map<String, Product> map) {
        List<Product> products = new ArrayList<>(map.values());
        Collections.sort(products);

        System.out.println(products);
    }

    private static void convertToArrayListAndSortByKey(Map<String, Product> map) {
        List<String> employeeByKey = new ArrayList<>(map.keySet());
        Collections.sort(employeeByKey);
        System.out.println(employeeByKey);
    }

    private static void addDuplicates(Map<String, Product> map) {
        Product product5 = new Product(7, "Desk");
        map.put(product5.getName(), product5);
        Product product6 = new Product(4, "Shelf");
        map.put(product6.getName(), product6);
    }

}
