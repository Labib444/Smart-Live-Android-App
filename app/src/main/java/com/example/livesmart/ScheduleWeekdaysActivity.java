package com.example.livesmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.livesmart.Weekdays_Schedule.Adapter;
import com.example.livesmart.Weekdays_Schedule.Data;

import java.util.ArrayList;
import java.util.List;

public class ScheduleWeekdaysActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ViewPager viewPager;
    Adapter adapter;
    List<Data> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekdays_schedule_page);

        CardView done_cardView = findViewById(R.id.done_cardview);
        done_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SmartModesActivity.class);
                startActivity(intent);
            }
        });

        data = new ArrayList<>();
        data.add(new Data("Sunday", 7*2 +1 ));
        data.add(new Data("Monday", 7*4));
        data.add(new Data("Tuesday",7*6));
        data.add(new Data("Wednesday",7*8));
        data.add(new Data("Thursday", 7*10));
        data.add(new Data("Friday", 7*12));
        data.add(new Data("Saturday", 7*14 +2));

        adapter = new Adapter(data, this);
        viewPager = findViewById(R.id.viewpager_weekdays);


        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);


        progressBar = findViewById(R.id.weekday_progressBar);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                progressBar.setProgress(data.get(position).getProgressValue());
            }
            public void onPageSelected(int position) {
                // Check if this is the page you want.
            }
        });

    }

    public void weekday_onclick(View v){
        Intent intent = new Intent(getApplicationContext(), ScheduleAddTimeActivity.class);
        startActivity(intent);
    }


}
