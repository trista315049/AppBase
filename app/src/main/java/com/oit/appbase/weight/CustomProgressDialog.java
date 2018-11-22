package com.oit.appbase.weight;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.oit.appbase.R;


/**
 *
 * @author trista
 * @date 2017/5/1
 */

public class CustomProgressDialog extends ProgressDialog {

    public CustomProgressDialog(Context context, int theme) {
        super(context,theme);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customprogressdialog);
        LinearLayout lin = (LinearLayout) findViewById(R.id.dialo_lin);
        lin.getBackground().setAlpha(180);

    }
    public static CustomProgressDialog getDialog (Context context) {
        CustomProgressDialog dialog = new CustomProgressDialog(context,R.style.dialog);
        return dialog;
    }


}