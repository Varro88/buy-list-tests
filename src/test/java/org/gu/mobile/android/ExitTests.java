package org.gu.mobile.android;

import io.qameta.allure.Description;
import org.gu.mobile.android.pages.MainPage;
import org.testng.annotations.Test;

public class ExitTests extends BaseTest {
    private MainPage mainPage = new MainPage();

    @Test(description = "Cancel app exit")
    @Description("Exit app and cancel it")
    public void noToStay() {
        mainPage.exitAndCancel();
    }

    @Test(description = "Proceed to app exit")
    @Description("Exit app and confirm it")
    public void yesToExit() {
        mainPage.exitAndConfirm();
    }
}
