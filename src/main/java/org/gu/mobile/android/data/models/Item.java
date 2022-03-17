package org.gu.mobile.android.data.models;

import org.gu.mobile.android.Utils.DataHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.qala.datagen.RandomShortApi.*;

public class Item {
    private String name;
    private int price;
    private String currency;
    private int amount;
    private String unit;
    private String comment;
    private String category;

    private static final List<String> categories = Arrays.asList("No category", "Dairy produce", "Pet products",
            "Accessories Grocery", "Child products", "Medication", "Stationery", "Cosmetics, hygiene",
            "Sporting goods, games", "Ornamentation", "Accessories", "Clothing, footwear",
            "Fruits, vegetables", "pickles", "Auto products", "Household appliances", "Plants", "Meat, fish, eggs",
            "Frozen food", "Drinks, juices", "Bread, cakes and pastries", "Confection", "Grocery", "Alcohol, tobacco");
    private static final List<String> units = Arrays.asList("pcs.", "kg", "pack", "l", "g", "bottles", "box", "m", "sm",
            "unit", "package", "pack", "couple", "ten");

    private static final int MIN_FIELD_LENGTH = 3;
    private static final String DEFAULT_CURRENCY = "£";

    public static Item getRandomItem() {
        return new Item().setName(alphanumeric(MIN_FIELD_LENGTH, 10))
                .setPrice(integer(1, 50))
                .setCurrency(DEFAULT_CURRENCY)
                .setAmount(integer(1, 10))
                .setUnit(DataHelper.getRandomListItem(units))
                .setComment(english(0, 25))
                .setCategory(DataHelper.getRandomListItem(categories));
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Item setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public Item setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public Item setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public Item setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Item setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Item setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSum() {
        return String.valueOf(price * amount);
    }
}
