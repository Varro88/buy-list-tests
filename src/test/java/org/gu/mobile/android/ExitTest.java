package org.gu.mobile.android;

import org.gu.mobile.android.pages.MainPage;
import org.testng.annotations.Test;

public class ExitTest extends BaseTest {
    @Test
    public void noToStay() {
        new MainPage(driver).exitAndCancel();
    }

    @Test
    public void yesToExit() {
        new MainPage(driver).exitAndConfirm();
    }
}
