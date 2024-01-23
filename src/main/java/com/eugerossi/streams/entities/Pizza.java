package com.eugerossi.streams.entities;

import java.util.EnumSet;
import java.util.Objects;

public class Pizza {

    public enum Ingredient {
        TOMATO_SAUCE("Tomato sauce"),
        MOZZARELLA("Mozzarella"),
        BASIL("Basil"),
        MUSHROOMS("Mushrooms"),
        CHEDDAR("Cheddar"),
        BACON("Bacon"),
        PARMESAN("Parmesan"),
        SHRIMPS("Shrimps"),
        PEPPERONI("Pepperoni"),
        SLICED_TOMATOES("Sliced tomatoes");

        private String name;

        Ingredient(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public enum Category {
        CLASSIC("Classic"),
        GOURMET("Gourmet");

        private String name;

        Category(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    private final String name;

    private final Category category;

    private final EnumSet<Ingredient> ingredients;

    public Pizza(String name, Category type, EnumSet<Ingredient> ingredients) {
        this.name = name;
        this.category = type;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public EnumSet<Ingredient> getIngredients() {
        return ingredients;
    }

    // Implemented equals & hashCode to use with streams().distinct()
    // https://www.baeldung.com/java-override-hashcode-equals-records
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pizza)) {
            return false;
        }

        Pizza pizza = (Pizza) o;
        return name.equals(pizza.name) && category.equals(pizza.category) && ingredients.equals(pizza.ingredients);
    }

//    public static final int PRIME = 31;
    @Override
    public int hashCode() {
        return Objects.hash(name, category, ingredients);

//        int hash = 0;
//        if (getName() != null) {
//            hash += getName().hashCode();
//        }
//        if (getType() != null) {
//            hash += getType().hashCode();
//        }
//        if (getIngredients() != null) {
//            hash += getIngredients().hashCode();
//        }
//
//        return hash == 0 ? System.identityHashCode(this) : PRIME * hash;
    }
}
