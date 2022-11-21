package org.gu.mobile.android;

import com.codeborne.selenide.Selenide;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Description;
import org.gu.mobile.android.data.models.BuyList;
import org.gu.mobile.android.data.models.Item;
import org.gu.mobile.android.pages.ListPage;
import org.gu.mobile.android.pages.MainPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class FlowTests extends BaseTest {
    @Test
    @Description("Add new list with items")
    public void addNewList() {
        BuyList list = new BuyList("AddNewList");
        list.addToList(Item.getRandomItem());
        list.addToList(Item.getRandomItem());
        new MainPage()
                .addList(list.getName())
                .addListItems(list.getItems())
                .verifyItems(list.getItems())
                .goToMainPage()
                .verifyItems(list.getItems())
                .removeList(list.getName());
    }

    @Test
    @Description("Create list and cancel item adding")
    public void cancelListCreating() {
        BuyList list = new BuyList("CancelList");
        Item tempItem = Item.getRandomItem();
        ((ListPage)(new MainPage()
                .addList(list.getName())
                .fillItemField(tempItem)
                .goBack()))
                .cancel();
        new MainPage()
                .verifyItems(list.getItems())
                .removeList(list.getName());
    }

    @AfterMethod
    public void openMainPage() {
        while(!$(MobileBy.id("com.slava.buylist:id/textView1")).text().equals("Buy list")) {
            Selenide.back();
            if($(MobileBy.id("com.slava.buylist:id/button1")).text().equalsIgnoreCase("yes")) {
                $(MobileBy.id("com.slava.buylist:id/button1")).click();
            }
        }
        new MainPage().removeAllLists();
    }
}
