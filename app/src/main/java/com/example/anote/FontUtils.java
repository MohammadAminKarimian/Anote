package com.example.anote;

import android.content.Context;
import android.graphics.Typeface;

public class FontUtils {
    private static Typeface iranSans, iranSansLight, iranSansUltraLight,
            iranSansMedium, iranSansBold;


    public static Typeface getIranSans(Context context) {

        return iranSans = Typeface.createFromAsset(context.getAssets(),
                "fonts/IRANSansMobile(FaNum).ttf");
    }

    public static Typeface getIranSansLight(Context context) {
        return iranSansLight = iranSans = Typeface.createFromAsset(context.getAssets(),
                "fonts/IRANSansMobile(FaNum)_Light.ttf");
    }

    public static Typeface getIranSansUltraLight(Context context) {
        return iranSansUltraLight = iranSansLight = iranSans = Typeface.createFromAsset(context.getAssets(),
                "fonts/IRANSansMobile(FaNum)_UltraLight.ttf");
    }

    public static Typeface getIranSansMedium(Context context) {
        return iranSansMedium = iranSansLight = iranSans = Typeface.createFromAsset(context.getAssets(),
                "fonts/IRANSansMobile(FaNum)_Medium.ttf");
    }

    public static Typeface getIranSansBold(Context context) {
        return iranSansBold = iranSansLight = iranSans = Typeface.createFromAsset(context.getAssets(),
                "fonts/IRANSansMobile(FaNum)_Bold.ttf");
    }
}