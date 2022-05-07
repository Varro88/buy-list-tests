package org.gu.mobile.android;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.gu.mobile.android.driver.DriverFactory;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    protected void startTest(Method method) throws Exception {
        String testName = method.getName();
        System.out.println("[INFOoo] Starting test: " + testName);
    }

    @AfterMethod
    protected void endTest(Method method) throws Exception {
        String testName = method.getName();
        System.out.println("[INFOoo] Finishing test: " + testName);
    }

    @BeforeSuite
    public void before() throws IOException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        driver = DriverFactory.createDriver();
        ((AndroidDriver)driver).rotate(ScreenOrientation.PORTRAIT);
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterSuite
    public void after() {
        ((AppiumDriver)WebDriverRunner.getWebDriver()).closeApp();
        if(WebDriverRunner.hasWebDriverStarted()) {
            Selenide.closeWebDriver();
        }
    }
}
