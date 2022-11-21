package org.gu.mobile.android.constants;

import org.gu.mobile.android.data.models.Config;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final List<String> CATEGORIES = Arrays.asList("No category", "Dairy produce", "Pet products",
            "Accessories Grocery", "Child products", "Medications", "Stationery", "Cosmetics, hygiene",
            "Sporting goods, games", "Ornamentation", "Accessories", "Clothing, footwear",
            "Fruits, vegetables, pickles", "Auto products", "Household appliances", "Plants", "Meat, fish, eggs",
            "Frozen food", "Drinks, juices", "Bread, cakes and pastries", "Confection", "Grocery", "Alcohol, tobacco");
    public static final List<String> UNITS = Arrays.asList("pcs.", "kg", "pack", "l", "g", "bottles", "box", "m", "sm",
                    "unit", "package", "pack", "couple", "ten");
    public static Config CONFIG;
    public static final String BROWSERSTACK_URL = "hub-cloud.browserstack.com/wd/hub";
    public static final String APP_ID = "com.slava.buylist";
    public static final String CONFIGS_PATH = "src/main/resources/";
}
