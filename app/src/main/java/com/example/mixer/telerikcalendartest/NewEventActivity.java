package com.example.mixer.telerikcalendartest;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


public class NewEventActivity extends Activity {
    Button createEvent, cancelNewEvent;
    EditText eventTitle, eventLocation, eventDescription;
    TextView eventDateStart, eventDateEnd, eventTimeStart, eventTimeEnd;
    Spinner eventRrule, eventReminder, eventAvailability;
    CheckBox eventAllDay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        //Init elements
        createEvent = (Button)findViewById(R.id.btnCreateEvent);
        cancelNewEvent = (Button)findViewById(R.id.btnCancel);

        eventTitle = (EditText)findViewById(R.id.title);
        eventLocation = (EditText)findViewById(R.id.location);
        eventDescription = (EditText)findViewById(R.id.description);
        eventDateStart = (TextView)findViewById(R.id.start_date);
        eventDateEnd = (TextView)findViewById(R.id.end_date);
        eventTimeStart = (TextView)findViewById(R.id.start_time);
        eventTimeEnd = (TextView)findViewById(R.id.end_time);
        eventRrule = (Spinner)findViewById(R.id.rrule);
        eventReminder = (Spinner)findViewById(R.id.reminder_add);
        eventAvailability = (Spinner)findViewById(R.id.availability);
        eventAllDay = (CheckBox)findViewById(R.id.is_all_day);



        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Variables

                Date convertedDateStart = null;
                Date convertedDateEnd = null;
                String rrule = "";
                String eventDay = "";
                String eventDayNumber = "";
                String eventUntil = "";
                int reminderTime = 0;
                int availability = 0;

                //Hacer validaciones de que se llenaron los campos minimos
                //Titulo
                //Inicio
                //Fin




                //Obtener valores de los campos
                eventTitle.getText(); //Ya
                eventLocation.getText(); // Ya
                eventDescription.getText(); // Ya
                eventDateStart.getText(); // Ya
                eventDateEnd.getText(); // YA
                eventTimeStart.getText(); // Ya
                eventTimeEnd.getText(); // yA
                eventRrule.getSelectedItem().toString();
                eventReminder.getSelectedItem().toString();
                eventAvailability.getSelectedItem().toString();
                eventAllDay.isChecked(); // ya


                //Crear los string de fechas de inicio y fin
                String startDateString = eventDateStart.getText()+"/"+eventTimeStart.getText();
                String endDateString = eventDateEnd.getText()+"/"+eventTimeEnd.getText();
                SimpleDateFormat formatTime = new SimpleDateFormat("dd/MM/yyyy/hh:mm");
                formatTime.setTimeZone(TimeZone.getDefault());


                //Convertir la fecha en string a date obj
                try{
                    convertedDateStart = formatTime.parse(startDateString);
                    convertedDateEnd = formatTime.parse(endDateString);
                }catch(ParseException e){
                    e.printStackTrace();
                }



                //Obtener el dia de la fecha de inicio
                Calendar cal = Calendar.getInstance();
                cal.setTime(convertedDateStart);
                eventDay = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
                eventDayNumber = eventDateStart.getText().toString().substring(0,2);
                eventUntil = "UNTIL="+(cal.get(Calendar.YEAR)+1)+eventDateStart.getText().toString().substring(3,5)+eventDateStart.getText().toString().substring(0,2)+"T000000Z";
                eventDay = eventDay.substring(0, 2).toUpperCase();


                //Switch para determinar el valor de Rrule
                switch (eventRrule.getSelectedItemPosition()){
                    case 1: rrule = "FREQ=WEEKLY;INTERVAL=1;WKST=SU;BYDAY=MO,TU,WE,TH,FR;"+eventUntil;
                            break;
                    case 2: rrule = "FREQ=WEEKLY;INTERVAL=1;WKST=SU;BYDAY="+eventDay+";"+eventUntil;
                            break;
                    case 3: rrule = "FREQ=WEEKLY;INTERVAL=3;WKST=SU;BYDAY="+eventDay+";"+eventUntil;
                            break;
                    case 4: rrule = "FREQ=MONTHLY;INTERVAL=1;WKST=SU;BYMONTHDAY="+eventDayNumber+";"+eventUntil;
                            break;
                    case 5: rrule = "FREQ=YEARLY;INTERVAL=1;WKST=SU";
                            break;
                    default: rrule = "";
                            break;
                }


