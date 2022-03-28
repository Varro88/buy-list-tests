package org.gu.mobile.android;

import io.appium.java_client.android.AndroidDriver;
import org.gu.mobile.android.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExitTests extends BaseTest {
    @Test
    public void noToStay() {
        new MainPage(driver).exitAndCancel();
    }

    @Test
    public void yesToExit() {
        new MainPage(driver).exitAndConfirm();
    }

    @Test
    public void thumbsUpToOpenPlayStore() {
        new MainPage(driver).openPlayStore();
        Assert.assertEquals(((AndroidDriver)driver).currentActivity(), "com.google.android.gms");
    }
}
