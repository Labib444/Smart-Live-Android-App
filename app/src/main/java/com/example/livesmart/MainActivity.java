package com.example.livesmart;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.livesmart.BroadCastReceivers.AlarmBroadCast;
import com.example.livesmart.Weekdays_Schedule.Adapter;
import com.example.livesmart.Weekdays_Schedule.Data;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {


    RequestQueue requestQueue;
    String timePicked = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_page);



        //http://api.openweathermap.org/data/2.5/forecast?id=524901&appid=44682f9edf09c0c8f6cf585cf4859893
        //http://api.openweathermap.org/data/2.5/forecast?id=1185241&appid=44682f9edf09c0c8f6cf585cf4859893

        //requestQueue = Volley.newRequestQueue(this);
        //parseJson();

        CardView createSchedule = findViewById(R.id.create_schedule_cardview);
        createSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), ScheduleCategoryActivity.class);
                //startActivity(intent);

                pickEndTimeForSchedule();

                //Log.d("RINGMODE", String.valueOf(am.getRingerMode()) );
                //AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                //am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                //Log.d("RINGMODE", String.valueOf(am.getRingerMode()) );


//                NotificationManager n = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//                if(n.isNotificationPolicyAccessGranted()) {
//                    AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
//                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
//                }else{
//                    // Ask the user to grant access
//                    Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
//                    startActivityForResult(intent);
//                }

            }
        });

    }

    private void parseJson() {

        String URL = "http://api.openweathermap.org/data/2.5/forecast?id=1185241&appid=44682f9edf09c0c8f6cf585cf4859893";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("list");
                    for(int i = 0; i < jsonArray.length(); i++){
                        //SONArray weatherArray = jsonArray.getJSONArray(2);
                        //Log.d("WEATHER", weatherArray.getString(1));

                        JSONObject weatherObject = jsonArray.getJSONObject(i);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
                        String dateString = weatherObject.getString("dt_txt");
                        Date date = simpleDateFormat.parse(dateString);

                        Calendar c1 = Calendar.getInstance();
                        c1.setTime(date);
                        Calendar c2 = Calendar.getInstance();

                        if( (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) && ( c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH) ) ){
                            JSONArray weather =  weatherObject.getJSONArray("weather");
                            JSONObject real_weather = weather.getJSONObject(0);
                            Log.d("WEATHER", real_weather.getString("description")+" "+dateString );
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void pickEndTimeForSchedule(){
        Calendar calendar = Calendar.getInstance();
        timePicked = "";

        int HOUR = calendar.get(Calendar.HOUR);
        int MIN = calendar.get(Calendar.MINUTE);

        final int[] hourTimer = new int[1];
        final int[] minuteTimer = new int[1];

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                //hourTimer[0] = timePicker.getHour();
                //minuteTimer[0] = timePicker.getMinute();
                timePicked = String.valueOf(hour);

                if (android.os.Build.VERSION.SDK_INT >= 23) {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(), timePicker.getMinute(), 0);
                } else {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
                }
            }
        }, HOUR, MIN, false);

        timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if( !timePicked.equals("") ){
                    setAlarm(calendar.getTimeInMillis(), calendar);
                    pickBeginTimeForSchedule();
                }

//                Calendar c = Calendar.getInstance();
//                int cHour = c.get(Calendar.HOUR);
//                int cMin  = c.get(Calendar.MINUTE);
//
//                if( cHour >= hourTimer[0] && cMin >= minuteTimer[0] ){
//                    AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                    Log.d("RINGMODE", String.valueOf(am.getRingerMode()) );
//                    am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//                    Log.d("RINGMODE", String.valueOf(am.getRingerMode()) );
//                }

            }
        });

        timePickerDialog.show();
    }

    private void setAlarm(long timeInMillis, Calendar calendar) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(this, AlarmBroadCast.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
    }

    private void setAlarm2(long timeInMillis, Calendar calendar) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(this, AlarmBroadCast.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 1, i, 0);
        am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
    }

    private void pickBeginTimeForSchedule(){
        Calendar calendar = Calendar.getInstance();
        timePicked = "";

        int HOUR = calendar.get(Calendar.HOUR);
        int MIN = calendar.get(Calendar.MINUTE);

        final int[] hourTimer = new int[1];
        final int[] minuteTimer = new int[1];

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                //hourTimer[0] = timePicker.getHour();
                //minuteTimer[0] = timePicker.getMinute();
                timePicked = String.valueOf(hour);
                if (android.os.Build.VERSION.SDK_INT >= 23) {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(), timePicker.getMinute(), 0);
                } else {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
                }
            }
        }, HOUR, MIN, false);

        timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

                if( !timePicked.equals("") ){
                    setAlarm2(calendar.getTimeInMillis(), calendar);
                }


//                Calendar c = Calendar.getInstance();
//                int cHour = c.get(Calendar.HOUR);
//                int cMin  = c.get(Calendar.MINUTE);
//
//                if( cHour >= hourTimer[0] && cMin >= minuteTimer[0] ){
//                    AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                    Log.d("RINGMODE", String.valueOf(am.getRingerMode()) );
//                    am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//                    Log.d("RINGMODE", String.valueOf(am.getRingerMode()) );
//                }
            }
        });

        timePickerDialog.show();

    }


}
