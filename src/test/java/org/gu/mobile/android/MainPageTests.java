package org.gu.mobile.android;

import org.gu.mobile.android.data.models.BuyList;
import org.gu.mobile.android.data.models.Item;
import org.gu.mobile.android.pages.MainPage;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class MainPageTests extends BaseTest {
    @Test
    public void addNewList() {
        BuyList list = new BuyList("AddNewList");
        list.addToList(Item.getRandomItem());
        list.addToList(Item.getRandomItem());
        new MainPage(driver)
                .addList(list.getName())
                .addListItems(list.getItems())
                .verifyItems(list.getItems())
                .goToMainPage()
                .verifyItems(list.getItems())
                .removeList(list.getName());
    }

    @Test
    public void cancelListCreating() {
        BuyList list = new BuyList("CancelList");
        Item tempItem = Item.getRandomItem();
        new MainPage(driver)
                .addList(list.getName())
                .addListItems(List.of(tempItem))
                .cancel();
        new MainPage((driver))
                .verifyItems(list.getItems())
                .removeList(list.getName());
    }

    @Test
    public void checkAdsAreShown() {
        new MainPage(driver).verifyAds();
    }
}
