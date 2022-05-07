package org.gu.mobile.android.data.models;

import org.gu.mobile.android.Utils.DataHelper;
import org.gu.mobile.android.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import static io.qala.datagen.RandomShortApi.*;

public class Item {
    private String name;
    private int price;
    private String currency;
    private int amount;
    private String unit;
    private String comment;
    private String category;

    private static final int MIN_FIELD_LENGTH = 3;
    private static final String DEFAULT_CURRENCY = "£";

    static Logger logger = LoggerFactory.getLogger("Item");

    public static Item getRandomItem() {
        Item item = new Item().setName(alphanumeric(MIN_FIELD_LENGTH, 10))
                .setPrice(integer(1, 50))
                .setCurrency(DEFAULT_CURRENCY)
                .setAmount(integer(1, 10))
                .setUnit(DataHelper.getRandomListItem(Constants.UNITS))
                .setComment(english(0, 25))
                .setCategory(DataHelper.getRandomListItem(Constants.CATEGORIES));
        logger.info("Created item: " + item.toString());
        return item;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(Item.class.getDeclaredFields()).sorted(Comparator.comparing(Field::getName))
                .forEach(i -> {
                    try {
                        sb.append(String.format("[%s]: %s; ", i.getName(), i.get(this)));
                    } catch (IllegalAccessException e) {
                        logger.error("Can't parse item field: ", e);
                    }
                });
        return sb.toString();
    }
}
