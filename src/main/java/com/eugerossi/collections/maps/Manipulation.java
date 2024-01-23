package com.eugerossi.collections.maps;

import com.eugerossi.collections.maps.entities.MutableKey;

import java.util.*;
import java.util.stream.Collectors;

public class Manipulation {
    /*
    A map is a key-value structure, every key is mapped to exactly one value, and that key can be used to retrieve the
    corresponding value from the map.
     */
    public static void main(String[] args) {
        Map<String, String> citiesAndFood = new HashMap<>();
        citiesAndFood.put("Rome", "Pizza");
        citiesAndFood.put("Madrid", "Bocata");
        citiesAndFood.put("London", "Fish and Chips");
        citiesAndFood.put("Bologna", "Pizza");

        System.out.println("== Running addElementToMap ==");
        addElementToMap(citiesAndFood, "Paris", "Escargots");

        System.out.println("\n== Running getElementFromMapByKey ==");
        System.out.printf("Got value '%s' passing key '%s'%n", getElementFromMapByKey(citiesAndFood, "Paris"), "Paris");
        System.out.printf("Got value '%s' passing key '%s'%n", getElementFromMapByKey(citiesAndFood, "OtherCity"), "OtherCity");

        System.out.println("\n== Running addElementToMap (override value for a existing key) ==");
        addElementToMap(citiesAndFood, "Paris", "Croque-monsieur");

        System.out.println("\n== Running removeElementFromMapByKey ==");
        removeElementFromMapByKey(citiesAndFood, "Paris");

        System.out.println("\n== Running checkElementInMapByKey ==");
        System.out.printf("Key '%s' exists in Map? %b %n", "Paris", checkElementInMapByKey(citiesAndFood, "Paris"));

        System.out.println("\n== Running getKeys ==");
        System.out.printf("Keys in Map: %s%n", String.join(", ", getKeys(citiesAndFood)));

        System.out.println("\n== Running getValues ==");
        System.out.printf("Values in Map: %s%n", String.join(", ", getValues(citiesAndFood)));

        System.out.println("\n== Running getFirstKeyForValueUsingForeach ==");
        System.out.printf("Key for value 'Bocata': %s%n", getFirstKeyForValueUsingForeach(citiesAndFood, "Bocata"));

        System.out.println("\n== Running getKeysForValueUsingStreams ==");
        System.out.printf("Key for value 'Pizza': %s%n", String.join(", ", getKeysForValueUsingStreams(citiesAndFood, "Pizza")));

        System.out.println("\n== Running copyMapUsingCopyConstructor ==");
        Map<String, String> newMap = copyMapUsingCopyConstructor(citiesAndFood);
        System.out.printf("Values in newMap: %s%n", convertMapToStringUsingStream(newMap));

        System.out.println("\n== Running copyMapUsingPutIfAbsent ==");
        Map<String, String> newMap2 = copyMapUsingPutIfAbsent(citiesAndFood);
        System.out.printf("Values in newMap: %s%n", convertMapToStringUsingStream(newMap2));

        System.out.println("\n== Running getElementFromMapByKeyOrDefault ==");
        System.out.printf("Default value when key is not found in newMap: %s%n", getElementFromMapByKeyOrDefault(newMap2, "Random Key", String.class));

        System.out.println("\n== Running addElementToMapUsingCompute ==");
        addElementToMapUsingCompute(citiesAndFood, "New York", "Cheesecake");
        System.out.printf("Got value '%s' passing key '%s'%n", getElementFromMapByKey(citiesAndFood, "New York"), "New York");

        System.out.println("\n== Running addElementToMapUsingCompute with an existing key ==");
        addElementToMapUsingCompute(citiesAndFood, "New York", "Cheesecake");
        System.out.printf("Got value '%s' passing key '%s'%n", getElementFromMapByKey(citiesAndFood, "London"), "London");

        System.out.println("\n== Running clearMap ==");
        clearMap(citiesAndFood);
        System.out.printf("Number of elements in citiesAndFood: %d%n", citiesAndFood.size());

        // Example with mutableKey
        Map<MutableKey, String> mutableKeyMap = new HashMap<>();
        MutableKey romeKey = new MutableKey("Rome");
        mutableKeyMap.put(romeKey, "Pizza");
        System.out.printf("Got value '%s' passing key romeKey with hash %s%n", mutableKeyMap.get(romeKey), romeKey.hashCode());
        romeKey.setName("Pisa");
        System.out.printf("Got value '%s' passing key romeKey with hash %s%n", mutableKeyMap.get(romeKey), romeKey.hashCode());

    }

