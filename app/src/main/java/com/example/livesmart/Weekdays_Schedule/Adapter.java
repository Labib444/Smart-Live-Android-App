package com.example.livesmart.Weekdays_Schedule;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.livesmart.R;
import com.example.livesmart.ScheduleAddTimeActivity;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<Data> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter(List<Data> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.weeks, container, false);

        TextView textView = view.findViewById(R.id.weekday_name);
        textView.setText(data.get(position).getText());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}
