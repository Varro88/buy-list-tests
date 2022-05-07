package org.gu.mobile.android;

import com.codeborne.selenide.ex.ElementShould;
import io.qameta.allure.Description;
import org.gu.mobile.android.data.DataProviders;
import org.gu.mobile.android.data.models.BuyList;
import org.gu.mobile.android.data.models.Item;
import org.gu.mobile.android.pages.MainPage;
import org.testng.annotations.Test;

import java.util.List;

public class ListPageTests extends BaseTest {
    @Test
    @Description("Add and remove several items")
    public void deleteItem() {
        BuyList list = new BuyList("LIST_DeleteItem");
        list.addToList(Item.getRandomItem());
        new MainPage()
                .addList(list.getName())
                .addListItems(list.getItems())
                .removeItem(list.getItem(0))
                .cancelRemoving()
                .verifyItems(list.getItems())
                .removeItem(list.getItem(0))
                .verifyListIsEmpty()
                .goToMainPage()
                .removeList(list.getName());
    }

    @Test
    @Description("Edit list item")
    public void editItem() {
        BuyList list = new BuyList("LIST_EditItem");
        list.addToList(Item.getRandomItem());
        Item anotherItem = Item.getRandomItem();
        new MainPage()
                .addList(list.getName())
                .addListItems(list.getItems())
                .editItem(list.getItem(0), anotherItem)
                .verifyItems(List.of(anotherItem))
                .goToMainPage()
                .removeList(list.getName());
    }

    @Test(dataProvider = "tooShortListNames", dataProviderClass = DataProviders.class)
    @Description("User cannot create list with too short name")
    public void cantCreateListWithTooShortName(String name) {
        new MainPage().fillListName(name)
                .verifyTitle();
    }

    @Test(dataProvider = "validListNames", dataProviderClass = DataProviders.class)
    @Description("User can create list with allowed name length")
    public void canCreateListWithValidNameLength(String name) {
        new MainPage().addList(name)
                .verifyListTitle(name)
                .goToMainPage()
                .removeList(name);
    }

    @Test(expectedExceptions = {ElementShould.class },
            dataProvider = "tooLongListNames", dataProviderClass = DataProviders.class)
    @Description("User cannot create list with too long name")
    public void cantCreateListWithTooLongName(String name) {
        new MainPage().fillListName(name);
    }
}
