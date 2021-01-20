package utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class UtilSharedPreference {

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences("pref", Context.MODE_PRIVATE);
    }

    public static void setInteger(Context context, String key, int value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

//    public static void setBoolean(Context context, String key, boolean value) {
//        SharedPreferences prefs = getPreferences(context);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putBoolean(key, value);
//        editor.apply();
//    }

    public static int getInteger(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        if ((prefs != null) && (prefs.contains(key))) {
            int value = prefs.getInt(key, 0);
            return value;
        } else {
            return 0;
        }
    }

//    public static boolean getBoolean(Context context, String key) {
//        SharedPreferences prefs = getPreferences(context);
//        if (prefs != null && prefs.contains(key)) {
//            boolean value = prefs.getBoolean(key, false);
//            return value;
//        } else {
//            return false;
//        }
//    }

    public static void clear(Context context) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