    /*
    ============== WAYS TO MANIPULATE A MAP ==============
    */
    private static <K, V> void addElementToMap(Map<K, V> map, K key, V value) {
        map.put(key, value);
    }

    private static <K, V> V getElementFromMapByKey(Map<K, V> map, K key) {
        return map.get(key);
    }

    // Java 8
    // Convert to generic V https://stackoverflow.com/questions/14524751/cast-object-to-generic-type-for-returning
    private static <K, V> V getElementFromMapByKeyOrDefault(Map<K, V> map, K key, Class<V> clazz) {
        return map.getOrDefault(key, clazz.cast("Default value"));
    }

    // Java 8
    private static <K, V> void addElementToMapUsingCompute(Map<K, V> map, K key, V value) {
        map.compute(key, (k, v) -> {
            if (v == null) {
                return value;
            }
            return v;
        });
    }

    private static <K, V> void removeElementFromMapByKey(Map<K, V> map, K key) {
        map.remove(key);
    }

    private static <K, V> boolean checkElementInMapByKey(Map<K, V> map, K key) {
        return map.containsKey(key);
    }

    private static <K, V> Set<K> getKeys(Map<K, V> map) {
        return map.keySet();
    }

    private static <K, V> Collection<V> getValues(Map<K, V> map) {
        return map.values();
    }

    public static <K, V> K getFirstKeyForValueUsingForeach(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static <K, V> List<K> getKeysForValueUsingStreams(Map<K, V> map, V value) {
        return map.entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    private static <K, V> Map<K, V> copyMapIteratingOriginalMap(Map<K, V> sourceMap) {
        Map<K, V> newMap = new HashMap<>();
        for (Map.Entry<K, V> entry : sourceMap.entrySet()) {
            newMap.put(entry.getKey(), entry.getValue());
        }

        return newMap;
    }

    private static <K, V> HashMap<K, V> copyMapUsingClone(HashMap<K, V> sourceMap) {
        return (HashMap<K, V>) sourceMap.clone();
    }

    private static <K, V> Map<K, V> copyMapUsingPutAll(Map<K, V> sourceMap) {
        Map<K, V> newMap = new HashMap<>();
        newMap.putAll(sourceMap);
        return newMap;
    }

    private static <K, V> Map<K, V> copyMapUsingCopyConstructor(Map<K, V> sourceMap) {
        return new HashMap<>(sourceMap);
    }

    // Java 8
    private static <K, V> Map<K, V> copyMapUsingPutIfAbsent(Map<K, V> sourceMap) {
        Map<K, V> newMap = new HashMap<>();
        sourceMap.forEach(newMap::putIfAbsent);
        return newMap;
    }

    public Map<String, String> copyUsingPutIfAbsentWithForLoop(Map<String, String> sourceMap, Map<String, String> targetMap) {
        for (Map.Entry<String, String> entry : sourceMap.entrySet()) {
            targetMap.putIfAbsent(entry.getKey(), entry.getValue());
        }
        return targetMap;
    }

    private static <K, V> Map<K, V> copyMapUsingStream(Map<K, V> sourceMap) {
        return (HashMap<K, V>) sourceMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static <K, V> void clearMap(Map<K, V> map) {
        map.clear();
    }

    private static <K, V> String convertMapToStringUsingStream(Map<K, V> map) {
        return map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }

    public static Map<String, String> convertStringToMapWithStream(String mapAsString) {
        Map<String, String> map = Arrays.stream(mapAsString.split(","))
                .map(entry -> entry.split("="))
                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
        return map;
    }
}
