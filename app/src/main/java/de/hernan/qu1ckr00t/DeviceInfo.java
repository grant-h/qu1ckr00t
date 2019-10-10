package de.hernan.qu1ckr00t;

import android.os.Build;
import android.text.TextUtils;

public class DeviceInfo {
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    public static String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getAndroidPatchLevel() {
        return Build.VERSION.SECURITY_PATCH;
    }

    public static String getKernelVersion() {
        return System.getProperty("os.version");
    }

    public static String getDeviceArchitecture() {
        return System.getProperty("os.arch");
    }

    public static String getBuildFingerprint() {
        return Build.FINGERPRINT;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }
}
