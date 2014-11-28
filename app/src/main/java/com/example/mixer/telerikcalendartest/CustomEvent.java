package com.example.mixer.telerikcalendartest;

import com.telerik.widget.calendar.events.Event;

/**
 * Created by Mixer on 28/11/14.
 */
public class CustomEvent extends Event {

    public String location;


    public CustomEvent(String title, long startDate, long endDate){
        super(title, startDate, endDate);
    }

    public void setEventLocation(String eventLocation){
        this.location = eventLocation;
    }

    public String getEventLocation(){
        return this.location;
    }
}
