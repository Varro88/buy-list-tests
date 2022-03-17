package org.gu.mobile.android;

import org.gu.mobile.android.data.models.BuyList;
import org.gu.mobile.android.data.models.Item;
import org.gu.mobile.android.pages.ListPage;
import org.gu.mobile.android.pages.MainPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListsSortingTests extends BaseTest {
    List<Item> items = new ArrayList<>();

    @BeforeClass
    public void beforeClass() {
        /*
        * Item1 - name2, category3
        * Item2 - name3, category1
        * Item3 - name1, category2
        */
        BuyList list = new BuyList("ForSorting #1");
        Item i1 = Item.getRandomItem();
        i1.setName("Name2");
        i1.setCategory("Stationery");
        i1.setComment("Added1");
        items.add(i1);

        Item i2 = Item.getRandomItem();
        i2.setName("Name3");
        i2.setCategory("Dairy produce");
        i2.setComment("Added2");
        items.add(i2);

        Item i3 = Item.getRandomItem();
        i3.setName("Name1");
        i3.setCategory("Accessories");
        i3.setComment("Added3");
        items.add(i3);

        for (Item i : items) {
            list.addToList(i);
        }

        new MainPage(driver)
                .addList(list.getName())
                .addListItems(list.getItems());
    }

    @Test
    public void sortByCategoryTest() {
        new ListPage(driver).setSortingOrder(0);
        items.sort(Comparator.comparing(Item::getCategory));
        new ListPage(driver).verifyItemsOrder(items);
    }

    @Test
    public void sortByAlphabetTest() {
        new ListPage(driver).setSortingOrder(1);
        items.sort(Comparator.comparing(Item::getName));
        new ListPage(driver).verifyItemsOrder(items);
    }

    @Test
    public void sortByPreOrderTest() {
        new ListPage(driver).setSortingOrder(2);
        items.sort(Comparator.comparing(Item::getComment));
        new ListPage(driver).verifyItemsOrder(items);
    }
}
