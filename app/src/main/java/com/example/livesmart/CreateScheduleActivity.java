package com.example.livesmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_schedule_page);

        CardView schedule1_cardView = findViewById(R.id.schedule1_cardView);
        CardView schedule2_cardView = findViewById(R.id.schedule2_cardView);
        CardView schedule3_cardView = findViewById(R.id.schedule3_cardView);

        schedule1_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScheduleWeekdaysActivity.class);
                startActivity(intent);
            }
        });

        schedule2_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScheduleWeekdaysActivity.class);
                startActivity(intent);
            }
        });

        schedule3_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScheduleWeekdaysActivity.class);
                startActivity(intent);
            }
        });

    }
}
