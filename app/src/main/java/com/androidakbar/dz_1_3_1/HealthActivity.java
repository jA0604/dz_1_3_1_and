package com.androidakbar.dz_1_3_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class HealthActivity extends AppCompatActivity {

    private String TAG = "HealthActivity";
    private TextView txtDate;
    private TextView txtTime;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Calendar calendar;
    private ArrayList<PersonHealth> personHealthList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        final EditText etUpper = findViewById(R.id.et_up_press);
        final EditText etLower = findViewById(R.id.et_low_press);
        final EditText etPulse = findViewById(R.id.et_pulse);
        final CheckBox chkTakhikard = findViewById(R.id.chk_takhikard);


//************ Блок обработки ввода даты и времени

        txtDate = (TextView) findViewById(R.id.txt_date);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                 int year = calendar.get(Calendar.YEAR);
                 int month = calendar.get(Calendar.MONTH);
                 int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(HealthActivity.this,
                        android.R.style.Theme_Material_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(HealthActivity.this, R.color.colorPrimaryDarkIndigo))); //Color.HSVToColor(R.color.colorBackgroundIndigo)));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(year, month, day);
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                txtDate.setText(date);
                Log.d(TAG, "cal mDateSetListener" + calendar.getTime());
            }
        };

        txtTime = (TextView) findViewById(R.id.txt_time);
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calendar cal = Calendar.getInstance();
                int mHour = calendar.get(Calendar.HOUR_OF_DAY);
                int mMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(HealthActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String editTextTimeParam = hourOfDay + " : " + minute;
                        txtTime.setText(editTextTimeParam);
                        Log.d(TAG, "time " + "---mmm--- " + hourOfDay + ":" + minute);
                        calendar.set(Calendar.HOUR, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                    }
                }, mHour, mMinute, true);
                timePickerDialog.show();

                Log.d(TAG, "time " + calendar.getTime() + "mmm " + mHour + ":" + mMinute);

            }
        });

//**************** Конец Блока обработки ввода даты и времени

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lowerStr = etLower.getText().toString();
                String upperStr = etUpper.getText().toString();
                String pulseStr = etPulse.getText().toString();

                // log нажатия кнопки пользователем
                Log.i(TAG, "нажатие кнопки SAVE");

                if (lowerStr.isEmpty() || upperStr.isEmpty() || pulseStr.isEmpty()) {
                    Toast.makeText(HealthActivity.this, getText(R.string.msg_empty_fill), Toast.LENGTH_LONG).show();
                    return;
                }

                int lower;
                int upper;
                int pulse;
                try {
                    lower = Integer.parseInt(lowerStr);
                    upper = Integer.parseInt(upperStr);
                    pulse = Integer.parseInt(pulseStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(HealthActivity.this, getText(R.string.msg_non_number), Toast.LENGTH_LONG).show();
                    return;
                }

                final boolean takhikard = chkTakhikard.isChecked();

                Log.d(TAG, "123 " + txtTime.getText().toString() + " --- " + calendar.getTime());

                PersonHealth personHealth = new PersonHealth(lower, upper, pulse, takhikard, calendar);
                personHealthList.add(personHealth);

                Toast.makeText(HealthActivity.this, personHealth.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Button btnPerson = findViewById(R.id.btn_person);
        btnPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HealthActivity.this, MainActivity.class);
                startActivity(intent);
                // log нажатия кнопки пользователем
                Log.i(TAG, "нажатие кнопки перехода на страницу ввода персональных данных");
            }

        });

        Button btnLife = findViewById(R.id.btn_life);
        btnLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HealthActivity.this, LifeActivity.class);
                startActivity(intent);
                // log нажатия кнопки пользователем
                Log.i(TAG, "нажатие кнопки перехода на страницу жизненных показателей");
            }
        });
    }
}