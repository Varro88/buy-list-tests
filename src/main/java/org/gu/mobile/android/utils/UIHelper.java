package org.gu.mobile.android.utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofMillis;

public class UIHelper {
    private static final int TAP_DURATION_MS = 250;

    public static void selectOption(String text) {
        $(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ text +"\").instance(0))")).click();
    }

    public static ElementsCollection getListItems() {
        return $$(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()."
                + "scrollable(true)).scrollIntoView(new UiSelector().resourceId('android:id/list'))"));
    }

    public static void tapByElement (SelenideElement element) {
        new TouchAction((PerformsTouchActions) WebDriverRunner.getWebDriver())
                .tap(tapOptions().withElement(element(element)))
                .waitAction(waitOptions(ofMillis(TAP_DURATION_MS))).perform();
    }

    public static void longTap(SelenideElement element) {
        new Actions(element.getWrappedDriver()).clickAndHold(element).perform();
    }
}
