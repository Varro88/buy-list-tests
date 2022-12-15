package org.gu.mobile.android.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.gu.mobile.android.utils.UIHelper;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    private final By settingsButton = MobileBy.id("button1");

    public SettingsPage openSetting() {
        $(settingsButton).click();
        return new SettingsPage();
    }

    public BasePage goBack() {
        UIHelper.hideKeyboard();
        Selenide.back();
        return this;
    }
}
