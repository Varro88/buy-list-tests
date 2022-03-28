package org.gu.mobile.android;

import org.gu.mobile.android.data.models.BuyList;
import org.gu.mobile.android.data.models.Item;
import org.gu.mobile.android.pages.MainPage;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class MainPageTests extends BaseTest {



    @Test
    public void verifyMainPageElements() {
        new MainPage(driver).verifyTitle().verifyAds();
    }
}
