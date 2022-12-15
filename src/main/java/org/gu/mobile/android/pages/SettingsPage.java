package org.gu.mobile.android.pages;

import com.codeborne.selenide.Selenide;
import io.appium.java_client.MobileBy;
import org.gu.mobile.android.utils.UIHelper;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SettingsPage extends BasePage {
    private final By settingsItem = MobileBy.id("android:id/title");
    private final String menuItemSelector = "new UiSelector().text(\"%s\")";
    private final By alertTitle = MobileBy.id("android:id/alertTitle");
    private final By sortTypeItem = MobileBy.id("android:id/text1");

    public void setSortingOrder(int sortTypeIndex) {
        $(settingsItem).click();
        $(MobileBy.AndroidUIAutomator(String.format(menuItemSelector, "Sort list"))).click();
        $(alertTitle).shouldBe(visible);
        $$(sortTypeItem).get(sortTypeIndex).click();
        Selenide.back();
    }

    public void verifyCategoriesList(List<String> expected) {
        $(settingsItem).click();
        UIHelper.selectOption("Categories list");

        List<String> list = new ArrayList<>($$(MobileBy.id("title")).texts());

        $(MobileBy.AndroidUIAutomator("new UiScrollable("
                + "new UiSelector().scrollable(true)).scrollIntoView("
                + "new UiSelector().textContains(\"Alcohol, tobacco\"));")).shouldBe(visible);

        list.addAll($$(MobileBy.id("title")).texts());
        List<String> actual = list.stream().distinct().collect(Collectors.toList());
        Assert.assertEquals(actual, expected);
    }
}
