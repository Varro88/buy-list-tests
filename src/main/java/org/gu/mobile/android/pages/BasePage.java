package org.gu.mobile.android.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    private final By settingsButton = MobileBy.id("com.slava.buylist:id/button1");

    public SettingsPage openSetting() {
        $(settingsButton).click();
        return new SettingsPage();
    }

    public BasePage goBack() {
        if(((AndroidDriver) WebDriverRunner.getWebDriver()).isKeyboardShown()) {
            ((AndroidDriver) WebDriverRunner.getWebDriver()).hideKeyboard();
        }
        Selenide.back();
        return this;
    }
}
