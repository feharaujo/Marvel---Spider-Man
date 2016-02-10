package br.com.fearaujo.marvel.util;

import android.view.Window;
import android.view.WindowManager;

public class StatusBarHelper {

    public static void setStatusBarTranslucent(boolean makeTranslucent, Window window) {
        if (makeTranslucent) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
