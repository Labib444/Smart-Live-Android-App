package com.example.livesmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Calendar;

public class ScheduleAddTimeActivity extends AppCompatActivity {

    CardView cardView;
    Chip chip;
    ChipGroup chipGroup;
    String time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_time_schedule_page);

        CardView continue_cardView = findViewById(R.id.continue_cardview);
        continue_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScheduleWeekdaysActivity.class);
                startActivity(intent);
            }
        });


        chipGroup = findViewById(R.id.chipGroup_select_time);

        cardView = findViewById(R.id.add_time_for_schedule_cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chip = new Chip(ScheduleAddTimeActivity.this);
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
        });

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
                    am_pm = "PM-";
                    if(hour!=12){
                        hour=hour-12;
                    }
                }else{
                    am_pm = "AM-";
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
