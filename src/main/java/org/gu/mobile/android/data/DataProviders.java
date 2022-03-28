package org.gu.mobile.android.data;

import io.qala.datagen.RandomShortApi;
import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "tooShortListNames")
    public static Object[][] tooShortListNames() {
        return new Object[][] {
                {""},
                {RandomShortApi.alphanumeric(1)},
                {RandomShortApi.alphanumeric(2)}
        };
    }

    @DataProvider(name = "validListNames")
    public static Object[][] validListNames() {
        return new Object[][] {
                {RandomShortApi.alphanumeric(3)},
                {RandomShortApi.alphanumeric(30)},
        };
    }

    @DataProvider(name = "tooLongListNames")
    public static Object[][] tooLongListNames() {
        return new Object[][] {
                {RandomShortApi.alphanumeric(31)},
        };
    }

}
