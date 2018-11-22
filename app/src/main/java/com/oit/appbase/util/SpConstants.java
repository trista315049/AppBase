package com.oit.appbase.util;

/**
 * @author trista
 * @date 2018/9/14
 * @function
 */
public class SpConstants {

    private static final String CURRENT_THEME = "theme_current";
    public static final int CARD_RED = 0x1;
    public static final int CARD_v = 0x2;
    public static final int CARD_BULE = 0x3;
    public static final int CARD_GREEN = 0x4;

    public static int getTheme() {
        return (int) SpUtil.get(CURRENT_THEME, CARD_RED);
    }

    public static void setTheme(int theme) {
        SpUtil.get(CURRENT_THEME, theme);
    }

    public static String SP_VERSION_CODE = "sp_version_code";
    public static String SP_IS_LOGIN = "sp_is_login";
    public static String BUGLY_ID = "42e6c5a08e";//bugly
    public static String BUGLY_KEY = "6436534c-c3dd-4b22-9568-e77498a57d1c";//bugly

    public static String test = "onebox/exchange/currency";
}
