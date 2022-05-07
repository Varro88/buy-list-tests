package org.gu.mobile.android;

import io.qameta.allure.Description;
import org.gu.mobile.android.pages.MainPage;
import org.testng.annotations.Test;

public class MainPageTests extends BaseTest {

    @Test
    @Description("Verify main page elements")
    public void verifyMainPageElements() {
        new MainPage(driver).verifyTitle().verifyAds();
    }
}
