package com.oit.appbase.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.oit.appbase.R;
import com.oit.appbase.base.BaseToolBarActivity;

/**
 * @author trista
 * @date 2018/11/22
 * @function
 */
public class SpannableStringActivity extends BaseToolBarActivity {
    private TextView mForeground;
    private TextView mBackground;
    private TextView mSizespan;
    private TextView mStrikethrough;
    private TextView mUnderline;
    private TextView mSuperscript;
    private TextView mSubscript;
    private TextView mStyle;
    private TextView mImage;
    private TextView mClickable;
    private TextView mUrls;
    private TextView mMove;





    @Override
    public void setRootView() {
        setContentView(R.layout.activity_spannable_string);
        setTitle("SpannableString");
    }

    @Override
    public void initView() {
        mForeground = findViewById(R.id.foreground);
        mBackground = findViewById(R.id.background);
        mSizespan = findViewById(R.id.sizespan);
        mStrikethrough = findViewById(R.id.strikethrough);
        mUnderline = findViewById(R.id.underline);
        mSuperscript = findViewById(R.id.superscript);
        mSubscript = findViewById(R.id.subscript);
        mStyle = findViewById(R.id.style);
        mImage = findViewById(R.id.image);
        mClickable = findViewById(R.id.clickable);
        mUrls = findViewById(R.id.urls);
        mMove = findViewById(R.id.move);

    }

    @Override
    public void initData() {

        setTextForeGround(mForeground);
        setTextBackGround(mBackground);
        setTextSizePan(mSizespan);
        setTextStrikethrough(mStrikethrough);
        setTextUnderline(mUnderline);
        setTextSuperscript(mSuperscript);
        setTextSubscript(mSubscript);
        setTextStyle(mStyle);
        setTextImage(mImage);
        setTextClickable(mClickable);
        setUrls(mUrls);
        string = mMove.getText().toString();

        handler.sendEmptyMessage(0x158);


    }

    private String string;

    private int position = 0;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x158:
                    SpannableString spannableString = new SpannableString(string);

                    RelativeSizeSpan sizeSpan = new RelativeSizeSpan(1.2f);
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
                    ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.parseColor("#333333"));
                    if (position>0){
                        spannableString.setSpan(colorSpan1, 0, position, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    }
                    spannableString.setSpan(colorSpan1, position+1, mMove.getText().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(sizeSpan, position, position + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(colorSpan, position, position + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    mMove.setText(spannableString);
                    position++;
                    if(position >= mMove.getText().toString().length()) {
                        position = 0;
                    }
                    handler.sendEmptyMessageDelayed(0x158, 150);
                    break;
            }
        }
    };


    private void setUrls(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        URLSpan urlSpan = new URLSpan("http://ehomeinternet.com");
        spannableString.setSpan(urlSpan, 3, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.parseColor("#ff0000"));
        textView.setText(spannableString);



    }

    private void setTextClickable(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText());
        MyClickableSpan clickableSpan = new MyClickableSpan("http://ehomeinternet.com");
        spannableString.setSpan(clickableSpan, 3, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.parseColor("#ff0000"));
        textView.setText(spannableString);
    }
    class MyClickableSpan extends ClickableSpan {

        private String content;

        public MyClickableSpan(String content) {
            this.content = content;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            ToastUtils.showLongToast(content);
        }
    }

    private void setTextImage(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, 42, 42);
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableString.setSpan(imageSpan, 3, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);


    }

    private void setTextStyle(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        StyleSpan styleSpan_B = new StyleSpan(Typeface.BOLD);
        StyleSpan styleSpan_I = new StyleSpan(Typeface.ITALIC);
        spannableString.setSpan(styleSpan_B, 2, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(styleSpan_I, 6, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setHighlightColor(Color.parseColor("#ff0000"));
        textView.setText(spannableString);


    }

    private void setTextSubscript(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        SubscriptSpan underlineSpan = new SubscriptSpan();
        spannableString.setSpan(underlineSpan, spannableString.length() - 2, spannableString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(spannableString);
    }

    private void setTextSuperscript(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        SuperscriptSpan underlineSpan = new SuperscriptSpan();
        spannableString.setSpan(underlineSpan, spannableString.length() - 2, spannableString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(spannableString);
    }


    private void setTextUnderline(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableString.setSpan(underlineSpan, 5, spannableString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(spannableString);

    }

    private void setTextSizePan(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText());

        RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.2f);
        RelativeSizeSpan sizeSpan02 = new RelativeSizeSpan(1.4f);
        RelativeSizeSpan sizeSpan03 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan sizeSpan04 = new RelativeSizeSpan(1.8f);
        RelativeSizeSpan sizeSpan05 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan sizeSpan06 = new RelativeSizeSpan(1.4f);
        RelativeSizeSpan sizeSpan07 = new RelativeSizeSpan(1.2f);

        spannableString.setSpan(sizeSpan01, 1, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan02, 3, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan03, 5, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan04, 7, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan05, 9, 11, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan06, 11, 13, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan07, 13, 15, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);


    }

    private void setTextForeGround(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        spannableString.setSpan(colorSpan, 3, spannableString.length() - 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    public void setTextBackGround(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        BackgroundColorSpan colorSpan = new BackgroundColorSpan(Color.parseColor("#0099EE"));
        spannableString.setSpan(colorSpan, 3, spannableString.length() - 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(spannableString);
    }


    public void setTextStrikethrough(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 5, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);

    }
}
