package org.gu.mobile.android.utils;

import java.util.List;

import static io.qala.datagen.RandomShortApi.integer;

public class DataHelper {
    public static String getRandomListItem(List<String> items) {
        if(items.size() == 0) {
            return null;
        }
        return items.get(integer(0, items.size() - 1));
    }
}
