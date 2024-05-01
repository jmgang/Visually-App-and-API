package com.visually.utils;

import android.app.AlertDialog;
import android.content.Context;


public class ComponentUtils {

//    public static void showAlert(Context context, String title, String message, DialogInterface.OnClickListener okListener) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.setPositiveButton("OK", okListener);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

    public static void showAlert(Context context, String title, String message, int styleId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, styleId);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, id) -> {
            // Do nothing
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
