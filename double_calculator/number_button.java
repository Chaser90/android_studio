package com.example.doublecalculator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class NumberButton extends AppCompatButton {

    int numberButtonDefault = R.drawable.number_button_default;
    int numberButtonClick = R.drawable.number_button_click;

    public NumberButton(Context context) {
        super(context);
        setBackgroundResource(numberButtonDefault);
        setTextColor(0xFFFFFFFF);
    }

    public NumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(numberButtonDefault);
        setTextColor(0xFFFFFFFF);
    }

    public NumberButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundResource(numberButtonDefault);
        setTextColor(0xFFFFFFFF);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(numberButtonClick);
                setTextColor(0xFFCFCFCF);
                setPadding(10, 10, 0,0);
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(numberButtonDefault);
                setTextColor(0xFFFFFFFF);
                setPadding(0,0,0,0);
                break;
        }
        return true;
    }

}