                //Switch para determinar el valor de reminder

                switch(eventReminder.getSelectedItemPosition()){
                    case 0 : reminderTime = 5;
                        break;
                    case 1 : reminderTime = 10;
                        break;
                    case 2 : reminderTime = 15;
                        break;
                    case 3 : reminderTime = 20;
                        break;
                    case 4 : reminderTime = 25;
                        break;
                    case 5 : reminderTime = 30;
                        break;
                    case 6 : reminderTime = 45;
                        break;
                    case 7 : reminderTime = 60;
                        break;
                    case 8 : reminderTime = 120;
                        break;
                    case 9 : reminderTime = 180;
                        break;
                    case 10 : reminderTime = 720;
                        break;
                    case 11 : reminderTime = 1440;
                        break;
                    case 12 : reminderTime = 2880;
                        break;
                    case 13 : reminderTime = 10080;
                        break;
                    default: reminderTime = 0;
                        break;
                }


                //Switch para determinar el valor de availability

                switch (eventAvailability.getSelectedItemPosition()){
                    case 0: availability = CalendarContract.Events.AVAILABILITY_BUSY;
                        break;
                    case 1: availability = CalendarContract.Events.AVAILABILITY_FREE;
                        break;
                    case 2: availability = CalendarContract.Events.AVAILABILITY_TENTATIVE;
                        break;
                    default: availability = CalendarContract.Events.AVAILABILITY_FREE;
                        break;
                }




                /* Logs para chequear valores

                Log.d("Valores evento: ", "" + eventTitle.getText() + eventLocation.getText() + eventDescription.getText() + eventRrule.getSelectedItem().toString() + eventReminder.getSelectedItem().toString() + eventAvailability.getSelectedItem().toString() );
                Log.d("ALLDAY?",  ""+eventAllDay.isChecked());
                Log.d("RRULE", ""+eventRrule.getSelectedItemPosition());
                Log.d("EVENTDAY", "" + eventDay.substring(0, 2).toUpperCase());
                */


                //Creacion de evento


                long calId = 1;
                if (calId == -1) {
                    // no calendar account; react meaningfully
                    return;
                }




                ContentValues values = new ContentValues();
                values.put(CalendarContract.Events.DTSTART, convertedDateStart.getTime());
                values.put(CalendarContract.Events.DTEND, convertedDateEnd.getTime());
                values.put(CalendarContract.Events.RRULE, rrule);
                values.put(CalendarContract.Events.TITLE, eventTitle.getText().toString());
                values.put(CalendarContract.Events.EVENT_LOCATION, eventLocation.getText().toString());
                values.put(CalendarContract.Events.CALENDAR_ID, calId);
                values.put(CalendarContract.Events.DESCRIPTION, eventDescription.getText().toString() );
                values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().toString());
                // reasonable defaults exist:

                values.put(CalendarContract.Events.ALL_DAY, eventAllDay.isChecked());
                values.put(CalendarContract.Events.AVAILABILITY, availability);
                Uri uri =
                        getContentResolver().
                                insert(CalendarContract.Events.CONTENT_URI, values);
                long eventId = new Long(uri.getLastPathSegment());

                //Para poner el reminder se hace con el id del evento, el tiempo y el metodo (alert)
                System.out.print(eventId);


                // reminder insert
                ContentValues values2 = new ContentValues();
                values2.put( "event_id", eventId);
                values2.put( "method", 1 );
                values2.put( "minutes", reminderTime );
                Uri uri2 =
                        getContentResolver().
                                insert(CalendarContract.Reminders.CONTENT_URI, values2);



            }
        });

        cancelNewEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Hacer algo para devolverme con el boton cancelar
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_event, menu);
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
