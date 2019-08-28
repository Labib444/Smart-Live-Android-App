package com.example.livesmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.livesmart.Weekdays_Schedule.Adapter;
import com.example.livesmart.Weekdays_Schedule.Data;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ViewPager viewPager;
    Adapter adapter;
    List<Data> data;
    CardView cardView;
    Chip chip;
    ChipGroup chipGroup;
    String time = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.particular_day_calender);

        DatePickerDialog datePickerDialog;
        DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(final DatePicker view, final int year, final int monthOfYear,
                                  final int dayOfMonth) {

            }
        };

        datePickerDialog =
                new DatePickerDialog(this, startDateListener, Calendar.YEAR, Calendar.MONTH,
                        Calendar.YEAR);
        datePickerDialog.show();



        /*chipGroup = findViewById(R.id.chipGroup_select_time);

        cardView = findViewById(R.id.add_time_for_schedule_cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chip = new Chip(MainActivity.this);
                chip.setCloseIconEnabled(true);
                chip.setChecked(true);


                chip.setOnCloseIconClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        chipGroup.removeView(view);
                    }
                });

                pickBeginTimeForSchedule();
                pickEndTimeForSchedule();



                //if(!chip.getText().equals("")){
                //    chipGroup.addView(chip);
                //}
            }
        });*/


//        data = new ArrayList<>();
//        data.add(new Data("Sunday", 7*2 +1 ));
//        data.add(new Data("Monday", 7*4));
//        data.add(new Data("Tuesday",7*6));
//        data.add(new Data("Wednesday",7*8));
//        data.add(new Data("Thursday", 7*10));
//        data.add(new Data("Friday", 7*12));
//        data.add(new Data("Saturday", 7*14 +2));
//
//        adapter = new Adapter(data, this);
//        viewPager = findViewById(R.id.viewpager_weekdays);
//        viewPager.setAdapter(adapter);
//        viewPager.setPadding(130, 0, 130, 0);
//
//
//        progressBar = findViewById(R.id.weekday_progressBar);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            public void onPageScrollStateChanged(int state) {}
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                progressBar.setProgress(data.get(position).getProgressValue());
//            }
//
//            public void onPageSelected(int position) {
//                // Check if this is the page you want.
//            }
//        });
    }

    private void pickEndTimeForSchedule(){
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MIN = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                String am_pm;
                if(hour>11){
                    am_pm = "PM -";
                    if(hour!=12){
                        hour=hour-12;
                    }
                }else{
                    am_pm = "AM -";
                }

                if(minute == 0){
                    time = time+String.valueOf(hour)+":"+String.valueOf(minute)+"0"+am_pm;
                }
                else{
                    time = time+String.valueOf(hour)+":"+String.valueOf(minute)+am_pm;
                }
            }
        }, HOUR, MIN, false);

        timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                chip.setText(time);
                time = "";
            }
        });

        timePickerDialog.show();
    }

    private void pickBeginTimeForSchedule(){
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MIN = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                String am_pm;
                if(hour>11){
                    am_pm = "PM";
                    if(hour!=12){
                        hour=hour-12;
                    }
                }else{
                    am_pm = "AM";
                }

                if(minute == 0){
                    time = time+String.valueOf(hour)+":"+String.valueOf(minute)+"0"+am_pm;
                }
                else{
                    time = time+String.valueOf(hour)+":"+String.valueOf(minute)+am_pm;
                }

            }

        }, HOUR, MIN, false);


        timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                chip.append(time);
                chipGroup.addView(chip);
                time = "";
            }
        });

        timePickerDialog.show();

    }


}
