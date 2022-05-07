package org.gu.mobile.android.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    protected WebDriver driver;
    private final By settingsButton = MobileBy.id("com.slava.buylist:id/button1");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public SettingsPage openSetting() {
        $(settingsButton).click();
        return new SettingsPage(driver);
    }

    public BasePage goBack() {
        if(((AndroidDriver) WebDriverRunner.getWebDriver()).isKeyboardShown()) {
            ((AndroidDriver) WebDriverRunner.getWebDriver()).hideKeyboard();
        }
        Selenide.back();
        return this;
    }
}
