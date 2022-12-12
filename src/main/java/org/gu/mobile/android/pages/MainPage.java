package org.gu.mobile.android.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import io.qameta.allure.Step;
import org.gu.mobile.android.constants.Constants;
import org.gu.mobile.android.data.models.Item;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.appmanagement.ApplicationState.RUNNING_IN_BACKGROUND;

public class MainPage extends BasePage {
    private final By titleLabel = MobileBy.id("textView1");

    private final By listTitleLabel = MobileBy.id("title");
    private final String listTitleXpath = "//android.widget.TextView[@id=\"com.slava.buylist:id/title\n\" and contains(@text, \"%s\")]";

    private final By nameInput = MobileBy.id("editText1");
    private final By addListButton = MobileBy.id("button2");
    private final By infoString = MobileBy.id("str1");

    private final By deleteButton = MobileBy.id("mageView1");
    private final String deleteButtonXpath = "//android.widget.TextView[@resource-id=\"com.slava.buylist:id/title\" and contains(@text, \"%s\")]/following-sibling::android.widget.ImageView[@resource-id=\"com.slava.buylist:id/imageView1\"]";

    private final By okToDeleteButton = MobileBy.id("android:id/button1");

    private final By yesButton = MobileBy.id("button1");
    private final By noButton = MobileBy.id("button2");
    private final By thumbsUpButton = MobileBy.id("imageButton1");

    private final By adsContainer = MobileBy.id("dView");
    private final By adsTitle = MobileBy.className("android.widget.TextView");
    private final By adsContent = MobileBy.className("android.webkit.WebView");

    @Step("Verify 'Buy list' title")
    public MainPage verifyTitle() {
        $(titleLabel).shouldHave(text("Buy list"));
        return this;
    }

    @Step("Create and open list")
    public ListPage addList(String name) {
        fillListName(name);
        return new ListPage();
    }

    @Step("Create list")
    public MainPage fillListName(String name) {
        $(nameInput).val(name);
        $(nameInput).shouldHave(exactText(name));
        $(addListButton).click();
        return this;
    }

    @Step("Verify list info")
    public MainPage verifyItems(List<Item> items) {
        $(infoString)
                .shouldHave(text("All items: " + items.size()))
                .shouldHave(text("Sum: " +
                        items.stream().mapToInt(i -> i.getAmount()*i.getPrice()).sum()));
        return this;
    }

    @Step("Remove list")
    public MainPage removeList(String name) {
        $x(String.format(deleteButtonXpath, name)).click();
        $(okToDeleteButton).click();
        $x(String.format(listTitleXpath, name)).shouldBe(hidden);
        return this;
    }

    @Step("Verify no list available")
    public void verifyNoLists() {
        $$(deleteButton).shouldBe(empty);
    }

    @Step("Cancel exit")
    public void exitAndCancel() {
        Selenide.back();
        $(noButton).click();
        $(titleLabel).shouldBe(visible);
    }

    @Step("Confirm exit")
    public void exitAndConfirm() {
        Selenide.back();
        $(yesButton).click();
        ApplicationState state = ((AndroidDriver) WebDriverRunner.getWebDriver()).queryAppState(Constants.APP_ID);
        assert state == RUNNING_IN_BACKGROUND;
    }

    @Step("Verify ads")
    public MainPage verifyAds() {
        $(adsContainer).$(adsTitle).shouldBe(visible).shouldHave(text("Test Ad"));
        $(adsContainer).$(adsContent).shouldBe(visible);
        return this;
    }

    public void openPlayStore() {
        Selenide.back();
        $(thumbsUpButton).click();
    }

    public MainPage removeAllLists() {
        $$(MobileBy.id("title")).texts().forEach(this::removeList);
        return this;
    }
}
