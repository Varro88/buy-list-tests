package org.gu.mobile.android.Utils;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofMillis;

import org.openqa.selenium.interactions.Actions;


public class UIHelper {
    public static void selectOption(String text) {
        $(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ text +"\").instance(0))")).click();
    }

    public static void tapByElement (SelenideElement element) {
        new TouchAction((PerformsTouchActions) WebDriverRunner.getWebDriver())
                .tap(tapOptions().withElement(element(element)))
                .waitAction(waitOptions(ofMillis(250))).perform();
    }

    public static void longTap(SelenideElement element) {
        new Actions(element.getWrappedDriver()).clickAndHold(element).perform();

        /*TouchActions action = new TouchActions(element.getWrappedDriver());
        action.longPress(element);
        action.perform();*/
    }

    /*public static void tap(SelenideElement element) {
        TouchAction action = new TouchAction((PerformsTouchActions) WebDriverRunner.getWebDriver());
        //TouchAction action = new TouchAction(WebDriverRunner.getWebDriver());

        WebElement resource = WebDriverRunner.getWebDriver().findElement(By.id("com.xxx.verify.xxxtestapp:id/oauth2_resource_edittext"));
        WebElement openid = WebDriverRunner.getWebDriver().findElement(By.id("com.xxx.verify.xxxtestapp:id/oauth2_scope_edittext"));
        TouchAction action = new TouchAction(WebDriverRunner.getWebDriver());
        action.press((PointOption) resource).moveTo(openid).release();
        action.perform();

        TapOptions to = new TapOptions();
        to.withElement((ElementOption) element);
        action.tap(TapOp)
        action.perform();
    }*/
}
