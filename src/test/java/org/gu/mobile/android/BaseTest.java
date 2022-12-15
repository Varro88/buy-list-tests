package org.gu.mobile.android;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.gu.mobile.android.driver.DriverFactory;
import org.openqa.selenium.ScreenOrientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

public class BaseTest {
    static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @BeforeMethod
    protected void startTest(Method method) {
        String testName = method.getName();
        logger.info("Starting test: " + testName);
    }

    @AfterMethod
    protected void endTest(Method method) {
        String testName = method.getName();
        logger.info("Finishing test: " + testName);
    }

    @BeforeSuite
    public void before() throws IOException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        AppiumDriver driver = DriverFactory.createDriver();
        driver.rotate(ScreenOrientation.PORTRAIT);
        WebDriverRunner.setWebDriver(driver);
    }
}
