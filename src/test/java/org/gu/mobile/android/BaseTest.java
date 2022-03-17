package org.gu.mobile.android;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.gu.mobile.android.driver.DriverFactory;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void beforeClass() throws IOException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        driver = DriverFactory.createDriver();
        ((AndroidDriver)driver).rotate(ScreenOrientation.PORTRAIT);
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterClass
    public void afterClass() {
        ((AppiumDriver)WebDriverRunner.getWebDriver()).closeApp();
        if(WebDriverRunner.hasWebDriverStarted()) {
            Selenide.closeWebDriver();
        }
    }
}
