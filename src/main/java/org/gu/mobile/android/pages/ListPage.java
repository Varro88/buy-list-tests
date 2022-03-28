package org.gu.mobile.android.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.gu.mobile.android.Utils.UIHelper;
import org.gu.mobile.android.data.models.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ListPage extends BasePage {
    private final By titleLabel = MobileBy.id("com.slava.buylist:id/textView1");
    private final By productNameInput = MobileBy.id("com.slava.buylist:id/editText1");
    private final By addProductButton = MobileBy.id("com.slava.buylist:id/button2");
    private final By priceInput = MobileBy.id("com.slava.buylist:id/editText2");
    private final By currencyLabel = MobileBy.id("com.slava.buylist:id/value");
    private final By amountInput = MobileBy.id("com.slava.buylist:id/editText3");
    private final By unitsList = MobileBy.id("com.slava.buylist:id/spinner1");
    private final String textFormatString = "new UiSelector().text(\"%s\")";
    private final By commentInput = MobileBy.id("com.slava.buylist:id/editText4");
    private final By categoriesList = MobileBy.id("com.slava.buylist:id/spinner2");
    private final By selectedItem = MobileBy.id("android:id/text1");

    private final By itemsList = MobileBy.id("com.slava.buylist:id/listView1");
    private final By itemTitleLabel = MobileBy.id("com.slava.buylist:id/title");
    private final By itemCommentLabel = MobileBy.id("com.slava.buylist:id/str1");
    private final By itemAmountLabel = MobileBy.id("com.slava.buylist:id/TextView01");
    private final By itemPriceLabel = MobileBy.id("com.slava.buylist:id/textView1");

    private final By yesModalButton = MobileBy.id("android:id/button1");
    private final By noModalButton = MobileBy.id("android:id/button2");

    public ListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Add list items")
    public ListPage addListItems(List<Item> items) {
        for (Item item : items) {
            fillItemField(item);
            $(addProductButton).click();
        }
        return this;
    }

    @Step("Fill item fields")
    private void fillItemField(Item item) {
        $(productNameInput).val(item.getName());
        $(priceInput).val(String.valueOf(item.getPrice()));
        $(amountInput).val(String.valueOf(item.getAmount()));
        selectUnit(item.getUnit());
        $(commentInput).val(item.getComment());
        selectCategory(item.getCategory());
    }

    @Step("Confirm removing item")
    public ListPage confirmRemoving() {
        $(yesModalButton).click();
        return this;
    }

    @Step("Cancel removing item")
    public ListPage cancelRemoving() {
        $(noModalButton).click();
        return this;
    }

    @Step("Save list")
    public MainPage save() {
        $(yesModalButton).click();
        return new MainPage(driver);
    }

    @Step("Cancel saving")
    public MainPage cancel() {
        $(noModalButton).click();
        return new MainPage(driver);
    }

    @Step("Back to main page")
    public MainPage goToMainPage() {
        if(((AndroidDriver)WebDriverRunner.getWebDriver()).isKeyboardShown()) {
            Selenide.back();
        }
        Selenide.back();
        return new MainPage(driver);
    }

    @Step("Verify added item")
    public ListPage verifyItems(List<Item> items) {
        $(itemsList).$$(itemTitleLabel)
                .shouldHave(textsInAnyOrder(items.stream().map(Item::getName).collect(Collectors.toList())));
        $(itemsList).$$(itemCommentLabel)
                .shouldHave(textsInAnyOrder(items.stream().map(Item::getComment).collect(Collectors.toList())));
        $(itemsList).$$(itemAmountLabel)
                .shouldHave(textsInAnyOrder(items.stream().map(i->i.getAmount() + " " + i.getUnit()).collect(Collectors.toList())));
        $(itemsList).$$(itemPriceLabel)
                .shouldHave(textsInAnyOrder(items.stream().map(i->i.getPrice() + " " + i.getCurrency()).collect(Collectors.toList())));
        return this;
    }

    @Step("Remove item {item.name}")
    public ListPage removeItem(Item item) {
        UIHelper.longTap($(itemsList).$$(itemTitleLabel).filter(text(item.getName())).get(0));
        $(MobileBy.AndroidUIAutomator(String.format(textFormatString, "Remove"))).click();
        $(itemsList).$$(itemTitleLabel).filter(text(item.getName())).get(0).shouldBe(hidden);
        return this;
    }

    @Step("Edit item {item.name}")
    public ListPage editItem(Item source, Item target) {
        UIHelper.longTap($(itemsList).$$(itemTitleLabel).filter(text(source.getName())).get(0));
        $(MobileBy.AndroidUIAutomator(String.format(textFormatString, "Edit"))).click();
        fillItemField(target);
        $(addProductButton).click();
        return this;
    }

    @Step("Verify items are in specified order")
    public ListPage verifyItemsOrder(List<Item> items) {
        $(itemsList).$$(itemTitleLabel)
                .shouldHave(texts(items.stream().map(Item::getName).collect(Collectors.toList())));
        return this;
    }

    @Step("Select '{unit}' as unit")
    public void selectUnit(String unit) {
        $(unitsList).click();
        UIHelper.selectOption(unit);
    }

    @Step("Select '{unit}' as category")
    public void selectCategory(String category) {
        $(categoriesList).click();
        UIHelper.selectOption(category);
        $(categoriesList).$(selectedItem).shouldHave(text(category));
    }

    @Step("Verify no items in list")
    public ListPage verifyListIsEmpty() {
        $$(itemTitleLabel).shouldHave(size(0));
        return this;
    }

    public void verifyTitle(String name) {
        $(titleLabel).shouldHave(text(name));
    }
}
