package com.eugerossi.collections.maps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Loop {
    public static void main(String[] args) {
        Map<String, String> citiesAndFood = new HashMap<>();
        citiesAndFood.put("Rome", "Pizza");
        citiesAndFood.put("Madrid", "Bocata");
        citiesAndFood.put("London", "Fish and Chips");

        System.out.println("\n== Running iterateUsingForEach ==");
        iterateUsingForEach(citiesAndFood);

        System.out.println("\n== Running iterateUsingKeySetAndForeach ==");
        iterateUsingForeachWithKeySet(citiesAndFood);

        System.out.println("\n== Running iterateUsingForeachWithValues ==");
        iterateUsingForeachWithValues(citiesAndFood);

        System.out.println("\n== Running iterateUsingIteratorAndEntry ==");
        iterateUsingIteratorAndEntry(citiesAndFood);

        System.out.println("\n== Running iterateUsingIteratorAndKeySet ==");
        iterateUsingIteratorAndKeySet(citiesAndFood);

        System.out.println("\n== Running iterateUsingIteratorAndValues ==");
        iterateUsingIteratorAndValues(citiesAndFood);

        System.out.println("\n== Running iterateUsingLambda ==");
        iterateUsingLambda(citiesAndFood);

        System.out.println("\n== Running iterateUsingLambdaByKeys ==");
        iterateUsingLambdaByKeys(citiesAndFood);

        System.out.println("\n== Running iterateUsingLambdaWithValues ==");
        iterateUsingLambdaWithValues(citiesAndFood);

        System.out.println("\n== Running iterateUsingStreamAPI ==");
        iterateUsingStreamAPI(citiesAndFood);
    }

    /*
    ============== WAYS OF LOOPING A MAP ==============
    */
    private static void iterateUsingForEach(java.util.Map<String, String> objectsToIterate) {
        for (java.util.Map.Entry<String, String> entry : objectsToIterate.entrySet()) {
            System.out.printf("%s: Contains value '%s'%n", entry.getKey(), entry.getValue());
        }
    }

    private static void iterateUsingForeachWithKeySet(Map<String, String> map) {
        for (String key : map.keySet()) {
            System.out.printf("%s: Contains value '%s'%n", key, map.get(key));
        }
    }

    private static void iterateUsingForeachWithValues(Map<String, String> map) {
        for (String value : map.values()) {
            System.out.printf("no-index: Contains value '%s'%n", value);
        }
    }

    private static void iterateUsingIteratorAndEntry(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.printf("%s: Contains value '%s'%n", entry.getKey(), entry.getValue());
        }
    }

    private static void iterateUsingIteratorAndKeySet(Map<String, String> map) {
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.printf("%s: Contains value '%s'%n", key, map.get(key));
        }
    }

    private static void iterateUsingIteratorAndValues(Map<String, String> map) {
        Iterator<String> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            System.out.printf("no-index: Contains value '%s'%n", iterator.next());
        }
    }

    // Java 8
    private static void iterateUsingLambda(Map<String, String> objectsToIterate) {
        objectsToIterate.forEach((key, value) -> System.out.printf("%s: Contains value '%s'%n", key, value));
    }

    private static void iterateUsingLambdaByKeys(Map<String, String> objectsToIterate) {
        objectsToIterate.keySet().forEach(key -> System.out.printf("%s: Contains value '%s'%n", key, objectsToIterate.get(key)));
    }

    private static void iterateUsingLambdaWithValues(Map<String, String> objectsToIterate) {
        objectsToIterate.values().forEach(value -> System.out.printf("no-index: Contains value '%s'%n", value));
    }

    private static void iterateUsingStreamAPI(Map<String, String> objectsToIterate) {
        objectsToIterate.entrySet().stream().forEach(entry -> System.out.printf("%s: Contains value '%s'%n", entry.getKey(), entry.getValue()));
    }
}
