package com.eugerossi.collections.maps;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Concurrency {

    public static void main(String[] args) {

        HashMap<String, String> citiesAndFood = new HashMap<>();
        citiesAndFood.put("Rome", "Pizza");
        citiesAndFood.put("Madrid", "Bocata");
        citiesAndFood.put("London", "Fish and Chips");
        citiesAndFood.put("Budapest", "Goulash");
        citiesAndFood.put("Munich", "Pretzel");
        // Current Map Status: {Budapest=Goulash, Munich=Pretzel, Rome=Pizza, Madrid=Bocata, London=Fish and Chips}
        printCurrentMapStatus(citiesAndFood);

        citiesAndFood.remove("London");
        // Current Map Status: {Budapest=Goulash, Munich=Pretzel, Rome=Pizza, Madrid=Bocata}
        printCurrentMapStatus(citiesAndFood);


        citiesAndFood.remove("Budapest", "Escargots");
        // Current Map Status: {Budapest=Goulash, Munich=Pretzel, Rome=Pizza, Madrid=Bocata}
        printCurrentMapStatus(citiesAndFood);

        try {
            for (Entry<String, String> item : citiesAndFood.entrySet()) {
                if (item.getKey()
                        .equals("Madrid")) {
                    citiesAndFood.remove(item.getKey());
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Exception occurred while updating map: " + e.toString());
        }
        //Current Map Status: {Budapest=Goulash, Munich=Pretzel, Rome=Pizza}
        printCurrentMapStatus(citiesAndFood);

        citiesAndFood.entrySet()
                .removeIf(entry -> entry.getKey()
                        .equals("Budapest"));
        // Current Map Status: {Munich=Pretzel, Rome=Pizza}
        printCurrentMapStatus(citiesAndFood);

        Iterator<Entry<String, String>> iterator = citiesAndFood.entrySet()
                .iterator();
        while (iterator.hasNext()) {
            if (iterator.next()
                    .getKey()
                    .equals("Rome"))
                iterator.remove();
        }
        // Current Map Status: {Munich=Pretzel}
        printCurrentMapStatus(citiesAndFood);


        System.out.println("\n== Using Concurrent Map ==");
        // Use ConcurrentHashMap
        ConcurrentHashMap<String, String> citiesAndFoodConcurrentMap = new ConcurrentHashMap<>();
        citiesAndFoodConcurrentMap.put("Rome", "Pizza");
        citiesAndFoodConcurrentMap.put("Madrid", "Bocata");
        citiesAndFoodConcurrentMap.put("London", "Fish and Chips");
        citiesAndFoodConcurrentMap.put("Budapest", "Goulash");
        citiesAndFoodConcurrentMap.put("Munich", "Pretzel");

        for (Entry<String, String> item : citiesAndFoodConcurrentMap.entrySet()) {
            if (item.getKey() != null && item.getKey()
                    .equals("Budapest")) {
                citiesAndFoodConcurrentMap.remove(item.getKey());
            }
        }
        // Current Map Status: {Munich=Pretzel}
        printCurrentConcurrentMapStatus(citiesAndFoodConcurrentMap);

    }

    private static void printCurrentMapStatus(HashMap<String, String> map) {
        System.out.printf("Current Map Status: %s%n",
                map.keySet().stream()
                        .map(key -> key + "=" + map.get(key))
                        .collect(Collectors.joining(", ", "{", "}")));
    }

    private static void printCurrentConcurrentMapStatus(ConcurrentHashMap<String, String> map) {
        System.out.printf("Current Map Status: %s%n",
                map.keySet().stream()
                        .map(key -> key + "=" + map.get(key))
                        .collect(Collectors.joining(", ", "{", "}")));
    }

}
