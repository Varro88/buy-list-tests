package org.gu.mobile.android;

import org.gu.mobile.android.constants.Constants;
import org.gu.mobile.android.pages.MainPage;
import org.testng.annotations.Test;

public class SettingsTests extends BaseTest {
    @Test
    public void verifyDefaultCategories() {
        new MainPage(driver).openSetting()
                .verifyCategoriesList(Constants.CATEGORIES.subList(1, Constants.CATEGORIES.size()));
    }
}
