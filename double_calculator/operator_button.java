package com.example.doublecalculator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatImageButton;

public class OperatorButton extends AppCompatImageButton {

    int OperatorButtonDefault = R.drawable.operator_button_default;
    int OperatorButtonClick = R.drawable.operator_button_click;

    public OperatorButton(Context context) {
        super(context);
        init();
    }

    public OperatorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OperatorButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        setBackgroundResource(OperatorButtonDefault);
        setPadding(80,80,80,80);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(OperatorButtonClick);
                setPadding(90,90,80,80);
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(OperatorButtonDefault);
                setPadding(80,80,80,80);
                break;
        }
        return true;
    }
}
