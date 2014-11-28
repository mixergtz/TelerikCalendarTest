package com.example.mixer.telerikcalendartest;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import com.telerik.widget.calendar.events.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Mixer on 26/11/14.
 */
public class CalendarHelper {

    public static List<Event> GetEventsFromCalendars(Context context, long startDate, long endDate){
        List<Event> events = new ArrayList<Event>();

        String[] INSTANCE_PROJECTION = new String[] {
                CalendarContract.Instances._ID, //0
                CalendarContract.Instances.EVENT_ID,   //1
                CalendarContract.Instances.BEGIN, // 2
                CalendarContract.Instances.TITLE, // 3
                CalendarContract.Instances.CALENDAR_COLOR, //4
                CalendarContract.Instances.END, //5
                CalendarContract.Instances.DTSTART, // 6
                CalendarContract.Instances.DTEND, // 7
                CalendarContract.Instances.CALENDAR_ID, // 8
                CalendarContract.Instances.ALL_DAY,
                CalendarContract.Instances.DESCRIPTION,
                CalendarContract.Instances.EVENT_COLOR,
                CalendarContract.Instances.EVENT_LOCATION //12

        };

        Uri.Builder instance_builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(instance_builder, startDate);
        ContentUris.appendId(instance_builder, endDate);

        Cursor cursor = context.getContentResolver().query(instance_builder.build(), INSTANCE_PROJECTION, null, null, null);

        cursor.moveToFirst();

        // fetching calendars name
        String CNames[] = new String[cursor.getCount()];

        // fetching calendars id
        for (int i = 0; i < CNames.length; i++) {

            Log.d("EVENT_ID", ""+cursor.getString(0));
            Log.d("EVENT_ID", ""+cursor.getString(1));
            Log.d("EVENT_ID", ""+cursor.getString(2));
            Log.d("EVENT_ID", ""+cursor.getString(3));
            Log.d("INSTANCE_END", ""+cursor.getString(5));

            Log.d("EVENT_STARt", ""+cursor.getString(6));
            Log.d("EVENT_END", ""+cursor.getString(7));
            Log.d("ALL_DAY", ""+cursor.getString(9));



            //Creamos un nuevo objeto event de la libreria de telerik
            Event event = new Event(cursor.getString(3), Long.parseLong(cursor.getString(2)), Long.parseLong(cursor.getString(5))+1);


            if (Integer.parseInt(cursor.getString(9)) == 1){
                event.setAllDay(true);
            }

/*
            // Vemos si hay rrule en el evento, si la hay, ponemos el color en el evento de la libreria
            String color = cursor.getString(10);
            if (color != null) {
                event.setEventColor(Integer.parseInt(color));
            }

*/
            //Agregamos el evento al arraylist
            events.add(event);


            CNames[i] = cursor.getString(1);
            cursor.moveToNext();

        }

        return events;

    }

    public static List<CustomEvent> GetEventsFromCalendarsForDay(Context context, long startDate, long endDate){
        List<CustomEvent> events = new ArrayList<CustomEvent>();

        String[] INSTANCE_PROJECTION = new String[] {
                CalendarContract.Instances._ID, //0
                CalendarContract.Instances.EVENT_ID,   //1
                CalendarContract.Instances.BEGIN, // 2
                CalendarContract.Instances.TITLE, // 3
                CalendarContract.Instances.CALENDAR_COLOR, //4
                CalendarContract.Instances.END, //5
                CalendarContract.Instances.DTSTART, // 6
                CalendarContract.Instances.DTEND, // 7
                CalendarContract.Instances.CALENDAR_ID, // 8
                CalendarContract.Instances.ALL_DAY,
                CalendarContract.Instances.DESCRIPTION,
                CalendarContract.Instances.EVENT_COLOR,
                CalendarContract.Instances.EVENT_LOCATION //12

        };

        Uri.Builder instance_builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(instance_builder, startDate);
        ContentUris.appendId(instance_builder, endDate);

        Cursor cursor = context.getContentResolver().query(instance_builder.build(), INSTANCE_PROJECTION, null, null, null);

        cursor.moveToFirst();

        // fetching calendars name
        String CNames[] = new String[cursor.getCount()];

        // fetching calendars id
        for (int i = 0; i < CNames.length; i++) {

            Log.d("EVENT_ID", ""+cursor.getString(0));
            Log.d("EVENT_ID", ""+cursor.getString(1));
            Log.d("EVENT_ID", ""+cursor.getString(2));
            Log.d("EVENT_ID", ""+cursor.getString(3));
            Log.d("INSTANCE_END", ""+cursor.getString(5));

            Log.d("EVENT_STARt", ""+cursor.getString(6));
            Log.d("EVENT_END", ""+cursor.getString(7));
            Log.d("ALL_DAY", ""+cursor.getString(9));



            //Creamos un nuevo objeto event de la libreria de telerik
            //Event event = new Event(cursor.getString(3), Long.parseLong(cursor.getString(2)), Long.parseLong(cursor.getString(5))+1);

            CustomEvent event = new CustomEvent(cursor.getString(3), Long.parseLong(cursor.getString(2)), Long.parseLong(cursor.getString(5))+1);

            if (Integer.parseInt(cursor.getString(9)) == 1){
                event.setAllDay(true);
            }

            event.setEventLocation(""+cursor.getString(12));
/*
            // Vemos si hay rrule en el evento, si la hay, ponemos el color en el evento de la libreria
            String color = cursor.getString(10);
            if (color != null) {
                event.setEventColor(Integer.parseInt(color));
            }

*/
            //Agregamos el evento al arraylist
            events.add(event);


            CNames[i] = cursor.getString(1);
            cursor.moveToNext();

        }

        return events;

    }



}


