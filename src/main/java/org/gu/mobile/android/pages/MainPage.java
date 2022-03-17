package org.gu.mobile.android.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.appmanagement.ApplicationState;
import io.qameta.allure.Step;
import org.gu.mobile.android.Utils.UIHelper;
import org.gu.mobile.android.constants.Constants;
import org.gu.mobile.android.data.models.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.appmanagement.ApplicationState.NOT_RUNNING;
import static io.appium.java_client.appmanagement.ApplicationState.RUNNING_IN_BACKGROUND;

public class MainPage extends BasePage {
    private final By titleLabel = MobileBy.id("com.slava.buylist:id/textView1");
    private final By nameInput = MobileBy.id("com.slava.buylist:id/editText1");
    private final By addListButton = MobileBy.id("com.slava.buylist:id/button2");
    private final By infoString = MobileBy.id("com.slava.buylist:id/str1");
    private final By deleteButton = MobileBy.id("com.slava.buylist:id/imageView1");
    private final By okToDeleteButton = MobileBy.id("android:id/button1");

    private final By yesButton = MobileBy.id("com.slava.buylist:id/button1");
    private final By noButton = MobileBy.id("com.slava.buylist:id/button2");

    private final By adsContainer = MobileBy.id("com.slava.buylist:id/adView");
    private final By adsTitle = MobileBy.className("android.widget.TextView");
    private final By adsContent = MobileBy.className("android.webkit.WebView");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void verifyTitle() {
        $(titleLabel).shouldHave(text("Buy list"));
    }

    @Step("Add list")
    public ListPage addList(String name) {
        $(nameInput).val(name);
        $(addListButton).click();
        return new ListPage(driver);
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
        $$(deleteButton).get(1).click();
        $(okToDeleteButton).click();
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
        ApplicationState state = ((AppiumDriver)driver).queryAppState(Constants.APP_ID);
        assert state == RUNNING_IN_BACKGROUND;
    }

    @Step("Verify ads")
    public void verifyAds() {
        $(adsContainer).$(adsTitle).shouldBe(visible).shouldHave(text("Test Ad"));
        $(adsContainer).$(adsContent).shouldBe(visible);
    }
}
