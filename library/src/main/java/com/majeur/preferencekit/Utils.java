package com.majeur.preferencekit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.widget.NumberPicker;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

final class Utils {

    public static final int COLOR_ACCENT = Color.parseColor("#ff80cbc4");

    public static Drawable getDividerDrawable(NumberPicker numberPicker) {
        try {
            Field field = NumberPicker.class.getDeclaredField("mSelectionDivider");
            field.setAccessible(true);
            return (Drawable) field.get(numberPicker);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isArrayEmpty(Object[] a) {
        return a == null || a.length == 0;
    }

    public static int dpToPx(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static int spToPx(Context context, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                context.getResources().getDisplayMetrics());
    }

    public static int getAttrColor(Context context, int resAttrId) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(new int[]{resAttrId});
        int color = typedArray.getColor(0, 0);
        typedArray.recycle();
        return color;
    }

    public static int getAttrDimen(Context context, int resAttrId) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(new int[]{resAttrId});
        int color = typedArray.getDimensionPixelSize(0, 0);
        typedArray.recycle();
        return color;
    }

    public static boolean isApi17() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    public static boolean isApi16() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static int max(int... integers) {
        Arrays.sort(integers);
        return integers[integers.length - 1];
    }

    public static double polarAngle(double x, double y) {
        if (x >= 0. && y > 0.) {
            return Math.atan(y / x);
        } else if (x <= 0. && y > 0.) {
            return Math.PI - Math.atan(y / -x);
        } else if (x <= 0. && y < 0.) {
            return Math.PI + Math.atan(y / x);
        } else if (x >= 0. && y < 0.) {
            return Math.PI * 2. - Math.atan(-y / x);
        } else if (y == 0. && x != 0.) {
            return (x < 0.) ? Math.PI : 0.;
        } else {
            return 0.;
        }
    }

    public static void registerOnActivityDestroyListener(@NonNull android.preference.Preference preference, @NonNull PreferenceManager.OnActivityDestroyListener listener) {
        try {
            PreferenceManager pm = preference.getPreferenceManager();
            Method method = pm.getClass().getDeclaredMethod(
                    "registerOnActivityDestroyListener",
                    PreferenceManager.OnActivityDestroyListener.class);
            method.setAccessible(true);
            method.invoke(pm, listener);
        } catch (Exception ignored) {
        }
    }

    public static void unregisterOnActivityDestroyListener(@NonNull android.preference.Preference preference, @NonNull PreferenceManager.OnActivityDestroyListener listener) {
        try {
            PreferenceManager pm = preference.getPreferenceManager();
            Method method = pm.getClass().getDeclaredMethod(
                    "unregisterOnActivityDestroyListener",
                    PreferenceManager.OnActivityDestroyListener.class);
            method.setAccessible(true);
            method.invoke(pm, listener);
        } catch (Exception ignored) {
        }
    }
}