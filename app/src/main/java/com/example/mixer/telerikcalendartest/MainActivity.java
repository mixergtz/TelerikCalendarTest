package com.example.mixer.telerikcalendartest;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.telerik.widget.calendar.CalendarCell;
import com.telerik.widget.calendar.CalendarDisplayMode;
import com.telerik.widget.calendar.CalendarSelectionMode;
import com.telerik.widget.calendar.RadCalendarView;
import com.telerik.widget.calendar.WeekNumbersDisplayMode;
import com.telerik.widget.calendar.events.Event;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends Activity {
    Button btnAddEvent;
    RadCalendarView calendarView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddEvent = (Button)findViewById(R.id.addEventToCalendar);
        calendarView = (RadCalendarView)findViewById(R.id.calendarView);

        calendarView.setWeekNumbersDisplayMode(WeekNumbersDisplayMode.None);
        calendarView.getGridLinesLayer().setColor(Color.TRANSPARENT);
        calendarView.setSelectionMode(CalendarSelectionMode.Single);
        calendarView.setDisplayMode(CalendarDisplayMode.Year);

        readEventsFromCalendars();




        calendarView.setOnCellClickListener(new RadCalendarView.OnCellClickListener() {
            @Override
            public void onCellClick(CalendarCell calendarCell) {
                if (calendarCell.getEvents() != null &&
                        calendarCell.getEvents().size() > 0) {

                    Event firstEvent = calendarCell.getEvents().get(0);

                    String eventInfo = String.format("%tT - %tT: %s",
                            firstEvent.getStartDate(),
                            firstEvent.getEndDate(),
                            firstEvent.getTitle());

                    Toast.makeText(
                            getApplicationContext(),
                            eventInfo,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra(CalendarContract.Events.TITLE, "Hola");
            intent.putExtra(CalendarContract.Events.ALL_DAY, false);// periodicity
            intent.putExtra(CalendarContract.Events.DESCRIPTION, "Hola este es un evento");
            startActivityForResult(intent, 1);

            }
        });

    }


    private void readEventsFromCalendars(){
        List<Event> events = new ArrayList<Event>();

        Cursor cursor = this.getContentResolver()
                .query(
                        Uri.parse("content://com.android.calendar/events"),
                        new String[] { "calendar_id", "title", "description",
                                "dtstart", "dtend", "eventLocation" }, null,
                        null, null);
        cursor.moveToFirst();

        // fetching calendars name
        String CNames[] = new String[cursor.getCount()];

        // fetching calendars id
        for (int i = 0; i < CNames.length; i++) {

            Event event = new Event(cursor.getString(1), Long.parseLong(cursor.getString(3)), Long.parseLong(cursor.getString(4)));
            events.add(event);
            CNames[i] = cursor.getString(1);
            cursor.moveToNext();

        }

        calendarView.getEventAdapter().setEvents(events);
        calendarView.notifyDataChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Calendar App doesn't return a resultCode depending on result, so we just update the calendar anyway
        readEventsFromCalendars();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
