package com.example.mixer.telerikcalendartest;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Personal on 11/17/14.
 */
public class customTimeTextView extends TextView implements TimePickerDialog.OnTimeSetListener{


    private Context _context;

    public customTimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        _context = context;
    }

    public customTimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _context = context;
        setAttributes();
    }

    public customTimeTextView(Context context) {
        super(context);
        _context = context;
        setAttributes();
    }

    private void setAttributes() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });
    }

    private void showTimeDialog() {
        final Calendar c = Calendar.getInstance();
        TimePickerDialog tp = new TimePickerDialog(_context,this,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true);
        tp.show();
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i2) {

        setText(new StringBuilder().append(i).append(":")
                .append(i2));


    }
}
