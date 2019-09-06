package com.example.livesmart;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

public class ScheduleParticularDateActivity extends AppCompatActivity {

    String dateSelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.particular_date_pick);
        startDatePicker();

    }

    private void startDatePicker(){

        final Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DAY = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dateSelected = String.valueOf(dayOfMonth);
                    }
                }, YEAR, MONTH, DAY);
        datePickerDialog.show();

        datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if( dateSelected.isEmpty() ){
                    Intent intent = new Intent(getApplicationContext(), ScheduleCategoryActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(), ScheduleAddTimeActivity.class);
                    startActivity(intent);
                }
            }
        });


        datePickerDialog.show();
    }


}
