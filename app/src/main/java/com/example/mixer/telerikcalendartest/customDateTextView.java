package com.example.mixer.telerikcalendartest;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Personal on 11/17/14.
 */
public class customDateTextView extends TextView implements DatePickerDialog.OnDateSetListener {

        private Context _context;

        public customDateTextView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            _context = context;
        }

        public customDateTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
            _context = context;
            setAttributes();
        }

        public customDateTextView(Context context) {
            super(context);
            _context = context;
            setAttributes();
        }

        private void setAttributes() {
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDateDialog();
                }
            });
        }

        private void showDateDialog() {
            final Calendar c = Calendar.getInstance();

            Calendar cal=Calendar.getInstance();
            SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
            String month_name = month_date.format(cal.getTime());


            DatePickerDialog dp = new DatePickerDialog(_context, this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            dp.show();
        }


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            setText(String.format("%s/%s/%s", dayOfMonth, monthOfYear+1, year));

        }


}
