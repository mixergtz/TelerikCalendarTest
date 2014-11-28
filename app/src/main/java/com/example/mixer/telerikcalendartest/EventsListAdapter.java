package com.example.mixer.telerikcalendartest;

/**
 * Created by Mixer on 26/11/14.
 */
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.telerik.widget.calendar.RadCalendarView;
import com.telerik.widget.calendar.events.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EventsListAdapter extends ArrayAdapter<Event> {

    private List<CustomEvent> events;
    private Context context;

    public EventsListAdapter(Context context, int resource, List<CustomEvent> eventsFromADay) {
        super(context, resource);
        this.context = context;
        this.events = new ArrayList<CustomEvent>();

        //this.events.addAll(calendarView.getEventAdapter().getEventsForDate(date));
        this.events.addAll(eventsFromADay);
    }

    @Override
    public int getCount() {
        return this.events.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView;
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a", Locale.US);
        Calendar value = Calendar.getInstance();
        String formattedValue;
        Event e = events.get(position);
        if (e.isAllDay()) {
            rootView = View.inflate(context, R.layout.calendar_main_event_list_allday_item, null);
            rootView.setBackgroundColor(events.get(position).getEventColor());
        } else {

            rootView = View.inflate(context, R.layout.calendar_main_event_list_item, null);
            View eventColor = rootView.findViewById(R.id.eventColor);
            eventColor.setBackgroundColor(events.get(position).getEventColor());
            TextView txtStartDate = (TextView) rootView.findViewById(R.id.txtStart);
            TextView txtEndDate = (TextView) rootView.findViewById(R.id.txtEnd);
            TextView txtEventLoc = (TextView) rootView.findViewById(R.id.txtEventLocation);

            value.setTimeInMillis(e.getStartDate());
            formattedValue = dateFormat.format(value.getTime());
            txtStartDate.setText(formattedValue);

            value.setTimeInMillis(e.getEndDate());
            formattedValue = dateFormat.format(value.getTime());
            txtEndDate.setText(formattedValue);

            txtEventLoc.setText(""+events.get(position).getEventLocation());
        }

        TextView txtEventName = (TextView) rootView.findViewById(R.id.txtEventTitle);
        txtEventName.setText(events.get(position).getTitle());

        return rootView;
    }
}

