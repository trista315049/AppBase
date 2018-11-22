package com.oit.appbase.weight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oit.appbase.R;
import com.oit.appbase.util.SpConstants;

/**
 * @author trista
 * @date 2018/11/21
 * @function
 */
public class CardPickerDialog extends DialogFragment implements View.OnClickListener {

    private int mCurrentTheme;
    private TextView mRed;
    private TextView mViolet;
    private TextView mBlue;
    private TextView mGreen;
    private TextView mCancel;
    private TextView mOk;
    private ClickListener mClickListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.AppTheme_AppCompat_Dialog_Alert);
        mCurrentTheme = SpConstants.getTheme();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_theme_picker, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRed = view.findViewById(R.id.red);
        mViolet = view.findViewById(R.id.violet);
        mBlue = view.findViewById(R.id.blue);
        mGreen = view.findViewById(R.id.green);
        mCancel = view.findViewById(R.id.cancel);
        mOk = view.findViewById(R.id.ok);

        mRed.setOnClickListener(this);
        mViolet.setOnClickListener(this);
        mBlue.setOnClickListener(this);
        mGreen.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mOk.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                dismiss();
                break;
            case R.id.ok:
                if (mClickListener != null) {
                    SpConstants.setTheme(mCurrentTheme);
                    mClickListener.onConfirm(mCurrentTheme);
                    dismiss();
                }
                break;
            case R.id.red:
                mCurrentTheme = SpConstants.CARD_RED;
                break;
            case R.id.violet:
                mCurrentTheme = SpConstants.CARD_v;
                break;
            case R.id.blue:
                mCurrentTheme = SpConstants.CARD_BULE;
                break;
            case R.id.green:
                mCurrentTheme = SpConstants.CARD_GREEN;

                break;
        }
    }

    public void setClickListener(ClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface ClickListener {
        void onConfirm(int currentTheme);
    }
}
