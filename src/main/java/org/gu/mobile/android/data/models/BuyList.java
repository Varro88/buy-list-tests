package org.gu.mobile.android.data.models;

import java.util.ArrayList;
import java.util.List;

public class BuyList {
    private String name;
    private final List<Item> items = new ArrayList<>();

    public BuyList(String name) {
        this.name = name;
    }

    public void addToList(Item item) {
        items.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public List<Item> getItems() {
        return items;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}
