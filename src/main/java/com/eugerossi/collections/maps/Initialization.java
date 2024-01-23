package com.eugerossi.collections.maps;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class Initialization {
    /*
    A map is a key-value structure, every key is mapped to exactly one value, and that key can be used to retrieve the
    corresponding value from the map.
     */

    // Empty maps
    static Map<String, Integer> emptyMapUsingCollections = Collections.emptyMap();
    static SortedMap<String, String> emptySortedMap = Collections.emptySortedMap();
    static NavigableMap<String, String> emptyNavigableMap = Collections.emptyNavigableMap();
    static Map<String, Integer> emptyHashMap = new HashMap<>();
    static Map<String, Integer> emptyLinkedMap = new LinkedHashMap<>();
    static Map<String, Integer> emptyTreeMap = new TreeMap<>();

    static Map<String, Integer> staticMap;

    static {
        staticMap = new HashMap<>();
        staticMap.put("New Zeeland", 64);
        staticMap.put("Morocco", 212);
    }

    static Map<String, Integer> singletonMap = Collections.singletonMap("Paraguay", 595);

    // Java 8
    static Map<String, Integer> mapWithInlinePut = new HashMap<>() {
        {
            put("India", 91);
            put("Estonia", 372);
            put("Syria", 963);
        }
    };

    // Java 9 and higher
    // this works for up to 10 elements:
    static Map<String, Integer> mapWithInlineValues = Map.of(
            "Sweden", 46,
            "Armenia", 374
    );

    // this works with any number of elements:
    static Map<String, Integer> mapWithInlineEntries = Map.ofEntries(
            entry("New Zeeland", 64),
            entry("Syria", 963),
            entry("India", 91)
    );

    // Initialization using Streams
    static Map<String, Integer> mapFromStreamObjectArray = Stream.of(new Object[][]{{"Estonia", 372}, {"Sweden", 46},})
            .collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

    static Map<String, String> mapFromStreamStringArray = Stream.of(new String[][]{{"Belgium", "32"}, {"Chile", "56"},})
            .collect(Collectors.toMap(data -> data[0], data -> data[1]));

    Map<String, Integer> mapFromStreamSimpleEntry = Stream.of(new AbstractMap.SimpleEntry<>("Italy", 39), new AbstractMap.SimpleEntry<>("Spain", 36))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    static Map<String, Integer> mapFromStreamSimpleImmutableEntry = Stream.of(new AbstractMap.SimpleImmutableEntry<>("Italy", 39), new AbstractMap.SimpleImmutableEntry<>("Japan", 81))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public Map<String, String> createImmutableMapWithStreams() {
        Map<String, String> map = Stream.of(new String[][]{{"Syria", "963"}, {"Morocco", "212"},})
                .collect(Collectors.collectingAndThen(Collectors.toMap(data -> data[0], data -> data[1]), Collections::<String, String>unmodifiableMap));
        return map;

    }
}
