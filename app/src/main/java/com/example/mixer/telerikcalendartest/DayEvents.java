package com.example.mixer.telerikcalendartest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


import com.telerik.widget.calendar.RadCalendarView;
import com.telerik.widget.calendar.events.Event;

import java.util.List;


public class DayEvents extends Activity {
    List<CustomEvent> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_events);

        Intent intent = getIntent();
        long today = intent.getLongExtra("selectedDate", 0);

        //events = CalendarHelper.GetEventsFromCalendarsSingleDay(this, today);

        events = CalendarHelper.GetEventsFromCalendarsForDay(this, today, today + (1000*60*60*24));

        ListView listEvents = (ListView)findViewById(R.id.listEvents);
        listEvents.setAdapter(new EventsListAdapter(this, 0, events));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day_events, menu);
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
