package org.gu.mobile.android;

import com.codeborne.selenide.Selenide;
import org.gu.mobile.android.data.models.BuyList;
import org.gu.mobile.android.data.models.Item;
import org.gu.mobile.android.pages.MainPage;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ListPageTests extends BaseTest {
    @Test
    public void deleteItem() {
        BuyList list = new BuyList("AddNewList");
        list.addToList(Item.getRandomItem());
        new MainPage(driver)
                .addList(list.getName())
                .addListItems(list.getItems())
                .removeItem(list.getItem(0))
                .cancelRemoving()
                .verifyItems(list.getItems())
                .removeItem(list.getItem(0))
                .verifyListIsEmpty();
        Selenide.back();
    }

    @Test
    public void editItem() {
        BuyList list = new BuyList("AddNewList");
        list.addToList(Item.getRandomItem());
        Item anotherItem = Item.getRandomItem();
        new MainPage(driver)
                .addList(list.getName())
                .addListItems(list.getItems())
                .editItem(list.getItem(0), anotherItem)
                .verifyItems(List.of(anotherItem));
        Selenide.back();
    }
}
