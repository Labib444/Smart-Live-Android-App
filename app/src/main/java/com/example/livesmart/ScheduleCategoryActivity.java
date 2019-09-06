package com.example.livesmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

public class ScheduleCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_of_schedule_page);

        CardView dailyLifeCardView = findViewById(R.id.daily_life_cardview);
        CardView particularDayCardView = findViewById(R.id.particular_day_cardview);

        dailyLifeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateScheduleActivity.class);
                startActivity(intent);
            }
        });

        particularDayCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScheduleParticularDateActivity.class);
                startActivity(intent);
            }
        });




    }
}
