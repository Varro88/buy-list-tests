package org.gu.mobile.android.pages;

import com.codeborne.selenide.Selenide;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasePage {
    protected WebDriver driver;
    private final By settingsButton = MobileBy.id("com.slava.buylist:id/button1");
    private final By settingsItem = MobileBy.id("android:id/title");
    private final By sortListItem = MobileBy.id("android:id/title");
    private final By sortTypeItem = MobileBy.id("android:id/text1");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setSortingOrder(int sortTypeIndex) {
        $(settingsButton).click();
        $(settingsItem).click();
        $(sortListItem).click();
        $$(sortTypeItem).get(sortTypeIndex).click();
        Selenide.back();
    }
}
