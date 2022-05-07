package org.gu.mobile.android;

import io.qameta.allure.Description;
import org.gu.mobile.android.data.models.Item;
import org.gu.mobile.android.pages.ListPage;
import org.gu.mobile.android.pages.MainPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListsSortingTests extends BaseTest {
    List<Item> list = new ArrayList<>();

    @BeforeClass
    public void beforeTest() {
        /*
         * Item1 - name2, category3
         * Item2 - name3, category1
         * Item3 - name1, category2
         */

        Item i1 = Item.getRandomItem();
        i1.setName("Name2");
        i1.setCategory("Medications");
        i1.setComment("Added1");
        list.add(i1);

        Item i2 = Item.getRandomItem();
        i2.setName("Name3");
        i2.setCategory("Child Products");
        i2.setComment("Added2");
        list.add(i2);

        Item i3 = Item.getRandomItem();
        i3.setName("Name1");
        i3.setCategory("Accessories Grocery");
        i3.setComment("Added3");
        list.add(i3);

        new MainPage()
                .addList("ForSorting #1")
                .addListItems(list);
    }

    @Test
    @Description("Sort lists by category")
    public void sortByCategoryTest() {
        list.sort(Comparator.comparing(Item::getCategory));
        new ListPage().openSetting().setSortingOrder(0);
        new ListPage().verifyItemsOrder(list);
    }

    @Test
    @Description("Sort lists by alphabet")
    public void sortByAlphabetTest() {
        list.sort(Comparator.comparing(Item::getName));
        new ListPage().openSetting().setSortingOrder(1);
        new ListPage().verifyItemsOrder(list);
    }

    @Test
    @Description("Sort lists by preorder")
    public void sortByPreOrderTest() {
        list.sort(Comparator.comparing(Item::getComment).reversed());
        new ListPage().openSetting().setSortingOrder(2);
        new ListPage().verifyItemsOrder(list);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        new ListPage().goToMainPage();
    }
}
